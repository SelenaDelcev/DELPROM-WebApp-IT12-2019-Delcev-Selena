class DostavaModel {
    dostavaId: number;
    troskoviDostave: number;
    statusDostave: string;
    datumDostave: Date;
    tipDostave: string;

    constructor (dostavaId: number, troskoviDostave: number, statusDostave: string, datumDostave: Date, tipDostave: string) {
        this.dostavaId = dostavaId;
        this.troskoviDostave = troskoviDostave;
        this.statusDostave = statusDostave;
        this.datumDostave = datumDostave;
        this.tipDostave = tipDostave;
    }
}

export default DostavaModel;