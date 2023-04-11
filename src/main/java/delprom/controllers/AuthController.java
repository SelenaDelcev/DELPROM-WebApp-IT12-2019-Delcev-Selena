package delprom.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import delprom.dtos.LoginDto;
import delprom.dtos.SignupDto;
import delprom.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import delprom.dtos.JWTAuthResponse;

@RestController
@RequestMapping("/api/auth")
@Tag(
		name = "CRUD REST APIs for Auth Resource"
)
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// Build LOGIN REST API
	@Operation(
			summary = "SIGN IN",
			description = "Sign in")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 201 SUCCESS"
	)
	@PostMapping(value = {"/login", "/signin"})
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);
		
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
	// Build Register REST API
	@Operation(
			summary = "SIGN UP",
			description = "Sign up")
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 201 SUCCESS"
	)
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody SignupDto signupDto){
        String response = authService.register(signupDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
