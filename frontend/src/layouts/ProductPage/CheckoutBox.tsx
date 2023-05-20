import { Link, useHistory } from "react-router-dom";
import ProizvodModel from "../../models/ProizvodModel";

interface Props {
    mobile: any;
    isLoggedIn: boolean;
    product?: ProizvodModel;
    addToCart: any;
}

export const CheckoutBox: React.FC<Props> = ({ isLoggedIn, mobile, product, addToCart }) => {

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
                    <Link type='button' className='btn buttonForSignIn' to='/checkout/:proizvodId' onClick={() => addToCart(product)}>Dodaj u korpu</Link>
                }
            </div>
        </div>
    );
}
