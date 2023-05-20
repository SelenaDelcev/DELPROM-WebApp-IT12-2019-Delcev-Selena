class PlacanjeModel {
    placanjeId: number;
    vrstaPlacanja: string;
    odobrenPopust: number;
    datumPlacanja: Date;

    constructor (placanjeId: number, vrstaPlacanja: string, odobrenPopust: number, datumPlacanja: Date) {
        this.placanjeId = placanjeId;
        this.vrstaPlacanja = vrstaPlacanja;
        this.odobrenPopust = odobrenPopust;
        this.datumPlacanja = datumPlacanja;
    }
}

export default PlacanjeModel;