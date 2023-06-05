import { useEffect, useState } from "react";
import ProizvodModel from "../../models/ProizvodModel";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import axios from 'axios';
import SearchProduct from "./components/SearchProduct";
import { Pagination } from "../Utils/Pagination";
import { Link } from "react-router-dom";

interface Props {
    showCartButton: boolean;
}

const SearchProductsPage: React.FC<Props> = ({ showCartButton }) => {
    const [products, setProducts] = useState<ProizvodModel[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [productsPerPage] = useState(5);
    const [totalAmountOfProducts, setTotalAmountOfProducts] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [search, setSearch] = useState('');
    const [searchUrl, setSearchUrl] = useState('');
    const [categorySelection, setCategorySelection] = useState('Kategorija');

    useEffect(() => {
        const fetchProducts = async () => {
            const baseUrl: string = "http://localhost:8080/api/proizvod";

            let url: string = '';

            if (searchUrl === '') {
                url = `${baseUrl}?pageNo=${currentPage - 1}&pageSize=${productsPerPage}`;
            } else {
                url = `${baseUrl}${searchUrl}`;
            }

            console.log(url);

            const responseData = await axios.get(url);

            const response = responseData;
            console.log(response);
            setTotalAmountOfProducts(response.data.totalElements);
            console.log("Updated totalAmountOfProducts", response.data.totalElements);
            setTotalPages(response.data.totalPages)
            setProducts(response.data.content);
            setIsLoading(false);
        };
        fetchProducts().catch((error: any) => {
            setIsLoading(false);
            setHttpError(error.message);
        })

        window.scrollTo(0, 0);
    }, [currentPage, categorySelection, searchUrl]);

    useEffect(() => {
        console.log("Total amount", totalAmountOfProducts);
    }, [totalAmountOfProducts]);


    if (isLoading) {
        return (
            <SpinnerLoading />
        )
    }

    if (httpError) {
        return (
            <div className='container m-5'>
                <p>{httpError}</p>
            </div>
        )
    }

    const searchHandleChange = () => {
        if (search === '') {
            setSearchUrl('');
        } else {
            setSearchUrl(`/search?keywords=${search}&pageNo=0&pageSize=${productsPerPage}`);
        }
        setCategorySelection('Kategorija');
    }

    const categoryField = async (value: string) => {
        let kategorijaId = '';

        if (value.toLowerCase() === 'bicikli') {
            setCategorySelection(value);
            kategorijaId = '1';
        } else if (value.toLowerCase() === 'delovi') {
            setCategorySelection(value);
            kategorijaId = '2';
        } else if (value.toLowerCase() === 'oprema') {
            setCategorySelection(value);
            kategorijaId = '3';
        } else if (value.toLowerCase() === 'ostalo') {
            setCategorySelection(value)
            kategorijaId = '4';
        } else {
            setCategorySelection('Svi');
        }

        // Update the searchUrl based on the selected category
        if (kategorijaId) {
            setSearchUrl(`/search/${kategorijaId}`);
        } else {
            setSearchUrl(`?pageNo=0&pageSize=${productsPerPage}`);
        }
    }

    const indexOfLastProduct: number = currentPage * productsPerPage;
    const indexOfFirstProduct: number = indexOfLastProduct - productsPerPage;
    let lastItem = productsPerPage * currentPage <= totalAmountOfProducts ?
        productsPerPage * currentPage : totalAmountOfProducts;

    const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

    return (
        <div>
            <div className="container">
                <div>
                    <div className="row mt-5">
                        <div className="col-6">
                            <div className="d-flex">
                                <input className="form-control me-2" type="search"
                                    placeholder="Pretrazite.." aria-label="Search"
                                    onChange={e => setSearch(e.target.value)} />
                                <button className="btn btn-outline-success"
                                    onClick={() => searchHandleChange()}>
                                    Pretrazite
                                </button>
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="dropdown">
                                <button className="btn btn-secondary dropdown-toggle" type="button"
                                    id="dropdownCategory" data-bs-toggle="dropdown" aria-expanded="false">
                                    {categorySelection}
                                </button>
                                <ul className="dropdown-menu" aria-labelledby="dropdownCategory">
                                    <li onClick={() => categoryField('Svi')}>
                                        <a className="dropdown-item" href="#">
                                            Svi
                                        </a>
                                    </li>
                                    <li onClick={() => categoryField('Bicikli')}>
                                        <a className="dropdown-item" href="#">
                                            Bicikli
                                        </a>
                                    </li>
                                    <li onClick={() => categoryField('Delovi')}>
                                        <a className="dropdown-item" href="#">
                                            Delovi
                                        </a>
                                    </li>
                                    <li onClick={() => categoryField('Oprema')}>
                                        <a className="dropdown-item" href="#">
                                            Oprema
                                        </a>
                                    </li>
                                    <li onClick={() => categoryField('Ostalo')}>
                                        <a className="dropdown-item" href="#">
                                            Ostalo
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    {totalAmountOfProducts > 0 ?
                        <>
                            <div className="mt-3">
                                <h5>Broj rezultata: ({totalAmountOfProducts})</h5>
                            </div>
                            <p>
                                {indexOfFirstProduct + 1} do {lastItem} u {totalAmountOfProducts} stavki:
                            </p>
                            {products.map(product => (
                                <SearchProduct product={product} key={product.proizvodId} showCartButton={showCartButton} />
                            ))}
                        </>
                        :
                        <div className="m-5">
                            <h6 className="notFound">Nijedan proizvod ne odgovara izabranim kriterijumima.</h6>
                            <h3>
                                Ne mozete da pronadjete ono sto trazite?
                            </h3>
                            <Link type="button" className="btn main-color btn-md px-4 me-md-2 fw-bold text-white" to='/search'>Pogledaj ponudu</Link>
                        </div>
                    }
                    {totalPages > 1 &&
                        <Pagination currentPage={currentPage} totalPages={totalPages} paginate={paginate} />}
                </div>
            </div>
        </div >
    );
}

export default SearchProductsPage;
