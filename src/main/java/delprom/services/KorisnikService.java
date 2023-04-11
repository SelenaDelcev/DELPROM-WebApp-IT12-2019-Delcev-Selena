package delprom.services;

import delprom.dtos.KorisnikDto;
import delprom.dtos.KorisnikResponse;

public interface KorisnikService {

	KorisnikDto createKorisnik(KorisnikDto korisnikDto);

	KorisnikResponse getAllKorisnik(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	KorisnikDto getKorisnikById(Integer id);

	KorisnikDto updateKorisnik(KorisnikDto korisnikDto, Integer id);

	void deleteKorisnikById(Integer id);

}
