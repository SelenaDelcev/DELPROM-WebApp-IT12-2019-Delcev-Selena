package delprom.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class PlacanjeDto {

	private Integer placanjeId;

	@NotEmpty(message = "Payment type cannot be empty")
	private String vrstaPlacanja;

	@NotNull(message = "Discount cannot be empty")
	@Min(value = 0, message = "Must be equal or greater than 0")
	private Integer odobrenPopust;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Payment date must be in the past or present")
	private Date datumPlacanja;

	public PlacanjeDto() {

	}

	public Integer getPlacanjeId() {
		return placanjeId;
	}

	public void setPlacanjeId(Integer placanjeId) {
		this.placanjeId = placanjeId;
	}

	public String getVrstaPlacanja() {
		return vrstaPlacanja;
	}

	public void setVrstaPlacanja(String vrstaPlacanja) {
		this.vrstaPlacanja = vrstaPlacanja;
	}

	public Integer getOdobrenPopust() {
		return odobrenPopust;
	}

	public void setOdobrenPopust(Integer odobrenPopust) {
		this.odobrenPopust = odobrenPopust;
	}

	public Date getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(Date datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

}
