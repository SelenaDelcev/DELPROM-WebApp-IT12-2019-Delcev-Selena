package delprom.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "proizvod", uniqueConstraints = { @UniqueConstraint(columnNames = { "sifra_proizvoda" }) })
public class Proizvod {

	@Id
	@SequenceGenerator(name = "proizvod_seq", sequenceName = "proizvod_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proizvod_seq")
	@Column(name = "proizvod_id")
	private Integer proizvodId;

	@Column(name = "sifra_proizvoda", nullable = false)
	private String sifraProizvoda;

	@Column(name = "naziv_proizvoda", nullable = false)
	private String nazivProizvoda;

	@Column(name = "cena", nullable = false)
	private Integer cena;
	
	@Column(name = "naziv_proizvodjaca", nullable = false)
	private String nazivProizvodjaca;

	@Column(name = "boja", nullable = false)
	private String boja;

	@Column(name = "namena", nullable = false)
	private String namena;

	@Column(name = "opis_proizvoda")
	private String opisProizvoda;

	@Column(name = "slika", nullable = false)
	private String slika;

	@Column(name = "kolicina_na_stanju", nullable = false)
	private Integer kolicinaNaStanju;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kategorija_id", nullable = false)
	private Kategorija kategorija;
	
	@OneToMany(mappedBy = "proizvod", cascade = CascadeType.REMOVE)
    private Set<StavkaPorudzbine> stavkePorudzbine = new HashSet<>();
	
	public Proizvod() {
		
	}

	public Integer getProizvodId() {
		return proizvodId;
	}

	public void setProizvodId(Integer proizvodId) {
		this.proizvodId = proizvodId;
	}

	public String getSifraProizvoda() {
		return sifraProizvoda;
	}

	public void setSifraProizvoda(String sifraProizvoda) {
		this.sifraProizvoda = sifraProizvoda;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

	public Integer getCena() {
		return cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}

	public String getNazivProizvodjaca() {
		return nazivProizvodjaca;
	}

	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public String getNamena() {
		return namena;
	}

	public void setNamena(String namena) {
		this.namena = namena;
	}

	public String getOpisProizvoda() {
		return opisProizvoda;
	}

	public void setOpisProizvoda(String opisProizvoda) {
		this.opisProizvoda = opisProizvoda;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public Integer getKolicinaNaStanju() {
		return kolicinaNaStanju;
	}

	public void setKolicinaNaStanju(Integer kolicinaNaStanju) {
		this.kolicinaNaStanju = kolicinaNaStanju;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
	public Set<StavkaPorudzbine> getStavkePorudzbine() {
		return stavkePorudzbine;
	}

	public void setStavkePorudzbine(Set<StavkaPorudzbine> stavkePorudzbine) {
		this.stavkePorudzbine = stavkePorudzbine;
	}

}
