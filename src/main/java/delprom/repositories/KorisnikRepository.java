package delprom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import delprom.entities.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	Optional<Korisnik> findByEmail(String email);

	Optional<Korisnik> findByUsernameOrEmail(String username, String email);

	Optional<Korisnik> findByUsername(String username);
	
	Optional<Korisnik> findByUloga(String uloga);
	
	Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
