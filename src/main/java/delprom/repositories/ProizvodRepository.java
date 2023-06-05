package delprom.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import delprom.entities.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{
	
	Page<Proizvod> findByKategorijaKategorijaId(@PathVariable("kategorijaId") Integer kategorijaId, Pageable pageable);
	Page<Proizvod> findByNazivProizvodaContainingIgnoreCase(@RequestParam("nazivProizvoda") String nazivProizvoda, Pageable pageable);

}
