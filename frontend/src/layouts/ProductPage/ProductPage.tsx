import { useEffect, useState } from "react";
import ProizvodModel from "../../models/ProizvodModel";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import axios from "axios";
import { CheckoutBox } from "./CheckoutBox";
import { CartList } from "../CartPage/CartList";

interface Props {
    isLoggedIn: boolean;
    product?: ProizvodModel;
}

export const ProductPage: React.FC<Props> = ({ isLoggedIn }) => {

    const [product, setProduct] = useState<ProizvodModel>();
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    const [cart, setCart] = useState<ProizvodModel[]>([]);


    const proizvodId = (window.location.pathname).split('/')[2];

    const addToCart = (data: any) => {
        const updatedCart = [...cart, { ...data, quantity: 1 }];
        setCart(updatedCart);
    };

    useEffect(() => {
        const fetchProducts = async () => {
            const responseData = axios.get(`http://localhost:8080/api/proizvod/${proizvodId}`);

            const response = await responseData;
            setProduct(response.data);
            console.log(product);
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
        <div>
            <CartList cart={cart} key={proizvodId}></CartList>
            <div className='container d-none d-lg-block'>
                <div className='row mt-5'>
                    <div className='col-sm-2 col-md-2'>
                        <img src={product?.slika} width='225' height='300' alt='Product' />
                    </div>
                    <div className='col-4 col-md-4 infoContainer'>
                        <div className='ml-2'>
                            <p className='text-primary'>Sifra proizvoda: {product?.sifraProizvoda}</p>
                            <h2>{product?.nazivProizvoda}</h2>
                            <h5 className='text-primary'>{product?.nazivProizvodjaca}</h5>
                            <p className='lead'>{product?.opisProizvoda}</p>
                            <p>Namena: {product?.namena}</p>
                        </div>
                    </div>
                    <CheckoutBox product={product} mobile={false} isLoggedIn={isLoggedIn} addToCart={addToCart} />
                </div>
                <hr />
            </div>
            <div className='container d-lg-none mt-5'>
                <div className='d-flex justify-content-center alighn-items-center'>
                    <img src={product?.slika} width='226' height='349' alt='Product' />
                </div>
                <div className='col-4 col-md-4 container'>
                    <div className='ml-2'>
                        <p className='text-primary'>Sifra proizvoda: {product?.sifraProizvoda}</p>
                        <h2>{product?.nazivProizvoda}</h2>
                        <h5 className='text-primary'>{product?.nazivProizvodjaca}</h5>
                        <p className='lead'>{product?.opisProizvoda}</p>
                    </div>
                </div>
            </div>
        </div>

    );
}
