class ProizvodModel {
    proizvodId: number;
    sifraProizvoda: string;
    nazivProizvoda: string;
    cena: number;
    nazivProizvodjaca: string;
    boja: string;
    namena: string;
    opisProizvoda?: string;
    slika: string;
    kolicinaNaStanju: number;
    kategorijaId: number;

    constructor (proizvodId: number, sifraProizvoda: string, nazivProizvoda: string, cena: number, nazivProizvodjaca: string,
        boja: string, namena: string, opisProizvoda: string, slika: string, kolicinaNaStanju: number, kategorijaId: number) {
            this.proizvodId = proizvodId;
            this.sifraProizvoda = sifraProizvoda;
            this.nazivProizvoda = nazivProizvoda;
            this.cena = cena;
            this.nazivProizvodjaca = nazivProizvodjaca;
            this.boja = boja;
            this.namena = namena;
            this.opisProizvoda = opisProizvoda;
            this.slika = slika;
            this.kolicinaNaStanju = kolicinaNaStanju;
            this.kategorijaId = kategorijaId;
        }
}

export default ProizvodModel;