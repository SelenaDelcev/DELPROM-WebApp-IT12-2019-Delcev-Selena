package delprom.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import delprom.dtos.LoginDto;
import delprom.dtos.SignupDto;
import delprom.exceptions.DelpromAPIException;
import delprom.repositories.KorisnikRepository;
import delprom.security.JwtTokenProvider;
import delprom.services.AuthService;
import delprom.entities.Korisnik;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private KorisnikRepository korisnikRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, KorisnikRepository korisnikRepository,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.korisnikRepository = korisnikRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

        return token;
	}

	@Override
	public String register(SignupDto signupDto) {
		// add check for username exists in database
		if (korisnikRepository.existsByUsername(signupDto.getUsername())) {
			throw new DelpromAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
		}

		// add check for email exists in database
		if (korisnikRepository.existsByEmail(signupDto.getEmail())) {
			throw new DelpromAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
		}

		Korisnik korisnik = new Korisnik();
		korisnik.setIme(signupDto.getIme());
		korisnik.setPrezime(signupDto.getPrezime());
		korisnik.setDatumRodjenja(signupDto.getDatumRodjenja());
		korisnik.setEmail(signupDto.getEmail());
		korisnik.setTelefon(signupDto.getTelefon());
		korisnik.setAdresa(signupDto.getAdresa());
		korisnik.setUsername(signupDto.getUsername());
		korisnik.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		korisnik.setUloga("KUPAC");

		korisnikRepository.save(korisnik);

		return "User registered successfully!.";
	}

}
