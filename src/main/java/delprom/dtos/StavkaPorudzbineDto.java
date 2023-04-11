package delprom.dtos;

import jakarta.validation.constraints.Min;

public class StavkaPorudzbineDto {

	private Integer stavkaPorudzbineId;

	@Min(value = 0, message = "Must be equal or greater than 0")
	private Integer kolicina;

	private Integer porudzbinaId;

	private Integer proizvodId;

	public StavkaPorudzbineDto() {

	}

	public Integer getStavkaPorudzbineId() {
		return stavkaPorudzbineId;
	}

	public void setStavkaPorudzbineId(Integer stavkaPorudzbineId) {
		this.stavkaPorudzbineId = stavkaPorudzbineId;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Integer getPorudzbinaId() {
		return porudzbinaId;
	}

	public void setPorudzbinaId(Integer porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

	public Integer getProizvodId() {
		return proizvodId;
	}

	public void setProizvodId(Integer proizvodId) {
		this.proizvodId = proizvodId;
	}

}
