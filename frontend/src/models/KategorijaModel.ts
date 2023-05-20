class KategorijaModel {
    kategorijaId: number;
    nazivKategorije: string;

    constructor (kategorijaId: number, nazivKategorije: string) {
        this.kategorijaId = kategorijaId;
        this.nazivKategorije = nazivKategorije;
    }
}

export default KategorijaModel;