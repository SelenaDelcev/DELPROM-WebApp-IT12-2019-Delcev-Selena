package delprom.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import delprom.dtos.StavkaPorudzbineDto;
import delprom.services.StavkaPorudzbineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
@Tag(
		name = "CRUD REST APIs for Order Items Resource"
)
public class StavkaPorudzbineController {

	private StavkaPorudzbineService stavkaPorudzbineService;

	public StavkaPorudzbineController(StavkaPorudzbineService stavkaPorudzbineService) {
		this.stavkaPorudzbineService = stavkaPorudzbineService;
	}

	// create order item
	@Operation(
			summary = "Create Order Item REST API",
			description = "Create order item REST API is used to save order item into database")
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/stavkaporudzbine")
	public ResponseEntity<?> createStavkaPorudzbine(@Valid @RequestBody StavkaPorudzbineDto stavkaPorudzbineDto) {
		try {
			return new ResponseEntity<>(stavkaPorudzbineService.createStavkaPorudzbine(stavkaPorudzbineDto),
					HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid order item data");
		}
	}

	// get all order items by orderId
	@Operation(
			summary = "Get All Order Items By Order REST API",
			description = "Get all order items by order REST API is used to get all order items by order from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/stavkaporudzbine/porudzbina/{porudzbinaId}")
	public List<StavkaPorudzbineDto> getAllStavkaPorudzbineByPorudzbinaId(
			@PathVariable(value = "porudzbinaId") Integer porudzbinaId) {
		return stavkaPorudzbineService.getAllStavkaPorudzbineByPorudzbinaId(porudzbinaId);
	}

	// get all order items by productId
	@Operation(
			summary = "Get All Order Items By Product REST API",
			description = "Get all order items by product REST API is used to get all order items by product from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/stavkaporudzbine/proizvod/{proizvodId}")
	public List<StavkaPorudzbineDto> getAllStavkaPorudzbineByProizvodId(
			@PathVariable(value = "proizvodId") Integer proizvodId) {
		return stavkaPorudzbineService.getAllStavkaPorudzbineByProizvodId(proizvodId);
	}

	// update order item by id
	@Operation(
			summary = "Put Order Item By Id REST API",
			description = "Put order item by id REST API is used to delete particular order item from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/stavkaporudzbine/{id}")
	public ResponseEntity<?> updateStavkaPorudzbine(@Valid @RequestBody StavkaPorudzbineDto stavkaPorudzbineDto,
			@PathVariable(name = "id") Integer id) {
		try {
			StavkaPorudzbineDto updatedStavkaPorudzbine = stavkaPorudzbineService
					.updateStavkaPorudzbine(stavkaPorudzbineDto, id);
			return new ResponseEntity<>(updatedStavkaPorudzbine, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid order item data");
		}
	}

	// delete order by id
	@Operation(
			summary = "Delete Order Item By Id REST API",
			description = "Delete order item by id REST API is used to delete particular order item from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/stavkaporudzbine/{id}")
	public ResponseEntity<String> deleteStavkaPorudzbineById(@PathVariable(name = "id") Integer id) {
		stavkaPorudzbineService.deleteStavkaPorudzbineById(id);
		return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
	}

}
