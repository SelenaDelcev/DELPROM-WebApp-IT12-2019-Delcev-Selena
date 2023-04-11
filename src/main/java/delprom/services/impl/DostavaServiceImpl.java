package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import delprom.dtos.DostavaDto;
import delprom.dtos.DostavaResponse;
import delprom.entities.Dostava;
import delprom.exceptions.ResourceNotFoundException;
import delprom.repositories.DostavaRepository;
import delprom.services.DostavaService;

@Service
public class DostavaServiceImpl implements DostavaService {

	private DostavaRepository dostavaRepository;

	public DostavaServiceImpl(DostavaRepository dostavaRepository) {
		this.dostavaRepository = dostavaRepository;
	}

	@Override
	public DostavaDto createDostava(DostavaDto dostavaDto) {
		// convert DTO to entity
		Dostava dostava = mapToEntity(dostavaDto);

		Dostava newDostava = dostavaRepository.save(dostava);

		// convert entity to DTO
		DostavaDto dostavaResponse = mapToDTO(newDostava);
		return dostavaResponse;
	}

	private DostavaDto mapToDTO(Dostava dostava) {
		DostavaDto dostavaDto = new DostavaDto();
		dostavaDto.setDostavaId(dostava.getDostavaId());
		dostavaDto.setTroskoviDostave(dostava.getTroskoviDostave());
		dostavaDto.setStatusDostave(dostava.getStatusDostave());
		dostavaDto.setDatumDostave(dostava.getDatumDostave());
		dostavaDto.setTipDostave(dostava.getTipDostave());
		return dostavaDto;
	}

	private Dostava mapToEntity(DostavaDto dostavaDto) {
		Dostava dostava = new Dostava();
		dostava.setTroskoviDostave(dostavaDto.getTroskoviDostave());
		dostava.setStatusDostave(dostavaDto.getStatusDostave());
		dostava.setDatumDostave(dostavaDto.getDatumDostave());
		dostava.setTipDostave(dostavaDto.getTipDostave());
		return dostava;
	}

	@Override
	public DostavaResponse getAllDostava(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Dostava> dostavas = dostavaRepository.findAll(pageable);

		// get content for page object
		List<Dostava> listOfDostava = dostavas.getContent();

		List<DostavaDto> content = listOfDostava.stream().map(dostava -> mapToDTO(dostava))
				.collect(Collectors.toList());

		DostavaResponse dostavaResponse = new DostavaResponse();
		dostavaResponse.setContent(content);
		dostavaResponse.setPageNo(dostavas.getNumber());
		dostavaResponse.setPageSize(dostavas.getSize());
		dostavaResponse.setTotalElements(dostavas.getTotalElements());
		dostavaResponse.setTotalPages(dostavas.getTotalPages());
		dostavaResponse.setLast(dostavas.isLast());

		return dostavaResponse;
	}

	@Override
	public DostavaDto getDostavaById(Integer dostavaId) {

		Dostava dostava = dostavaRepository.findById(dostavaId)
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", dostavaId));

		return mapToDTO(dostava);
	}

	@Override
	public DostavaDto updateDostava(DostavaDto dostavaDto, Integer id) {
		Dostava dostava = dostavaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", id));

		dostava.setTroskoviDostave(dostavaDto.getTroskoviDostave());
		dostava.setStatusDostave(dostavaDto.getStatusDostave());
		dostava.setDatumDostave(dostavaDto.getDatumDostave());
		dostava.setTipDostave(dostavaDto.getTipDostave());

		Dostava updatedDostava = dostavaRepository.save(dostava);
		return mapToDTO(updatedDostava);
	}

	@Override
	public void deleteDostavaById(Integer id) {
		Dostava dostava = dostavaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", id));
		dostavaRepository.delete(dostava);
	}

}
