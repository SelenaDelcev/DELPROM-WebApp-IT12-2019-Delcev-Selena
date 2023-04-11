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

import delprom.dtos.KategorijaDto;
import delprom.dtos.KategorijaResponse;
import delprom.services.KategorijaService;
import jakarta.validation.Valid;
import delprom.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(
		name = "CRUD REST APIs for Category Resource"
)
public class KategorijaController {

	private KategorijaService kategorijaService;

	public KategorijaController(KategorijaService kategorijaService) {
		this.kategorijaService = kategorijaService;
	}

	// create category product
	@Operation(
			summary = "Create Category REST API",
			description = "Create category REST API is used to save category into database")
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/kategorija")
	public ResponseEntity<?> createKategorija(@Valid @RequestBody KategorijaDto kategorijaDto) {
		try {
			return new ResponseEntity<>(kategorijaService.createKategorija(kategorijaDto), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid category data");
		}

	}

	// get all categories rest 
	@Operation(
			summary = "Get Category REST API",
			description = "Get category REST API is used to get all categories from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@GetMapping("/kategorija")
	public KategorijaResponse getAllKategorija(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "kategorijaId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return kategorijaService.getAllKategorija(pageNo, pageSize, sortBy, sortDir);
	}

	// get category by id
	@Operation(
			summary = "Get Category By Id REST API",
			description = "Get category by id REST API is used to get particular category from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@GetMapping(value = "/kategorija/{id}")
	public ResponseEntity<KategorijaDto> getKategorijaById(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(kategorijaService.getKategorijaById(id));
	}

	// update category by id
	@Operation(
			summary = "Put Category By Id REST API",
			description = "Put category by id REST API is used to put particular category from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/kategorija/{id}")
	public ResponseEntity<?> updateKategorija(@Valid @RequestBody KategorijaDto kategorijaDto,
			@PathVariable(name = "id") Integer id) {
		try {
			KategorijaDto kategorijaResponse = kategorijaService.updateKategorija(kategorijaDto, id);

			return new ResponseEntity<>(kategorijaResponse, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid category data");
		}

	}

	// delete category by id
	@Operation(
			summary = "Delete Category By Id REST API",
			description = "Delete category by id REST API is used to delete particular category from the database")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/kategorija/{id}")
	public ResponseEntity<String> deleteKategorija(@PathVariable(name = "id") Integer id) {

		kategorijaService.deleteKategorijaById(id);

		return new ResponseEntity<>("Category entity deleted successfully.", HttpStatus.OK);
	}

}
