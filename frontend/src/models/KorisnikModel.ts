class KorisnikModel {
    korisnikId: number;
    ime: string;
    prezime: string;
    datumRodjenja: Date;
    email: string;
    telefon: string;
    adresa: string;
    username: string;
    password: string;
    uloga: string;

    constructor (korisnikId: number, ime: string, prezime: string, datumRodjenja: Date, email: string, 
        telefon: string, adresa: string, username: string, password: string, uloga: string) {
            this.korisnikId = korisnikId;
            this.ime = ime;
            this.prezime = prezime;
            this.datumRodjenja = datumRodjenja;
            this.email = email;
            this.telefon = telefon;
            this.adresa = adresa;
            this.username = username;
            this.password = password;
            this.uloga = uloga;
        }
}

export default KorisnikModel;