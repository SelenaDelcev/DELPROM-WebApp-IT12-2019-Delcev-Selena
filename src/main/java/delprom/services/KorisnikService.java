package delprom.services;

import delprom.dtos.KorisnikDto;
import delprom.dtos.KorisnikResponse;

public interface KorisnikService {

	KorisnikDto createKorisnik(KorisnikDto korisnikDto);

	KorisnikResponse getAllKorisnik(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	KorisnikDto getKorisnikById(Integer id);
	
	KorisnikDto getMyDataByEmail(String email);

	KorisnikDto updateKorisnik(KorisnikDto korisnikDto, Integer id);
	
	KorisnikDto updateMyData(KorisnikDto korisnikDto, String userEmail);

	void deleteKorisnikById(Integer id);

}
