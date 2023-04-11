package delprom.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delprom.dtos.DostavaDto;
import delprom.dtos.DostavaResponse;
import delprom.services.DostavaService;
import delprom.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "CRUD REST APIs for Delivery Resource")
public class DostavaController {

	private DostavaService dostavaService;

	public DostavaController(DostavaService dostavaService) {
		this.dostavaService = dostavaService;
	}

	// create delivery
	@Operation(summary = "Create Delivery REST API", description = "Create delivery REST API is used to save delivery into database")
	@ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/dostava")
	public ResponseEntity<?> createDostava(@Valid @RequestBody DostavaDto dostavaDto) {
		try {
			if (dostavaDto.getTroskoviDostave() == null) {
				dostavaDto.setTroskoviDostave(300);
			}
			return new ResponseEntity<>(dostavaService.createDostava(dostavaDto), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid delivery data");
		}
	}

	// get all delivery rest api
	@Operation(summary = "Get Delivery REST API", description = "Get delivery REST API is used to get all delivery from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/dostava")
	public DostavaResponse getAllDostava(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "dostavaId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return dostavaService.getAllDostava(pageNo, pageSize, sortBy, sortDir);
	}

	// get delivery by id
	@Operation(summary = "Get Delivery By Id REST API", description = "Get delivery by id REST API is used to get particular delivery from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/dostava/{dostavaId}")
	public ResponseEntity<DostavaDto> getDostavaById(@PathVariable(value = "dostavaId") Integer dostavaId) {
		DostavaDto dostavaDto = dostavaService.getDostavaById(dostavaId);
		return new ResponseEntity<>(dostavaDto, HttpStatus.OK);
	}

	// update delivery by id
	@Operation(summary = "Put Delivery By Id REST API", description = "Put delivery by id REST API is used to put particular delivery from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/dostava/{id}")
	public ResponseEntity<?> updateDostava(@Valid @RequestBody DostavaDto dostavaDto,
			@PathVariable(name = "id") Integer id) {
		try {
			DostavaDto updatedDostava = dostavaService.updateDostava(dostavaDto, id);
			return new ResponseEntity<>(updatedDostava, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid delivery data");
		}
	}

	// delete delivery by id
	@Operation(summary = "Delete Delivery By Id REST API", description = "Delete delivery by id REST API is used to delete particular delivery from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/dostava/{id}")
	public ResponseEntity<String> deleteDostava(@PathVariable(name = "id") Integer id) {
		dostavaService.deleteDostavaById(id);
		return new ResponseEntity<>("Delivery deleted successfully", HttpStatus.OK);
	}

}
