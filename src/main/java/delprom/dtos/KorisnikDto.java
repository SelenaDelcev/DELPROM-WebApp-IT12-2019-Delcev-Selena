package delprom.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;

public class KorisnikDto {

	private Integer korisnikId;

	@NotBlank(message = "Name cannot be empty")
	private String ime;

	@NotBlank(message = "Surname cannot be empty")
	private String prezime;

	@NotNull(message = "Date of birth cannot be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Date birth must be in the past")
	private Date datumRodjenja;

	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotBlank(message = "The email cannot be empty")
	private String email;

	@NotBlank(message = "The phone number cannot be empty")
	@Size(min = 9, max = 10, message = "The phone number must have 9 or 10 digits")
	private String telefon;

	@NotEmpty(message = "Address cannot be empty")
	private String adresa;

	@NotEmpty(message = "Username cannot be empty")
	private String username;

	@NotEmpty(message = "Password cannot be empty")
	private String password;

	private String uloga;

	public KorisnikDto() {

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

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

}
