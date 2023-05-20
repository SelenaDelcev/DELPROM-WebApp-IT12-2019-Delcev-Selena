import React, { useState } from 'react';
import ProizvodModel from '../../models/ProizvodModel';

interface Props {
    cart: ProizvodModel[];
}

export const CartList: React.FC<Props> = ({ cart }) => {

    const [quantity, setQuantity] = useState<number>(1);

    return (
        <div className="cart-list">
            <h2>Korpa:</h2>
            {cart.map((item) => (
                <div key={item.proizvodId}>
                    <img src={item.slika} width={100}/>
                    <span>Naziv proizvoda: {item.nazivProizvoda}</span>
                    <button>-</button>
                    <span>{quantity}</span>
                    <button>+</button>
                    <span>Cena: {item.cena}</span>
                </div>
            ))}
        </div>
    );
};



