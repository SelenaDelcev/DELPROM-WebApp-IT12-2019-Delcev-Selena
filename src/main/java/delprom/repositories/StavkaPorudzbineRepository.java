package delprom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import delprom.entities.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer> {

	List<StavkaPorudzbine> findByPorudzbinaPorudzbinaId(Integer porudzbinaId);

	List<StavkaPorudzbine> findByProizvodProizvodId(Integer proizvodId);
}
