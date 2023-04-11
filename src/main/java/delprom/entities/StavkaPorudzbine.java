package delprom.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stavkaporudzbine")
public class StavkaPorudzbine {
	
	@Id
	@SequenceGenerator(name = "stavkaporudzbine_seq", sequenceName = "stavkaporudzbine_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stavkaporudzbine_seq")
	@Column(name = "stavkaporudzbine_id")
	private Integer stavkaPorudzbineId;
	
	@Column(name = "kolicina")
	private Integer kolicina;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proizvod_id", nullable = false)
	private Proizvod proizvod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "porudzbina_id", nullable = false)
	private Porudzbina porudzbina;
	
	public StavkaPorudzbine() {
		
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

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	
}
