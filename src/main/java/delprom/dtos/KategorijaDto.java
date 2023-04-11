package delprom.dtos;

import jakarta.validation.constraints.NotBlank;

public class KategorijaDto {

	private Integer kategorijaId;

	@NotBlank(message = "Category name cannot be empty")
	private String nazivKategorije;

	public KategorijaDto() {

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
