package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import delprom.dtos.PorudzbinaDto;
import delprom.dtos.PorudzbinaResponse;
import delprom.entities.Dostava;
import delprom.entities.Korisnik;
import delprom.entities.Placanje;
import delprom.entities.Porudzbina;
import delprom.exceptions.DelpromAPIException;
import delprom.exceptions.ResourceNotFoundException;
import delprom.repositories.DostavaRepository;
import delprom.repositories.KorisnikRepository;
import delprom.repositories.PlacanjeRepository;
import delprom.repositories.PorudzbinaRepository;
import delprom.services.PorudzbinaService;

@Service
public class PorudzbinaServiceImpl implements PorudzbinaService {

	private PorudzbinaRepository porudzbinaRepository;
	private KorisnikRepository korisnikRepository;
	private DostavaRepository dostavaRepository;
	private PlacanjeRepository placanjeRepository;

	public PorudzbinaServiceImpl(PorudzbinaRepository porudzbinaRepository, KorisnikRepository korisnikRepository,
			DostavaRepository dostavaRepository, PlacanjeRepository placanjeRepository) {
		this.porudzbinaRepository = porudzbinaRepository;
		this.korisnikRepository = korisnikRepository;
		this.dostavaRepository = dostavaRepository;
		this.placanjeRepository = placanjeRepository;
	}

	@Override
	public PorudzbinaDto createPorudzbina(PorudzbinaDto porudzbinaDto) {
		// convert DTO to entity
		Porudzbina porudzbina = mapToEntity(porudzbinaDto);
		Korisnik newKorisnik = korisnikRepository.findById(porudzbinaDto.getKorisnikId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", porudzbinaDto.getKorisnikId()));
		Dostava newDostava = dostavaRepository.findById(porudzbinaDto.getDostavaId())
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", porudzbinaDto.getDostavaId()));

		Placanje newPlacanje = placanjeRepository.findById(porudzbinaDto.getPlacanjeId())
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", porudzbinaDto.getPlacanjeId()));
		porudzbina.setKorisnik(newKorisnik);
		porudzbina.setDostava(newDostava);
		porudzbina.setPlacanje(newPlacanje);
		Porudzbina newPorudzbina = porudzbinaRepository.save(porudzbina);

		// convert entity to DTO
		PorudzbinaDto porudzbinaResponse = mapToDTO(newPorudzbina);
		return porudzbinaResponse;
	}
	
	@Override
    public void updateStripeId(Integer porudzbinaId, String stripeId) {
        Porudzbina porudzbina = porudzbinaRepository.findById(porudzbinaId)
                .orElseThrow(() -> new DelpromAPIException(HttpStatus.NOT_FOUND, "Order not found"));
        porudzbina.setStripeId(stripeId);
        porudzbinaRepository.save(porudzbina);
    }

	private PorudzbinaDto mapToDTO(Porudzbina porudzbina) {
		PorudzbinaDto porudzbinaDto = new PorudzbinaDto();
		porudzbinaDto.setPorudzbinaId(porudzbina.getPorudzbinaId());
		porudzbinaDto.setStatusPorudzbine(porudzbina.getStatusPorudzbine());
		porudzbinaDto.setDatumPorudzbine(porudzbina.getDatumPorudzbine());
		porudzbinaDto.setKorisnikId(porudzbina.getKorisnik().getKorisnikId());
		porudzbinaDto.setDostavaId(porudzbina.getDostava().getDostavaId());
		porudzbinaDto.setPlacanjeId(porudzbina.getPlacanje().getPlacanjeId());
		return porudzbinaDto;
	}

	private Porudzbina mapToEntity(PorudzbinaDto porudzbinaDto) {
		Porudzbina porudzbina = new Porudzbina();
		porudzbina.setStatusPorudzbine(porudzbinaDto.getStatusPorudzbine());
		porudzbina.setDatumPorudzbine(porudzbinaDto.getDatumPorudzbine());
		return porudzbina;
	}

	@Override
	public PorudzbinaResponse getAllPorudzbina(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		
		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Porudzbina> porudzbinas = porudzbinaRepository.findAll(pageable);

		// get content for page object
		List<Porudzbina> listOfPorudzbina = porudzbinas.getContent();
		
		List<PorudzbinaDto> content = listOfPorudzbina.stream().map(porudzbina -> mapToDTO(porudzbina))
				.collect(Collectors.toList());

		PorudzbinaResponse porudzbinaResponse = new PorudzbinaResponse();
		porudzbinaResponse.setContent(content);
		porudzbinaResponse.setPageNo(porudzbinas.getNumber());
		porudzbinaResponse.setPageSize(porudzbinas.getSize());
		porudzbinaResponse.setTotalElements(porudzbinas.getTotalElements());
		porudzbinaResponse.setTotalPages(porudzbinas.getTotalPages());
		porudzbinaResponse.setLast(porudzbinas.isLast());

		return porudzbinaResponse;
	}

	@Override
	public List<PorudzbinaDto> getPorudzbinaByKorisnikId(Integer korisnikId) {
		List<Porudzbina> porudzbinas = porudzbinaRepository
				.findByKorisnikKorisnikIdOrderByDatumPorudzbineDesc(korisnikId);

		return porudzbinas.stream().map(porudzbina -> mapToDTO(porudzbina)).collect(Collectors.toList());
	}
	
	@Override
    public List<PorudzbinaDto> getPorudzbinaByAuthenticatedUser(Integer korisnikId) {
        // Retrieve the orders for the authenticated user based on their ID
        List<Porudzbina> porudzbinas = porudzbinaRepository.findByKorisnikKorisnikIdOrderByDatumPorudzbineDesc(korisnikId);

        // Map the orders to DTOs and return them
        return porudzbinas.stream().map(porudzbina -> mapToDTO(porudzbina)).collect(Collectors.toList());
    }

	@Override
	public PorudzbinaDto getPorudzbinaByDostavaIdAndPlacanjeId(Integer dostavaId, Integer placanjeId) {
		Dostava dostava = dostavaRepository.findById(dostavaId)
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", dostavaId));
		Placanje placanje = placanjeRepository.findById(placanjeId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", placanjeId));
		;
		Porudzbina porudzbina = porudzbinaRepository.findByDostavaAndPlacanje(dostava, placanje);

		return mapToDTO(porudzbina);
	}

	@Override
	public PorudzbinaDto getPorudzbinaByDostavaId(Integer dostavaId) {
		Dostava dostava = dostavaRepository.findById(dostavaId)
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", dostavaId));
		Porudzbina porudzbina = porudzbinaRepository.findByDostava(dostava);
		return mapToDTO(porudzbina);
	}

	@Override
	public PorudzbinaDto getPorudzbinaByPlacanjeId(Integer placanjeId) {
		Placanje placanje = placanjeRepository.findById(placanjeId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", placanjeId));
		Porudzbina porudzbina = porudzbinaRepository.findByPlacanje(placanje);
		return mapToDTO(porudzbina);
	}

	@Override
	public PorudzbinaDto getPorudzbinaById(Integer porudzbinaId) {
		Porudzbina porudzbina = porudzbinaRepository.findById(porudzbinaId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", porudzbinaId));

		return mapToDTO(porudzbina);
	}

	@Override
	public PorudzbinaDto updatePorudzbina(PorudzbinaDto porudzbinaDto, Integer id) {
		Porudzbina porudzbina = porudzbinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));

		porudzbina.setStatusPorudzbine(porudzbinaDto.getStatusPorudzbine());
		porudzbina.setDatumPorudzbine(porudzbinaDto.getDatumPorudzbine());

		Korisnik newKorisnik = korisnikRepository.findById(porudzbinaDto.getKorisnikId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", porudzbinaDto.getKorisnikId()));
		porudzbina.setKorisnik(newKorisnik);

		Dostava newDostava = dostavaRepository.findById(porudzbinaDto.getDostavaId())
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", porudzbinaDto.getDostavaId()));

		Placanje newPlacanje = placanjeRepository.findById(porudzbinaDto.getPlacanjeId())
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", porudzbinaDto.getPlacanjeId()));
		porudzbina.setKorisnik(newKorisnik);
		porudzbina.setDostava(newDostava);
		porudzbina.setPlacanje(newPlacanje);

		Porudzbina updatedPorudzbina = porudzbinaRepository.save(porudzbina);
		return mapToDTO(updatedPorudzbina);
	}

	@Override
	public void deletePorudzbinaById(Integer id) {
		Porudzbina porudzbina = porudzbinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
		porudzbinaRepository.delete(porudzbina);
	}

}
