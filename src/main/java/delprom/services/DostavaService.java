package delprom.services;

import delprom.dtos.DostavaDto;
import delprom.dtos.DostavaResponse;

public interface DostavaService {

	DostavaDto createDostava(DostavaDto dostavaDto);

	DostavaResponse getAllDostava(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	DostavaDto getDostavaById(Integer dostavaId);

	DostavaDto updateDostava(DostavaDto dostavaDto, Integer id);

	void deleteDostavaById(Integer id);

}
