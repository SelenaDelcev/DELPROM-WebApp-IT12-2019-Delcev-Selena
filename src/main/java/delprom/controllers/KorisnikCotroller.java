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

import delprom.dtos.KorisnikDto;
import delprom.dtos.KorisnikResponse;
import delprom.services.KorisnikService;
import delprom.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(
		name = "CRUD REST APIs for User Resource"
)
public class KorisnikCotroller {

	private KorisnikService korisnikService;

	public KorisnikCotroller(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}

	// create user
	@Operation(
			summary = "Create User REST API",
			description = "Create user REST API is used to save user into database")
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/korisnik")
	public ResponseEntity<?> createKorisnik(@Valid @RequestBody KorisnikDto korisnikDto) {
		try {
			return new ResponseEntity<>(korisnikService.createKorisnik(korisnikDto), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid user data");
		}

	}

	// get all users rest api
	@Operation(
			summary = "Get User REST API",
			description = "Get user REST API is used to get all users from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping("/korisnik")
	public KorisnikResponse getAllKorisnik(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "korisnikId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return korisnikService.getAllKorisnik(pageNo, pageSize, sortBy, sortDir);
	}

	// get user by id
	@Operation(
			summary = "Get User By Id REST API",
			description = "Get user by id REST API is used to get particular user from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@GetMapping(value = "/korisnik/{id}")
	public ResponseEntity<KorisnikDto> getKorisnikById(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(korisnikService.getKorisnikById(id));
	}

	// update user by id
	@Operation(
			summary = "Put User By Id REST API",
			description = "Put user by id REST API is used to put particular user from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/korisnik/{id}")
	public ResponseEntity<?> updateKorisnik(@Valid @RequestBody KorisnikDto korisnikDto,
			@PathVariable(name = "id") Integer id) {

		try {
			KorisnikDto korisnikResponse = korisnikService.updateKorisnik(korisnikDto, id);

			return new ResponseEntity<>(korisnikResponse, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid user data");
		}
	}

	// delete user by id
	@Operation(
			summary = "Delete User By Id REST API",
			description = "Delete user by id REST API is used to delete particular user from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/korisnik/{id}")
	public ResponseEntity<String> deleteKorisnik(@PathVariable(name = "id") Integer id) {

		korisnikService.deleteKorisnikById(id);

		return new ResponseEntity<>("User entity deleted successfully.", HttpStatus.OK);
	}

}
