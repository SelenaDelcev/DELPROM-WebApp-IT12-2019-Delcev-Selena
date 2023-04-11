package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import delprom.dtos.PlacanjeDto;
import delprom.dtos.PlacanjeResponse;
import delprom.entities.Placanje;
import delprom.exceptions.ResourceNotFoundException;
import delprom.repositories.PlacanjeRepository;
import delprom.services.PlacanjeService;

@Service
public class PlacanjeServiceImpl implements PlacanjeService {

	@Autowired
	private PlacanjeRepository placanjeRepository;

	public PlacanjeServiceImpl(PlacanjeRepository placanjeRepository) {
		this.placanjeRepository = placanjeRepository;
	}

	@Override
	public PlacanjeDto createPlacanje(PlacanjeDto placanjeDto) {
		// convert DTO to entity
		Placanje placanje = mapToEntity(placanjeDto);
		Placanje newPlacanje = placanjeRepository.save(placanje);

		// convert entity to DTO
		PlacanjeDto placanjeResponse = mapToDTO(newPlacanje);
		return placanjeResponse;
	}

	@Override
	public PlacanjeResponse getAllPlacanje(Integer pageNo, Integer pageSize,  String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		
		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Placanje> placanjes = placanjeRepository.findAll(pageable);

		// get content for page object
		List<Placanje> listOfPlacanje = placanjes.getContent();

		List<PlacanjeDto> content = listOfPlacanje.stream().map(placanje -> mapToDTO(placanje))
				.collect(Collectors.toList());

		PlacanjeResponse placanjeResponse = new PlacanjeResponse();
		placanjeResponse.setContent(content);
		placanjeResponse.setPageNo(placanjes.getNumber());
		placanjeResponse.setPageSize(placanjes.getSize());
		placanjeResponse.setTotalElements(placanjes.getTotalElements());
		placanjeResponse.setTotalPages(placanjes.getTotalPages());
		placanjeResponse.setLast(placanjes.isLast());

		return placanjeResponse;
	}

	@Override
	public PlacanjeDto getPlacanjeById(Integer id) {
		Placanje placanje = placanjeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", id));
		return mapToDTO(placanje);
	}

	@Override
	public PlacanjeDto updatePlacanje(PlacanjeDto placanjeDto, Integer id) {
		Placanje placanje = placanjeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", id));
		placanje.setVrstaPlacanja(placanjeDto.getVrstaPlacanja());
		placanje.setOdobrenPopust(placanjeDto.getOdobrenPopust());
		placanje.setDatumPlacanja(placanjeDto.getDatumPlacanja());

		Placanje updatedPlacanje = placanjeRepository.save(placanje);
		return mapToDTO(updatedPlacanje);
	}

	@Override
	public void deletePlacanjeById(Integer id) {
		Placanje placanje = placanjeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "id", id));
		placanjeRepository.delete(placanje);

	}

	private Placanje mapToEntity(PlacanjeDto placanjeDto) {
		Placanje placanje = new Placanje();
		placanje.setVrstaPlacanja(placanjeDto.getVrstaPlacanja());
		placanje.setOdobrenPopust(placanje.getOdobrenPopust());
		placanje.setDatumPlacanja(placanjeDto.getDatumPlacanja());
		return placanje;
	}

	// convert Entity into DTO
	private PlacanjeDto mapToDTO(Placanje placanje) {
		PlacanjeDto placanjeDto = new PlacanjeDto();
		placanjeDto.setPlacanjeId(placanje.getPlacanjeId());
		placanjeDto.setVrstaPlacanja(placanje.getVrstaPlacanja());
		placanjeDto.setOdobrenPopust(placanje.getOdobrenPopust());
		placanjeDto.setDatumPlacanja(placanje.getDatumPlacanja());
		return placanjeDto;
	}

}
