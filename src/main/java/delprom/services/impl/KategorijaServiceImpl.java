package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import delprom.dtos.KategorijaDto;
import delprom.dtos.KategorijaResponse;
import delprom.repositories.KategorijaRepository;
import delprom.services.KategorijaService;
import delprom.entities.Kategorija;
import delprom.exceptions.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class KategorijaServiceImpl implements KategorijaService {

	@Autowired
	private KategorijaRepository kategorijaRepository;

	public KategorijaServiceImpl(KategorijaRepository kategorijaRepository) {
		this.kategorijaRepository = kategorijaRepository;
	}

	@Override
	public KategorijaDto createKategorija(KategorijaDto kategorijaDto) {

		// convert DTO to entity
		Kategorija kategorija = mapToEntity(kategorijaDto);
		Kategorija newKategorija = kategorijaRepository.save(kategorija);

		// convert entity to DTO
		KategorijaDto kategorijaResponse = mapToDTO(newKategorija);
		return kategorijaResponse;
	}

	@Override
	public KategorijaResponse  getAllKategorija(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		
		// create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        		
		Page<Kategorija> kategorijas = kategorijaRepository.findAll(pageable);
		
		// get content for page object
        List<Kategorija> listOfKategorija = kategorijas.getContent();
        
		List<KategorijaDto> content = listOfKategorija.stream().map(kategorija -> mapToDTO(kategorija)).collect(Collectors.toList());
		
		KategorijaResponse kategorijaResponse = new KategorijaResponse();
		kategorijaResponse.setContent(content);
		kategorijaResponse.setPageNo(kategorijas.getNumber());
		kategorijaResponse.setPageSize(kategorijas.getSize());
		kategorijaResponse.setTotalElements(kategorijas.getTotalElements());
		kategorijaResponse.setTotalPages(kategorijas.getTotalPages());
		kategorijaResponse.setLast(kategorijas.isLast());

        return kategorijaResponse;
	}

	// convert Entity into DTO
	private KategorijaDto mapToDTO(Kategorija kategorija) {
		KategorijaDto kategorijaDto = new KategorijaDto();
		kategorijaDto.setKategorijaId(kategorija.getKategorijaId());
		kategorijaDto.setNazivKategorije(kategorija.getNazivKategorije());
		return kategorijaDto;
	}

	// convert DTO to entity
	private Kategorija mapToEntity(KategorijaDto kategorijaDto) {
		Kategorija kategorija = new Kategorija();
		kategorija.setNazivKategorije(kategorijaDto.getNazivKategorije());
		return kategorija;
	}

	@Override
	public KategorijaDto getKategorijaById(Integer id) {
		Kategorija kategorija = kategorijaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		return mapToDTO(kategorija);
	}

	@Override
	public KategorijaDto updateKategorija(KategorijaDto kategorijaDto, Integer id) {
		Kategorija kategorija = kategorijaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		kategorija.setNazivKategorije(kategorijaDto.getNazivKategorije());

		Kategorija updatedKategorija = kategorijaRepository.save(kategorija);
		return mapToDTO(updatedKategorija);
	}

	@Override
	public void deleteKategorijaById(Integer id) {
		Kategorija kategorija = kategorijaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		kategorijaRepository.delete(kategorija);

	}

}
