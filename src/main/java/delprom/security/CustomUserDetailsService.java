package delprom.security;

import java.util.Set;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import delprom.entities.Korisnik;
import delprom.repositories.KorisnikRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private KorisnikRepository korisnikRepository;

	public CustomUserDetailsService(KorisnikRepository korisnikRepository) {
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
		.orElseThrow(() -> new UsernameNotFoundException("User not found with email or username: " +usernameOrEmail));
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + korisnik.getUloga()));

		return new org.springframework.security.core.userdetails.User(korisnik.getEmail(), korisnik.getPassword(), authorities);
	}

}
