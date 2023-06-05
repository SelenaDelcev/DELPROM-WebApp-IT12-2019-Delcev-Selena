import axios from 'axios';
import { useState } from 'react';
import AddProductRequest from '../../../models/AddProductRequest';

interface Props {
    accessToken: string;
}

export const AddNewProduct: React.FC<Props> = ({ accessToken }) => {
    // New Product
    const [sifraProizvoda, setSifraProizvoda] = useState('');
    const [nazivProizvoda, setNazivProizvoda] = useState('');
    const [cena, setCena] = useState(0);
    const [nazivProizvodjaca, setNazivProizvodjaca] = useState('');
    const [boja, setBoja] = useState('');
    const [namena, setNamena] = useState('');
    const [opisProizvoda, setOpisProizvoda] = useState('');
    const [kolicinaNaStanju, setKolicinaNaStanju] = useState(0);
    const [kategorijaId, setKategorijaId] = useState(0);
    const [slika, setSlika] = useState<any>(null);

    // Displays
    const [displayWarning, setDisplayWarning] = useState(false);
    const [displaySuccess, setDisplaySuccess] = useState(false);

    function categoryField(value: number) {
        setKategorijaId(value);
    }

    async function base64ConversionForImages(e: any) {
        if (e.target.files[0]) {
            getBase64(e.target.files[0]);
        }
    }

    function getBase64(file: any) {
        let reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
            setSlika(reader.result);
        };
        reader.onerror = function (error) {
            console.log('Error', error);
        };
    }

    const submitNewProduct = async (event: React.FormEvent) => {
        event.preventDefault();

        const url = 'http://localhost:8080/api/proizvod';

        if (
            nazivProizvoda !== '' &&
            nazivProizvodjaca !== '' &&
            kategorijaId !== 0 &&
            sifraProizvoda !== '' &&
            cena > 0 &&
            kolicinaNaStanju > 0 &&
            boja !== '' &&
            namena !== ''
        ) {
            const product: AddProductRequest = new AddProductRequest(
                sifraProizvoda,
                nazivProizvoda,
                cena,
                nazivProizvodjaca,
                boja,
                namena,
                kolicinaNaStanju,
                kategorijaId,
                opisProizvoda
            );
            product.slika = slika;
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${accessToken}`, // Add the Authorization header with the access token
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(product)
            };

            console.log(JSON.stringify(product))

            const submitNewProductResponse = await fetch(url, requestOptions);
            if (!submitNewProductResponse.ok) {
                throw new Error('Something went wrong');
            }

            setNazivProizvoda('');
            setNazivProizvodjaca('');
            setSifraProizvoda('');
            setOpisProizvoda('');
            setKategorijaId(0);
            setCena(0);
            setSlika(
                'https://www.google.com/url?sa=i&url=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3AA_black_image.jpg&psig=AOvVaw1x9dSE3n4-tJF-PsoZRqs7&ust=1685043221773000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCMinl6vZjv8CFQAAAAAdAAAAABAE'
            );
            setDisplayWarning(false);
            setDisplaySuccess(true);
        } else {
            setDisplayWarning(true);
            setDisplaySuccess(false);
        }
    };

    return (
        <div className='container mt-5 mb-5'>
            {displaySuccess && (
                <div className='alert alert-success' role='alert'>
                    Proizvod je uspešno dodat
                </div>
            )}
            {displayWarning && (
                <div className='alert alert-danger' role='alert'>
                    Sva polja moraju biti popunjena
                </div>
            )}
            <div className='card'>
                <div className='card-header'>Dodaj nov proizvod</div>
                <div className='card-body'>
                    <form onSubmit={submitNewProduct}>
                        <div className='row'>
                            <div className='col-md-6 mb-3'>
                                <label className='form-label'>Naziv proizvoda</label>
                                <input
                                    type='text'
                                    className='form-control'
                                    name='title'
                                    required
                                    onChange={(e) => setNazivProizvoda(e.target.value)}
                                    value={nazivProizvoda}
                                />
                            </div>
                            <div className='col-md-3 mb-3'>
                                <label className='form-label'>Naziv proizvođača</label>
                                <input
                                    type='text'
                                    className='form-control'
                                    name='author'
                                    required
                                    onChange={(e) => setNazivProizvodjaca(e.target.value)}
                                    value={nazivProizvodjaca}
                                />
                            </div>
                            <div className='col-md-3 mb-3'>
                                <label className='form-label'>Kategorija</label>
                                <button
                                    className='form-control btn btn-secondary dropdown-toggle'
                                    type='button'
                                    id='dropdownMenuButton1'
                                    data-bs-toggle='dropdown'
                                    aria-expanded='false'
                                >
                                    {kategorijaId}
                                </button>
                                <ul
                                    id='addNewProductId'
                                    className='dropdown-menu'
                                    aria-labelledby='dropdownMenuButton1'
                                >
                                    <li>
                                        <a
                                            onClick={() => categoryField(1)}
                                            className='dropdown-item'
                                        >
                                            Bicikli
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => categoryField(2)}
                                            className='dropdown-item'
                                        >
                                            Delovi
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => categoryField(3)}
                                            className='dropdown-item'
                                        >
                                            Oprema
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => categoryField(4)}
                                            className='dropdown-item'
                                        >
                                            Ostalo
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div className='col-md-12 mb-3'>
                            <label className='form-label'>Šifra proizvoda</label>
                            <input
                                type='text'
                                className='form-control'
                                name='isbn'
                                required
                                onChange={(e) => setSifraProizvoda(e.target.value)}
                                value={sifraProizvoda}
                            />
                        </div>
                        <div className='row'>
                            <div className='col-md-4 mb-3'>
                                <label className='form-label'>Cena</label>
                                <input
                                    type='number'
                                    className='form-control'
                                    name='price'
                                    required
                                    onChange={(e) => setCena(Number(e.target.value))}
                                    value={cena}
                                />
                            </div>
                            <div className='col-md-4 mb-3'>
                                <label className='form-label'>Boja</label>
                                <input
                                    type='text'
                                    className='form-control'
                                    name='color'
                                    required
                                    onChange={(e) => setBoja(e.target.value)}
                                    value={boja}
                                />
                            </div>
                            <div className='col-md-4 mb-3'>
                                <label className='form-label'>Namena</label>
                                <input
                                    type='text'
                                    className='form-control'
                                    name='description'
                                    required
                                    onChange={(e) => setNamena(e.target.value)}
                                    value={namena}
                                />
                            </div>
                        </div>
                        <div className='col-md-12 mb-3'>
                            <label className='form-label'>Opis proizvoda</label>
                            <textarea
                                className='form-control'
                                name='description'
                                rows={3}
                                required
                                onChange={(e) => setOpisProizvoda(e.target.value)}
                                value={opisProizvoda}
                            ></textarea>
                        </div>
                        <div className='col-md-12 mb-3'>
                            <label className='form-label'>Količina na stanju</label>
                            <input
                                type='number'
                                className='form-control'
                                name='quantity'
                                required
                                onChange={(e) => setKolicinaNaStanju(Number(e.target.value))}
                                value={kolicinaNaStanju}
                            />
                        </div>
                        <div className='col-md-12 mb-3'>
                            <label className='form-label'>Slika</label>
                            <input
                                type='file'
                                className='form-control'
                                name='image'
                                accept='.jpg, .jpeg, .png'
                                onChange={base64ConversionForImages}
                            />
                        </div>
                        <div className='col-md-12 mb-3'>
                            <button type='submit' className='btn btn-primary' onClick={submitNewProduct}>
                                Dodaj proizvod
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AddNewProduct;
