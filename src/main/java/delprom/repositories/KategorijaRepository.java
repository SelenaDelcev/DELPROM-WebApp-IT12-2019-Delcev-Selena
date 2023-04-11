package delprom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import delprom.entities.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer> {

}
