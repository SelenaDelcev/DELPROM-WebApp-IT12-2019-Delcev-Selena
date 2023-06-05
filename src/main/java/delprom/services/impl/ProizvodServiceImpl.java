package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import delprom.dtos.ProizvodDto;
import delprom.dtos.ProizvodResponse;
import delprom.entities.Kategorija;
import delprom.entities.Proizvod;
import delprom.exceptions.ResourceNotFoundException;
import delprom.repositories.KategorijaRepository;
import delprom.repositories.ProizvodRepository;
import delprom.services.ProizvodService;

@Service
public class ProizvodServiceImpl implements ProizvodService {

	private ProizvodRepository proizvodRepository;
	private KategorijaRepository kategorijaRepository;

	public ProizvodServiceImpl(ProizvodRepository proizvodRepository, KategorijaRepository kategorijaRepository) {
		this.proizvodRepository = proizvodRepository;
		this.kategorijaRepository = kategorijaRepository;
	}

	@Override
	public ProizvodDto createProizvod(ProizvodDto proizvodDto) {
		// convert DTO to entity
		Proizvod proizvod = mapToEntity(proizvodDto);
		Kategorija newKategorija = kategorijaRepository.findById(proizvodDto.getKategorijaId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", proizvodDto.getKategorijaId()));
		proizvod.setKategorija(newKategorija);
		Proizvod newProizvod = proizvodRepository.save(proizvod);

		// convert entity to DTO
		ProizvodDto proizvodResponse = mapToDTO(newProizvod);
		return proizvodResponse;
	}

	@Override
	public ProizvodResponse getAllProizvod(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Proizvod> proizvods = proizvodRepository.findAll(pageable);

		// get content for page object
		List<Proizvod> listOfProizvod = proizvods.getContent();

		List<ProizvodDto> content = listOfProizvod.stream().map(proizvod -> mapToDTO(proizvod))
				.collect(Collectors.toList());

		ProizvodResponse proizvodResponse = new ProizvodResponse();
		proizvodResponse.setContent(content);
		proizvodResponse.setPageNo(proizvods.getNumber());
		proizvodResponse.setPageSize(proizvods.getSize());
		proizvodResponse.setTotalElements(proizvods.getTotalElements());
		proizvodResponse.setTotalPages(proizvods.getTotalPages());
		proizvodResponse.setLast(proizvods.isLast());

		return proizvodResponse;
	}

	@Override
	public ProizvodResponse getProizvodByKategorijaId(Integer kategorijaId, Integer pageNo, Integer pageSize,
			String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Proizvod> proizvods = proizvodRepository.findByKategorijaKategorijaId(kategorijaId, pageable);

		// get content for page object
		List<Proizvod> listOfProizvod = proizvods.getContent();

		List<ProizvodDto> content = listOfProizvod.stream().map(proizvod -> mapToDTO(proizvod))
				.collect(Collectors.toList());

		ProizvodResponse proizvodResponse = new ProizvodResponse();
		proizvodResponse.setContent(content);
		proizvodResponse.setPageNo(proizvods.getNumber());
		proizvodResponse.setPageSize(proizvods.getSize());
		proizvodResponse.setTotalElements(proizvods.getTotalElements());
		proizvodResponse.setTotalPages(proizvods.getTotalPages());
		proizvodResponse.setLast(proizvods.isLast());

		return proizvodResponse;
	}

	@Override
	public ProizvodDto getProizvodById(Integer proizvodId) {

		Proizvod proizvod = proizvodRepository.findById(proizvodId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", proizvodId));

		return mapToDTO(proizvod);
	}

	@Override
	public ProizvodDto updateProizvod(ProizvodDto proizvodDto, Integer id) {

		Proizvod proizvod = proizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

		proizvod.setSifraProizvoda(proizvodDto.getSifraProizvoda());
		proizvod.setNazivProizvoda(proizvodDto.getNazivProizvoda());
		proizvod.setCena(proizvodDto.getCena());
		proizvod.setNazivProizvodjaca(proizvodDto.getNazivProizvodjaca());
		proizvod.setBoja(proizvodDto.getBoja());
		proizvod.setNamena(proizvodDto.getNamena());
		proizvod.setOpisProizvoda(proizvodDto.getOpisProizvoda());
		proizvod.setSlika(proizvodDto.getSlika());
		proizvod.setKolicinaNaStanju(proizvodDto.getKolicinaNaStanju());

		Kategorija newKategorija = kategorijaRepository.findById(proizvodDto.getKategorijaId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", proizvodDto.getKategorijaId()));
		proizvod.setKategorija(newKategorija);

		Proizvod updatedProizvod = proizvodRepository.save(proizvod);
		return mapToDTO(updatedProizvod);
	}

	@Override
	public void deleteProizvodById(Integer id) {
		Proizvod proizvod = proizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		proizvodRepository.delete(proizvod);
	}

	private ProizvodDto mapToDTO(Proizvod proizvod) {

		ProizvodDto proizvodDto = new ProizvodDto();
		proizvodDto.setProizvodId(proizvod.getProizvodId());
		proizvodDto.setSifraProizvoda(proizvod.getSifraProizvoda());
		proizvodDto.setNazivProizvoda(proizvod.getNazivProizvoda());
		proizvodDto.setCena(proizvod.getCena());
		proizvodDto.setNazivProizvodjaca(proizvod.getNazivProizvodjaca());
		proizvodDto.setBoja(proizvod.getBoja());
		proizvodDto.setNamena(proizvod.getNamena());
		proizvodDto.setOpisProizvoda(proizvod.getOpisProizvoda());
		proizvodDto.setSlika(proizvod.getSlika());
		proizvodDto.setKolicinaNaStanju(proizvod.getKolicinaNaStanju());
		proizvodDto.setKategorijaId(proizvod.getKategorija().getKategorijaId());
		return proizvodDto;
	}

	private Proizvod mapToEntity(ProizvodDto proizvodDto) {
		Proizvod proizvod = new Proizvod();
		proizvod.setSifraProizvoda(proizvodDto.getSifraProizvoda());
		proizvod.setNazivProizvoda(proizvodDto.getNazivProizvoda());
		proizvod.setCena(proizvodDto.getCena());
		proizvod.setNazivProizvodjaca(proizvodDto.getNazivProizvodjaca());
		proizvod.setBoja(proizvodDto.getBoja());
		proizvod.setNamena(proizvodDto.getNamena());
		proizvod.setOpisProizvoda(proizvodDto.getOpisProizvoda());
		proizvod.setSlika(proizvodDto.getSlika());
		proizvod.setKolicinaNaStanju(proizvodDto.getKolicinaNaStanju());
		return proizvod;
	}

	@Override
	public ProizvodResponse searchProizvod(String keywords, Integer pageNo, Integer pageSize, String sortBy,
			String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Proizvod> proizvods = proizvodRepository.findByNazivProizvodaContainingIgnoreCase(keywords, pageable);

		// get content for page object
		List<Proizvod> listOfProizvod = proizvods.getContent();

		List<ProizvodDto> content = listOfProizvod.stream().map(proizvod -> mapToDTO(proizvod))
				.collect(Collectors.toList());

		ProizvodResponse proizvodResponse = new ProizvodResponse();
		proizvodResponse.setContent(content);
		proizvodResponse.setPageNo(proizvods.getNumber());
		proizvodResponse.setPageSize(proizvods.getSize());
		proizvodResponse.setTotalElements(proizvods.getTotalElements());
		proizvodResponse.setTotalPages(proizvods.getTotalPages());
		proizvodResponse.setLast(proizvods.isLast());

		return proizvodResponse;
	}

}
