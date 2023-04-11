package delprom.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "dostava")
public class Dostava {

	@Id
	@SequenceGenerator(name = "dostava_seq", sequenceName = "dostava_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dostava_seq")
	@Column(name = "dostava_id")
	private Integer dostavaId;

	@Column(name = "troskovi_dostave")
	private Integer troskoviDostave;

	@Column(name = "status_dostave", nullable = false)
	private String statusDostave;

	@Temporal(TemporalType.DATE)
	@Column(name = "datum_dostave")
	private Date datumDostave;

	@Column(name = "tip_dostave", nullable = false)
	private String tipDostave;

	@OneToOne(mappedBy = "dostava", cascade = CascadeType.REMOVE)
	private Porudzbina porudzbina;

	public Dostava() {

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

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

}
