package delprom.services;

import delprom.dtos.KategorijaDto;
import delprom.dtos.KategorijaResponse;

public interface KategorijaService {

	KategorijaDto createKategorija(KategorijaDto kategorijaDto);

	KategorijaResponse getAllKategorija(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	KategorijaDto getKategorijaById(Integer id);

	KategorijaDto updateKategorija(KategorijaDto kategorijaDto, Integer id);

	void deleteKategorijaById(Integer id);

}
