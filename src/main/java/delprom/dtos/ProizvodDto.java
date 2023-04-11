package delprom.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProizvodDto {

	private Integer proizvodId;

	@NotBlank(message = "Product key cannot be blank")
	private String sifraProizvoda;

	@NotBlank(message = "Product name cannot be blank")
	private String nazivProizvoda;

	@NotNull(message = "The price cannot be null")
	@Min(value = 0, message = "Must be equal or greater than 0")
	private Integer cena;

	@NotBlank(message = "The name of the manufacturer cannot be blank")
	private String nazivProizvodjaca;

	@NotBlank(message = "Product color cannot be blank")
	private String boja;

	@NotBlank(message = "Product purpose cannot be blank")
	private String namena;

	private String opisProizvoda;

	@NotBlank(message = "Product image cannot be blank")
	private String slika;

	@NotNull(message = "Quantity of products in stock cannot be null")
	@Min(value = 0, message = "Must be equal or greater than 0")
	private Integer kolicinaNaStanju;
	
	private Integer kategorijaId;


	public ProizvodDto() {

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
	
	public Integer getKategorijaId() {
		return kategorijaId;
	}

	public void setKategorijaId(Integer kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

}
