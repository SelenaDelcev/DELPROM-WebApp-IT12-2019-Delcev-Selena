package delprom.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;

@Entity
public class Placanje {
	
	@Id
	@SequenceGenerator(name = "placanje_seq", sequenceName = "placanje_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placanje_seq")
	@Column(name = "placanje_id")
	private Integer placanjeId;
	
	@Column(name = "vrsta_placanja", nullable = false)
    private String vrstaPlacanja;
	
	@Min(value=0, message="Must be equal or greater than 0")  
	@Column(name = "odobren_popust", nullable = false)
    private Integer odobrenPopust = 0;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datum_placanja")
	private Date datumPlacanja;
	
	@OneToOne(mappedBy = "placanje", cascade = CascadeType.REMOVE)
    private Porudzbina porudzbina;

	public Placanje() {
		
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
	
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	

}
