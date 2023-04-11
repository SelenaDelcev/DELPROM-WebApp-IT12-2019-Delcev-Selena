package delprom.services;

import delprom.dtos.PlacanjeDto;
import delprom.dtos.PlacanjeResponse;

public interface PlacanjeService {

	PlacanjeDto createPlacanje(PlacanjeDto placanjeDto);

	PlacanjeResponse getAllPlacanje(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	PlacanjeDto getPlacanjeById(Integer id);

	PlacanjeDto updatePlacanje(PlacanjeDto placanjeDto, Integer id);

	void deletePlacanjeById(Integer id);

}
