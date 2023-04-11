package delprom.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kategorija")
public class Kategorija {

	@Id
	@SequenceGenerator(name = "kategorija_seq", sequenceName = "kategorija_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategorija_seq")
	@Column(name = "kategorija_id")
	private Integer kategorijaId;

	@Column(name = "naziv_kategorije", nullable = false)
	private String nazivKategorije;
	
	@OneToMany(mappedBy = "kategorija", cascade = CascadeType.REMOVE)
    private Set<Proizvod> proizvodi = new HashSet<>();

	public Kategorija() {

	}

	public Set<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(Set<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}

	public Integer getKategorijaId() {
		return kategorijaId;
	}

	public void setKategorijaId(Integer kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getNazivKategorije() {
		return nazivKategorije;
	}

	public void setNazivKategorije(String nazivKategorije) {
		this.nazivKategorije = nazivKategorije;
	}

}
