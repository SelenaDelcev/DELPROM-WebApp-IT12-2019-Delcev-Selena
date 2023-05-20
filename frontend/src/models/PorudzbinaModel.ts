class PorudzbinaModel {
    porudzbinaId: number;
    statusPorudzbine: string;
    datumPorudzbine: Date;
    korisnikId: number;
    dostavaId: number;
    placanjeId: number;

    constructor (porudzbinaId: number, statusPorudzbina: string, datumPorudzbine: Date, korisnikId: number, dostavaId: number, placanjeId: number) {
        this.porudzbinaId = porudzbinaId;
        this.statusPorudzbine = statusPorudzbina;
        this.datumPorudzbine = datumPorudzbine;
        this.korisnikId = korisnikId;
        this.dostavaId = dostavaId;
        this.placanjeId = placanjeId;
    }
}

export default PorudzbinaModel;