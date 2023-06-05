package delprom.dtos;

import delprom.entities.Korisnik;

public class PlacanjeStripeDto {
	
	public Integer ukupanIznos;
	
	public Korisnik korisnik;
	
	public String valuta;
	
	public PlacanjeStripeDto() {
		
	}

	public PlacanjeStripeDto(Integer ukupanIznos, Korisnik korisnik, String valuta) {
		this.ukupanIznos = ukupanIznos;
		this.korisnik = korisnik;
		this.valuta = valuta;
	}



	public Integer getUkupanIznos() {
		return ukupanIznos;
	}

	public void setUkupanIznos(Integer ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	
	

}
