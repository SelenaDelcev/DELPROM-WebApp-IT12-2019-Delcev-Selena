class AddProductRequest {
    sifraProizvoda: string;
    nazivProizvoda: string;
    cena: number;
    nazivProizvodjaca: string;
    boja: string;
    namena: string;
    kolicinaNaStanju: number;
    slika?: string;
    kategorijaId: number;
    opisProizvoda: string;

    constructor (sifraProizvoda: string, nazivProizvoda: string, cena: number, nazivProizvodjaca: string,
        boja: string, namena: string, kolicinaNaStanju: number, kategorijaId: number, opisProizvoda: string) {
            this.sifraProizvoda = sifraProizvoda;
            this.nazivProizvoda = nazivProizvoda;
            this.cena = cena;
            this.nazivProizvodjaca = nazivProizvodjaca;
            this.boja = boja;
            this.namena = namena;
            this.kolicinaNaStanju = kolicinaNaStanju;
            this.kategorijaId = kategorijaId;
            this.opisProizvoda = opisProizvoda;
        }
}

export default AddProductRequest;