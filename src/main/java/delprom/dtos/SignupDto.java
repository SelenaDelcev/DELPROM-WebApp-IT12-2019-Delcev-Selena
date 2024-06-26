package delprom.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class SignupDto {

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

	public SignupDto() {

	}

	public SignupDto(String ime, String prezime, Date datumRodjenja, String email, String telefon, String adresa,
			String username, String password) {
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.email = email;
		this.telefon = telefon;
		this.adresa = adresa;
		this.username = username;
		this.password = password;
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

}
