package delprom.services;

import java.util.List;

import delprom.dtos.PorudzbinaDto;
import delprom.dtos.PorudzbinaResponse;

public interface PorudzbinaService {

	PorudzbinaDto createPorudzbina(PorudzbinaDto porudzbinaDto);

	PorudzbinaResponse getAllPorudzbina(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	List<PorudzbinaDto> getPorudzbinaByKorisnikId(Integer korisnikId);

	List<PorudzbinaDto> getPorudzbinaByAuthenticatedUser(Integer korisnikId);

	PorudzbinaDto getPorudzbinaByDostavaIdAndPlacanjeId(Integer dostavaId, Integer placanjeId);

	PorudzbinaDto getPorudzbinaByDostavaId(Integer dostavaId);

	PorudzbinaDto getPorudzbinaByPlacanjeId(Integer placanjeId);

	PorudzbinaDto getPorudzbinaById(Integer porudzbinaId);

	PorudzbinaDto updatePorudzbina(PorudzbinaDto porudzbinaDto, Integer id);

	void deletePorudzbinaById(Integer id);
	
	void updateStripeId(Integer porudzbinaId, String stripeId);

}
