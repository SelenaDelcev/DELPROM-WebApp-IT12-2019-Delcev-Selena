package delprom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import delprom.entities.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{
	
	List<Proizvod> findByKategorijaKategorijaId(Integer kategorijaId);

}
