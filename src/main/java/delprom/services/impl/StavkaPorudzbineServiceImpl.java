package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import delprom.dtos.StavkaPorudzbineDto;
import delprom.entities.Porudzbina;
import delprom.entities.Proizvod;
import delprom.entities.StavkaPorudzbine;
import delprom.exceptions.ResourceNotFoundException;
import delprom.repositories.PorudzbinaRepository;
import delprom.repositories.ProizvodRepository;
import delprom.repositories.StavkaPorudzbineRepository;
import delprom.services.StavkaPorudzbineService;

@Service
public class StavkaPorudzbineServiceImpl implements StavkaPorudzbineService {

	private StavkaPorudzbineRepository stavkaPorudzbineRepository;
	private PorudzbinaRepository porudzbinaRepository;
	private ProizvodRepository proizvodRepository;

	public StavkaPorudzbineServiceImpl(StavkaPorudzbineRepository stavkaPorudzbineRepository,
			PorudzbinaRepository porudzbinaRepository, ProizvodRepository proizvodRepository) {
		this.stavkaPorudzbineRepository = stavkaPorudzbineRepository;
		this.porudzbinaRepository = porudzbinaRepository;
		this.proizvodRepository = proizvodRepository;
	}

	@Override
	public StavkaPorudzbineDto createStavkaPorudzbine(StavkaPorudzbineDto stavkaPorudzbineDto) {
		// convert DTO to entity
		StavkaPorudzbine stavkaPorudzbine = mapToEntity(stavkaPorudzbineDto);
		Porudzbina newPorudzbina = porudzbinaRepository.findById(stavkaPorudzbineDto.getPorudzbinaId())
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", stavkaPorudzbineDto.getPorudzbinaId()));
		stavkaPorudzbine.setPorudzbina(newPorudzbina);

		Proizvod newProizvod = proizvodRepository.findById(stavkaPorudzbineDto.getProizvodId())
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", stavkaPorudzbineDto.getProizvodId()));
		stavkaPorudzbine.setPorudzbina(newPorudzbina);
		stavkaPorudzbine.setProizvod(newProizvod);
		StavkaPorudzbine newStavkaPorudzbine = stavkaPorudzbineRepository.save(stavkaPorudzbine);

		// convert entity to DTO
		StavkaPorudzbineDto stavkaPorudzbineResponse = mapToDTO(newStavkaPorudzbine);
		return stavkaPorudzbineResponse;
	}

	private StavkaPorudzbineDto mapToDTO(StavkaPorudzbine stavkaPorudzbine) {
		StavkaPorudzbineDto stavkaPorudzbineDto = new StavkaPorudzbineDto();
		stavkaPorudzbineDto.setStavkaPorudzbineId(stavkaPorudzbine.getStavkaPorudzbineId());
		stavkaPorudzbineDto.setKolicina(stavkaPorudzbine.getKolicina());
		stavkaPorudzbineDto.setPorudzbinaId(stavkaPorudzbine.getPorudzbina().getPorudzbinaId());
		stavkaPorudzbineDto.setProizvodId(stavkaPorudzbine.getProizvod().getProizvodId());
		return stavkaPorudzbineDto;
	}

	private StavkaPorudzbine mapToEntity(StavkaPorudzbineDto stavkaPorudzbineDto) {
		StavkaPorudzbine stavkaPorudzbine = new StavkaPorudzbine();
		stavkaPorudzbine.setKolicina(stavkaPorudzbineDto.getKolicina());
		return stavkaPorudzbine;
	}

	@Override
	public List<StavkaPorudzbineDto> getAllStavkaPorudzbineByPorudzbinaId(Integer porudzbinaId) {
		List<StavkaPorudzbine> stavkaporudzbines = stavkaPorudzbineRepository.findByPorudzbinaPorudzbinaId(porudzbinaId);

		return stavkaporudzbines.stream().map(stavkaporudzbine -> mapToDTO(stavkaporudzbine))
				.collect(Collectors.toList());
	}

	@Override
	public List<StavkaPorudzbineDto> getAllStavkaPorudzbineByProizvodId(Integer proizvodId) {
		List<StavkaPorudzbine> stavkaporudzbines = stavkaPorudzbineRepository.findByProizvodProizvodId(proizvodId);

		return stavkaporudzbines.stream().map(stavkaporudzbine -> mapToDTO(stavkaporudzbine))
				.collect(Collectors.toList());
	}

	@Override
	public StavkaPorudzbineDto updateStavkaPorudzbine(StavkaPorudzbineDto stavkaPorudzbineDto, Integer id) {
		StavkaPorudzbine stavkaPorudzbine = stavkaPorudzbineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order Item", "id", id));

		stavkaPorudzbine.setKolicina(stavkaPorudzbineDto.getKolicina());

		Porudzbina newPorudzbina = porudzbinaRepository.findById(stavkaPorudzbineDto.getPorudzbinaId())
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", stavkaPorudzbineDto.getPorudzbinaId()));
		stavkaPorudzbine.setPorudzbina(newPorudzbina);

		Proizvod newProizvod = proizvodRepository.findById(stavkaPorudzbineDto.getProizvodId())
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", stavkaPorudzbineDto.getProizvodId()));

		stavkaPorudzbine.setPorudzbina(newPorudzbina);
		stavkaPorudzbine.setProizvod(newProizvod);

		StavkaPorudzbine updatedStavkaPorudzbine = stavkaPorudzbineRepository.save(stavkaPorudzbine);
		return mapToDTO(updatedStavkaPorudzbine);
	}

	@Override
	public void deleteStavkaPorudzbineById(Integer id) {
		StavkaPorudzbine stavkaPorudzbine = stavkaPorudzbineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order Item", "id", id));
		stavkaPorudzbineRepository.delete(stavkaPorudzbine);

	}

}
