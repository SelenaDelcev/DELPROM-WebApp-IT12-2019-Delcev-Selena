package delprom.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class DostavaDto {

	private Integer dostavaId;

	@Min(value = 0, message = "Must be equal or greater than 0")
	private Integer troskoviDostave;

	@NotBlank(message = "Delivery status cannot be blank")
	private String statusDostave;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Delivery date must be in the past or present")
	private Date datumDostave;

	@NotBlank(message = "Type of delivery cannot be blank")
	private String tipDostave;

	public DostavaDto() {

	}

	public Integer getDostavaId() {
		return dostavaId;
	}

	public void setDostavaId(Integer dostavaId) {
		this.dostavaId = dostavaId;
	}

	public Integer getTroskoviDostave() {
		return troskoviDostave;
	}

	public void setTroskoviDostave(Integer troskoviDostave) {
		this.troskoviDostave = troskoviDostave;
	}

	public String getStatusDostave() {
		return statusDostave;
	}

	public void setStatusDostave(String statusDostave) {
		this.statusDostave = statusDostave;
	}

	public Date getDatumDostave() {
		return datumDostave;
	}

	public void setDatumDostave(Date datumDostave) {
		this.datumDostave = datumDostave;
	}

	public String getTipDostave() {
		return tipDostave;
	}

	public void setTipDostave(String tipDostave) {
		this.tipDostave = tipDostave;
	}

}
