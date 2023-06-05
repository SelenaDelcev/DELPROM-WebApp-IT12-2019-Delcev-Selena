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

import delprom.dtos.ProizvodDto;
import delprom.dtos.ProizvodResponse;
import delprom.services.ProizvodService;
import delprom.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@Tag(name = "CRUD REST APIs for Product Resource")
public class ProizvodController {

	private ProizvodService proizvodService;

	public ProizvodController(ProizvodService proizvodService) {
		this.proizvodService = proizvodService;
	}

	// create product
	@Operation(summary = "Create Prouduct REST API", description = "Create product REST API is used to save product into database")
	@ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
	@SecurityRequirement(name = "Bear Authentication")
	@PostMapping("/proizvod")
	public ResponseEntity<?> createProizvod(@Valid @RequestBody ProizvodDto proizvodDto) {
		try {
			return new ResponseEntity<>(proizvodService.createProizvod(proizvodDto), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid product data");
		}
	}

	// get all products rest api
	@Operation(summary = "Get Products REST API", description = "Get product REST API is used to get all products from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@GetMapping("/proizvod")
	public ProizvodResponse getAllProizvod(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "proizvodId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return proizvodService.getAllProizvod(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/proizvod/search")
	public ProizvodResponse searchProizvod(@RequestParam("keywords") String keywords,
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "proizvodId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return proizvodService.searchProizvod(keywords, pageNo, pageSize, sortBy, sortDir);
	}

	// get all products by category
	@Operation(summary = "Get All Products By Category REST API", description = "Get all products by category REST API is used to get all products by category from the database")
	@GetMapping("/proizvod/search/{kategorijaId}")
	public ProizvodResponse getProizvodByKategorijaId(@PathVariable("kategorijaId") Integer kategorijaId,
	        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	        @RequestParam(value = "sortBy", defaultValue = "proizvodId", required = false) String sortBy,
	        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

	    return proizvodService.getProizvodByKategorijaId(kategorijaId, pageNo, pageSize, sortBy, sortDir);
	}

	// get product by id from category by id
	@Operation(summary = "Get Product By Id REST API", description = "Get product by id REST API is used to get particular product from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@GetMapping("/proizvod/{proizvodId}")
	public ResponseEntity<ProizvodDto> getProizvodById(@PathVariable(value = "proizvodId") Integer proizvodId) {
		ProizvodDto proizvodDto = proizvodService.getProizvodById(proizvodId);
		return new ResponseEntity<>(proizvodDto, HttpStatus.OK);
	}

	// update product by id
	@Operation(summary = "Put Product By Id REST API", description = "Put product by id REST API is used to put particular product from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	@PutMapping("/proizvod/{id}")
	public ResponseEntity<?> updateProizvod(@Valid @RequestBody ProizvodDto proizvodDto,
			@PathVariable(name = "id") Integer id) {
		try {
			ProizvodDto updatedProizvod = proizvodService.updateProizvod(proizvodDto, id);
			return new ResponseEntity<>(updatedProizvod, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Invalid product data");
		}
	}

	// delete product by id
	@Operation(summary = "Delete Product By Id REST API", description = "Delete product by id REST API is used to delete particular product from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	@DeleteMapping("/proizvod/{id}")
	public ResponseEntity<String> deleteProizvod(@PathVariable(name = "id") Integer id) {
		proizvodService.deleteProizvodById(id);
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	}

}
