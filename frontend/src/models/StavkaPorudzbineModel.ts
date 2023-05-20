class StavkaPorudzbineModel {
    stavkaPorudzbineId: number;
    kolicina: number;
    proizvodId: number;
    porudzbinaId: number;

    constructor (stavkaPorudzbineId: number, kolicina: number, proizvodId: number, porudzbinaId: number) {
        this.stavkaPorudzbineId = stavkaPorudzbineId;
        this.kolicina = kolicina;
        this.proizvodId = proizvodId;
        this.porudzbinaId = porudzbinaId;
    }
}

export default StavkaPorudzbineModel;