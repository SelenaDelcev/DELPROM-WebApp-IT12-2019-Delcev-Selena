package delprom.services;

import java.util.List;
import delprom.dtos.StavkaPorudzbineDto;

public interface StavkaPorudzbineService {

	StavkaPorudzbineDto createStavkaPorudzbine(StavkaPorudzbineDto stavkaPorudzbineDto);

	List<StavkaPorudzbineDto> getAllStavkaPorudzbineByPorudzbinaId(Integer porudzbinaId);

	List<StavkaPorudzbineDto> getAllStavkaPorudzbineByProizvodId(Integer proizvodId);

	StavkaPorudzbineDto updateStavkaPorudzbine(StavkaPorudzbineDto stavkaPorudzbineDto, Integer id);

	void deleteStavkaPorudzbineById(Integer id);

}
