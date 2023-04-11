package delprom.controllers;

import java.util.List;

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

import delprom.dtos.PorudzbinaDto;
import delprom.dtos.PorudzbinaResponse;
import delprom.exceptions.ErrorResponse;
import delprom.services.PorudzbinaService;
import delprom.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(
		name = "CRUD REST APIs for Order Resource"
)
public class PorudzbinaController {

	private PorudzbinaService porudzbinaService;

	public PorudzbinaController(PorudzbinaService porudzbinaService) {
		this.porudzbinaService = porudzbinaService;
	}

	// create order
	@Operation(
			summary = "Create Order REST API",
			description = "Create order REST API is used to save order into database")
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/porudzbina")
	public ResponseEntity<?> createPorudzbina(@Valid @RequestBody PorudzbinaDto porudzbinaDto) {
		try {
			return new ResponseEntity<>(porudzbinaService.createPorudzbina(porudzbinaDto), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid order data");
		}
	}

	// get all orders rest api
	@Operation(
			summary = "Get Orders REST API",
			description = "Get orders REST API is used to get all orders from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/porudzbina")
	public PorudzbinaResponse getAllPorudzbina(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "porudzbinaId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return porudzbinaService.getAllPorudzbina(pageNo, pageSize, sortBy, sortDir);
	}

	// get order by id
	@Operation(
			summary = "Get Order By Id REST API",
			description = "Get order by id REST API is used to get particular order from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/porudzbina/id/{id}")
	public ResponseEntity<PorudzbinaDto> getPorudzbinaById(@PathVariable(value = "id") Integer porudzbinaId) {
		PorudzbinaDto porudzbinaDto = porudzbinaService.getPorudzbinaById(porudzbinaId);
		return new ResponseEntity<>(porudzbinaDto, HttpStatus.OK);
	}

	// get all orders by korisnikId
	@Operation(
			summary = "Get Orders By user REST API",
			description = "Get orders by user REST API is used to get particular orders by user from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/porudzbina/korisnik/{korisnikId}")
	public ResponseEntity<?> getPorudzbinaByKorisnikId(@PathVariable(value = "korisnikId") Integer korisnikId) {
		List<PorudzbinaDto> porudzbine = porudzbinaService.getPorudzbinaByKorisnikId(korisnikId);
		if (porudzbine.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("User has not made any orders.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		return ResponseEntity.ok(porudzbine);
	}

	// get order by dostavaId and placanjeId
	@Operation(
			summary = "Get Orders By Payment and Delivery REST API",
			description = "Get orders by payment and delivery REST API is used to get particular rders by payment and delivery from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/porudzbina/dostava/{dostavaId}/placanje/{placanjeId}")
	public ResponseEntity<?> getPorudzbinaByDostavaIdAndPlacanjeId(@PathVariable Integer dostavaId,
			@PathVariable Integer placanjeId) {
		PorudzbinaDto porudzbinaDto = porudzbinaService.getPorudzbinaByDostavaIdAndPlacanjeId(dostavaId, placanjeId);
		return new ResponseEntity<>(porudzbinaDto, HttpStatus.OK);
	}

	// get order by dostavaId
	@Operation(
			summary = "Get Orders By dostavaId REST API",
			description = "Get orders by delivery REST API is used to get particular orders by delivery from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/porudzbina/dostava/{dostavaId}")
	public ResponseEntity<PorudzbinaDto> getPorudzbinaByDostavaIdAndPlacanjeId(@PathVariable Integer dostavaId) {
		PorudzbinaDto porudzbinaDto = porudzbinaService.getPorudzbinaByDostavaId(dostavaId);
		return new ResponseEntity<>(porudzbinaDto, HttpStatus.OK);
	}

	// get order by placanjeId
	@Operation(
			summary = "Get Orders By payment REST API",
			description = "Get orders by payment REST API is used to get particular orders by payment from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/porudzbina/placanje/{placanjeId}")
	public ResponseEntity<PorudzbinaDto> getPorudzbinaByPlacanjeIdAndPlacanjeId(@PathVariable Integer placanjeId) {
		PorudzbinaDto porudzbinaDto = porudzbinaService.getPorudzbinaByDostavaId(placanjeId);
		return new ResponseEntity<>(porudzbinaDto, HttpStatus.OK);
	}

	// update order by id
	@Operation(
			summary = "Put Order By Id REST API",
			description = "Put order by id REST API is used to put particular order from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/porudzbina/{id}")
	public ResponseEntity<?> updatePorudzbina(@Valid @RequestBody PorudzbinaDto porudzbinaDto,
			@PathVariable(name = "id") Integer id) {
		try {
			PorudzbinaDto updatedPorudzbina = porudzbinaService.updatePorudzbina(porudzbinaDto, id);
			return new ResponseEntity<>(updatedPorudzbina, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid order data");
		}
	}

	// delete order by id
	@Operation(
			summary = "Delete Order By Id REST API",
			description = "Delete order by id REST API is used to delete particular order from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/porudzbina/{id}")
	public ResponseEntity<String> deletePorudzbina(@PathVariable(name = "id") Integer id) {
		porudzbinaService.deletePorudzbinaById(id);
		return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
	}

}
