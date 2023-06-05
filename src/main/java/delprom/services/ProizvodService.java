package delprom.services;

import delprom.dtos.ProizvodDto;
import delprom.dtos.ProizvodResponse;

public interface ProizvodService {
	
	ProizvodDto createProizvod(ProizvodDto proizvodDto);
	
	ProizvodResponse getAllProizvod(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
	
	ProizvodResponse searchProizvod(String keywords, Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    ProizvodResponse getProizvodByKategorijaId(Integer kategorijaId, Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    ProizvodDto getProizvodById(Integer proizvodId);

    ProizvodDto updateProizvod(ProizvodDto proizvodDto, Integer id);

    void deleteProizvodById(Integer id);

}
