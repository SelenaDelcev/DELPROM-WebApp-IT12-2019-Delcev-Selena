import { ReturnBicycle } from "./ReturnBicycle";
import { useEffect, useState } from "react";
import ProizvodModel from "../../../models/ProizvodModel";
import axios from 'axios';
import { SpinnerLoading } from "../../Utils/SpinnerLoading";
import { Link } from "react-router-dom";

export const Carousel = () => {

    const [products, setProducts] = useState<ProizvodModel[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            const responseData = axios.get("http://localhost:8080/api/proizvod?pageNo=0&pageSize=4&sortBy=nazivProizvoda");

            const response = await responseData;
            setProducts(response.data.content);
            setIsLoading(false);
        };
        fetchProducts().catch((error: any) => {
            setIsLoading(false);
            setHttpError(error.message);
        })
        fetchProducts();
    }, []);

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

    return (
        <div className='container mb-5 mt-5'>
            <div className='homepage-carousel-title'>
                <h3 className='titleAboutOffer'>NOVO U PONUDI</h3>
            </div>
            <div id='carouselExampleControls' className='carousel carousel-light slide mt-5 
            d-none d-lg-block' data-bs-interval='false'>

                {/* Desktop */}
                <div className='carousel-inner'>
                    <div className='carousel-item '>
                        <div className='row d-flex justify-content-center align-items-center'>
                            {products.slice(0, 2).map(product => (
                                <ReturnBicycle product={product} key={product.proizvodId} />
                            ))}
                        </div>
                    </div>
                    <div className='carousel-item'>
                        <div className='row d-flex justify-content-center align-items-center'>
                            {products.slice(1, 3).map(product => (
                                <ReturnBicycle product={product} key={product.proizvodId} />
                            ))}
                        </div>
                    </div>
                    <div className='carousel-item active'>
                        <div className='row d-flex justify-content-center align-items-center'>
                            {products.slice(2, 4).map(product => (
                                <ReturnBicycle product={product} key={product.proizvodId} />
                            ))}
                        </div>
                    </div>
                </div>
                <button className='carousel-control-prev' type='button'
                    data-bs-target='#carouselExampleControls' data-bs-slide='prev'>
                    <span className='carousel-control-prev-icon' aria-hidden='true'></span>
                    <span className='visually-hidden'>Previous</span>
                </button>
                <button className='carousel-control-next' type='button'
                    data-bs-target='#carouselExampleControls' data-bs-slide='next'>
                    <span className='carousel-control-next-icon' aria-hidden='true'></span>
                    <span className='visually-hidden'>Next</span>
                </button>
            </div>

            <div className='homepage-carousel-title mt-3'>
                <Link className='btn btn-outline-secondary btn-lg' to='/search'>Pogledaj ponudu</Link>
            </div>
        </div>

    );
}