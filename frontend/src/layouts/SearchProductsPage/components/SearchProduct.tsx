import React from "react";
import ProizvodModel from "../../../models/ProizvodModel";
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addToCart } from "../../../redux/cart/cartReducer";

interface Props {
    product: ProizvodModel;
    showCartButton: boolean;
}

const SearchProduct: React.FC<Props> = ({ product, showCartButton }) => {
    const dispatch = useDispatch();

    const handleAddToCart = () => {
        dispatch(addToCart(product));
    };
    return (
        <div className='card mt-3 shadow p-3 mb-3 bg-body rounded'>
            <div className='row g-0'>
                <div className='col-md-2'>
                    <div className='d-none d-lg-block'>
                        <img src={product.slika} alt="proizvod" />
                    </div>
                    <div className='d-lg-none d-flex justify-content-center 
                        align-items-center'>
                        <img src={product.slika} alt="proizvod" />
                    </div>
                </div>
                <div className='col-md-6'>
                    <div className='card-body'>
                        <h4 className='card-title'>
                            {product.nazivProizvoda}
                        </h4>
                        <h5>
                            {product.nazivProizvodjaca}
                        </h5>
                        <p className='card-text'>
                            {product.opisProizvoda}
                        </p>
                    </div>
                </div>
                <div className='col-md-4 d-flex justify-content-center align-items-center'>
                    <div>
                        <h4 className='price'>
                            {product.cena}rsd
                        </h4>
                    </div>
                    <div>
                        <Link className='btn buttonViewItems' to={`/checkout/${product.proizvodId}`}>
                            Pogledaj proizvod
                        </Link>
                        {showCartButton && (
                            <button className='btn buttonAddToCart' onClick={handleAddToCart}>
                                Dodaj u korpu
                            </button>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchProduct;