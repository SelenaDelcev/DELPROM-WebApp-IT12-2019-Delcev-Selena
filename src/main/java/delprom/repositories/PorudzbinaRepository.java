package delprom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import delprom.entities.Dostava;
import delprom.entities.Placanje;
import delprom.entities.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {

	Porudzbina findByDostava(Dostava dostava);

	List<Porudzbina> findByKorisnikKorisnikIdOrderByDatumPorudzbineDesc(Integer korisnikId);

	Porudzbina findByPlacanje(Placanje placanje);

	Porudzbina findByDostavaAndPlacanje(Dostava dostava, Placanje placanje);

}
