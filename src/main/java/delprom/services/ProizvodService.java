package delprom.services;

import java.util.List;

import delprom.dtos.ProizvodDto;
import delprom.dtos.ProizvodResponse;

public interface ProizvodService {
	
	ProizvodDto createProizvod(ProizvodDto proizvodDto);
	
	ProizvodResponse getAllProizvod(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    List<ProizvodDto> getProizvodByKategorijaId(Integer kategorijaId);

    ProizvodDto getProizvodById(Integer proizvodId);

    ProizvodDto updateProizvod(ProizvodDto proizvodDto, Integer id);

    void deleteProizvodById(Integer id);

}
