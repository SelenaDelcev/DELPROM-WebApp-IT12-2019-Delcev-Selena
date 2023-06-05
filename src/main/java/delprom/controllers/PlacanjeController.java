package delprom.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delprom.dtos.PlacanjeDto;
import delprom.dtos.PlacanjeResponse;
import delprom.services.PlacanjeService;
import delprom.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
@Tag(
		name = "CRUD REST APIs for Payment Resource"
)
public class PlacanjeController {

	private PlacanjeService placanjeService;

	public PlacanjeController(PlacanjeService placanjeService) {
		this.placanjeService = placanjeService;
	}

	// create payment
	@Operation(
			summary = "Create Payment REST API",
			description = "Create payment REST API is used to save payment into database")
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/placanje")
	public ResponseEntity<?> createPlacanje(@Valid @RequestBody PlacanjeDto placanjeDto) {
		try {
			return new ResponseEntity<>(placanjeService.createPlacanje(placanjeDto), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid payment data");
		}
	}

	// get all payment rest api
	@Operation(
			summary = "Get Payment REST API",
			description = "Get payment REST API is used to get all payment from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/placanje")
	public PlacanjeResponse getAllPlacanje(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "placanjeId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return placanjeService.getAllPlacanje(pageNo, pageSize, sortBy, sortDir);
	}

	// get payment by id
	@Operation(
			summary = "Get Payment By Id REST API",
			description = "Get payment by id REST API is used to get particular payment from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping(value = "/placanje/{id}")
	public ResponseEntity<PlacanjeDto> getPlacanjeById(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(placanjeService.getPlacanjeById(id));
	}

	// update payment by id
	@Operation(
			summary = "Put Payment By Id REST API",
			description = "Put payment by id REST API is used to put particular payment from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/placanje/{id}")
	public ResponseEntity<?> updatePlacanje(@Valid @RequestBody PlacanjeDto placanjeDto,
			@PathVariable(name = "id") Integer id) {

		try {
			PlacanjeDto placanjeResponse = placanjeService.updatePlacanje(placanjeDto, id);

			return new ResponseEntity<>(placanjeResponse, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid payment data");
		}
	}

	// delete payment by id
	@Operation(
			summary = "Delete Payment By Id REST API",
			description = "Delete payment by id REST API is used to delete particular payment from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/placanje/{id}")
	public ResponseEntity<String> deletePlacanje(@PathVariable(name = "id") Integer id) {

		placanjeService.deletePlacanjeById(id);

		return new ResponseEntity<>("Payment entity deleted successfully.", HttpStatus.OK);
	}

}
