package delprom.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnik")
public class Korisnik {

	@Id
	@SequenceGenerator(name = "korisnik_seq", sequenceName = "korisnik_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_seq")
	@Column(name = "korisnik_id")
	private Integer korisnikId;

	@Column(name = "ime", nullable = false)
	private String ime;

	@Column(name = "prezime", nullable = false)
	private String prezime;

	@Column(name = "datum_rodjenja", nullable = false)
	private Date datumRodjenja;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "telefon", nullable = false, unique = true)
	private String telefon;

	@Column(name = "adresa", nullable = false)
	private String adresa;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "uloga", nullable = false)
	private String uloga;

	@OneToMany(mappedBy = "korisnik", cascade = CascadeType.REMOVE)
	private Set<Porudzbina> porudzbine = new HashSet<>();

	public Korisnik() {

	}

	public Integer getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(Set<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

}
