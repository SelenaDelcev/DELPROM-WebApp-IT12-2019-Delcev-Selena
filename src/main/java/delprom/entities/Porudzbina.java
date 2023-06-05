package delprom.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "porudzbina")
public class Porudzbina {

    @Id
    @SequenceGenerator(name = "porudzbina_seq", sequenceName = "porudzbina_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porudzbina_seq")
    @Column(name = "porudzbina_id")
    private Integer porudzbinaId;

    @Column(name = "status_porudzbine", nullable = false)
    private String statusPorudzbine;

    @Column(name = "datum_porudzbine", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPorudzbine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @OneToOne
    @JoinColumn(name = "dostava_id")
    private Dostava dostava;

    @OneToOne
    @JoinColumn(name = "placanje_id")
    private Placanje placanje;
    
    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.REMOVE)
    private Set<StavkaPorudzbine> stavkePorudzbine = new HashSet<>();
    
    @Column(name = "stripe_id")
    private String stripeId;

    public Porudzbina() {

    }

    public Integer getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(Integer porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public String getStatusPorudzbine() {
        return statusPorudzbine;
    }

    public void setStatusPorudzbine(String statusPorudzbine) {
        this.statusPorudzbine = statusPorudzbine;
    }

    public Date getDatumPorudzbine() {
        return datumPorudzbine;
    }

    public void setDatumPorudzbine(Date datumPorudzbine) {
        this.datumPorudzbine = datumPorudzbine;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Dostava getDostava() {
        return dostava;
    }

    public void setDostava(Dostava dostava) {
        this.dostava = dostava;
    }

    public Placanje getPlacanje() {
        return placanje;
    }

    public void setPlacanje(Placanje placanje) {
        this.placanje = placanje;
    }
    
    public Set<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(Set<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }
    
    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }
}
