import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../redux/cart/cartTypes";
import { adjustItemQuantity, removeFromCart } from "../../redux/cart/cartReducer";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { Link, useHistory } from "react-router-dom";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Cart: React.FC = () => {
    const cartItems = useSelector((state: RootState) => state.cart.cartItems);
    const dispatch = useDispatch();
    const history = useHistory();

    const handleQuantityChange = (proizvodId: number, quantity: number) => {
        const item = cartItems.find((item) => item.proizvodId === proizvodId);

        if (item) {
            // Check if the entered quantity exceeds the stock quantity
            const adjustedQuantity = Math.min(quantity, item.kolicinaNaStanju);
            if (adjustedQuantity === quantity) {
            dispatch(adjustItemQuantity({ proizvodId, quantity: adjustedQuantity }));
            } else {
                toast.error("To je trenutna kolicina na stanju")
            }
        } 
    };

    const handleRemoveItem = (proizvodId: number) => {
        dispatch(removeFromCart({ proizvodId }));
    };

    const getCartSubTotal = () => {
        return cartItems
            .reduce((price, item) => price + item.cena * item.quantity, 0)
            .toFixed(2);
    };


    const handlePayment = () => {
        const cartSubTotal = getCartSubTotal(); // Calculate the cart subtotal
        history.push("/payment", { cartSubTotal }); // Navigate to /payment and pass cartSubTotal as state
    };

    return (
        <div className="cart-container">
            <h1>Korpa</h1>
            {cartItems.length === 0 ? (
                <p>Vasa korpa je prazna.</p>
            ) : (
                <div>
                    <div className="titles">
                        <h2 className="product-title">Proizvod</h2>
                        <h2 className="price">Cena</h2>
                        <h2 className="quantity">Kolicina</h2>
                        <h2 className="total">Ukupno</h2>
                    </div>
                    <div className="cart-items">
                        {cartItems.map((item) => (
                            <div key={item.proizvodId} className="cart-item">
                                <div className="cart-product">
                                    <img src={item.slika} alt={item.nazivProizvoda} />
                                    <div>
                                        <h3>{item.nazivProizvoda}</h3>
                                        <p>Sifra proizvoda: {item.sifraProizvoda}</p>
                                        <div className="remove-button">
                                            <FontAwesomeIcon
                                                icon={faTrash}
                                                onClick={() => handleRemoveItem(item.proizvodId)}
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div className="cart-product-price">{item.cena}rsd</div>
                                <p>
                                    <input
                                        type="number"
                                        min={1}
                                        value={item.quantity}
                                        onChange={(e) =>
                                            handleQuantityChange(item.proizvodId, parseInt(e.target.value))
                                        }
                                        style={{
                                            width: "50px", // Adjust the width as needed
                                            textAlign: "center",
                                            fontSize: "12px", // Adjust the font size as needed
                                        }}
                                    />
                                </p>
                                <div className="cart-product-total-price">
                                    {item.cena * item.quantity}rsd
                                </div>
                            </div>
                        ))}
                    </div>
                    <div className="cart-summary">
                        <div className="cart-checkout">
                            <div className="subtotal">
                                <span>Ukupno: </span>
                                <span className="amount">{getCartSubTotal()}rsd</span>
                            </div>
                            <button onClick={handlePayment}>Izvrsi kupovinu</button>
                            <div className="continue-shopping">
                                <Link to="/search">
                                    <span><i className="fas fa-arrow-left"></i> Nastavi kupovinu</span>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Cart;