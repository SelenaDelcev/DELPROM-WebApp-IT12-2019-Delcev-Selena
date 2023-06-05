package delprom.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;

public class PorudzbinaDto {

    private Integer porudzbinaId;

    @NotBlank(message = "Order status cannot be blank")
    private String statusPorudzbine;

    @NotNull(message = "Date of order cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The order date cannot be in the future")
    private Date datumPorudzbine;

    private Integer korisnikId;

    private Integer dostavaId;

    private Integer placanjeId;

    private String stripeId; // Add the stripeId field for storing Stripe ID

    public PorudzbinaDto() {
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

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getDostavaId() {
        return dostavaId;
    }

    public void setDostavaId(Integer dostavaId) {
        this.dostavaId = dostavaId;
    }

    public Integer getPlacanjeId() {
        return placanjeId;
    }

    public void setPlacanjeId(Integer placanjeId) {
        this.placanjeId = placanjeId;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }
}
