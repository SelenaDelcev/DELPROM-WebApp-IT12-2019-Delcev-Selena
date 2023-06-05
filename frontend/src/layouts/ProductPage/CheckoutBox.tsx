import { Link } from "react-router-dom";
import ProizvodModel from "../../models/ProizvodModel";
import { useDispatch } from "react-redux";
import { addToCart } from "../../redux/cart/cartReducer";

interface Props {
    mobile: any;
    isLoggedIn: boolean;
    product?: ProizvodModel;
}

export const CheckoutBox: React.FC<Props> = ({ isLoggedIn, mobile, product }) => {
    const dispatch = useDispatch();

    const handleAddToCart = () => {
        dispatch(addToCart(product));
    };

    return (
        <div className={mobile ? 'card d-flex mt-5' : 'card col-3 checkoutContainer d-flex mb-5'}>
            <div className="card-body checkoutContainer">
                <div className="mt-3">
                    <h4 style={{ color: "green" }}><b>{product?.cena}</b>rsd</h4>
                    <hr />
                    {product && product.kolicinaNaStanju && product.kolicinaNaStanju > 0 ?
                        <h4 className="text-success available">Dostupno</h4>
                        :
                        <h4 className="text-danger outOfStock">
                            Nemamo na stanju
                        </h4>
                    }
                </div>
                
                {!isLoggedIn ?
                    <Link type='button' className='btn buttonForSignIn' to='/login'>Prijava</Link>
                    :
                    <Link type='button' className='btn buttonForSignIn' to='/checkout/:proizvodId' onClick={handleAddToCart}>Dodaj u korpu</Link>
                }
            </div>
        </div>
    );
}
