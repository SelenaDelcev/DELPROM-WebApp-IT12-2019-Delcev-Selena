import { Link, NavLink } from 'react-router-dom';
import image from '../../images/delprom-logo.png';
import { connect } from "react-redux";
import { RootState } from '../../redux/cart/cartTypes';
import { Product } from '../../redux/cart/cartReducer';

interface CartItem extends Product {
    quantity: number;
}

interface Props {
    isLoggedIn: boolean;
    handleLogout: () => void;
    showCartButton: boolean;
    cartItems: CartItem[];
    isAdmin: boolean;
}

const Navbar: React.FC<Props> = ({ isLoggedIn, handleLogout, showCartButton, cartItems, isAdmin }) => {
    // Calculate the total quantity in the cart
    const totalQuantity = cartItems.reduce((total, item) => total + item.quantity, 0);

    return (
        <nav className='navbar navbar-expand-lg navbar-dark main-color py-3'>
            <div className='container-fluid'>
                <div className='collapse navbar-collapse' id='navbarNavDropdown'>
                    <ul className='navbar-nav'>
                        <li className='nav-item'>
                            <NavLink href="https://bikesport.rs/" className="custom-logo-link" to='/home'>
                                <img width="220" height="40" src={image} alt="DELPROM" /></NavLink>
                        </li>
                        <li className='nav-item'>
                            <NavLink className='nav-link' to='/search'>Prodavnica</NavLink>
                        </li>
                        <li className='nav-item'>
                            <a className='nav-link' href='#'>O nama</a>
                        </li>
                        <li className='nav-item'>
                            <a className='nav-link' href='#'>Kontakt</a>
                        </li>
                    </ul>
                    <ul className='navbar-nav ms-auto'>
                        {isAdmin && (
                            <li className='nav-item'>
                                <NavLink type='button' className='btn btn-outline-light admin' to='/admin'>Admin stranica</NavLink>
                            </li>
                        )}
                        {!isLoggedIn ?
                            <li className='nav-item m-1'>
                                <Link type='button' className='btn btn-outline-light' to='/login'>Prijava</Link>
                            </li>
                            :
                            <li>
                                <Link className='btn btn-outline-light' onClick={handleLogout} to='/login'>Odjava</Link>
                            </li>
                        }
                        {showCartButton && (
                            <li className='nav-item'>
                                <a type='button' className='btn btn-outline-light cart' href='/cart'>Korpa <i className="fa fa-shopping-cart"><sup> {totalQuantity} </sup></i></a>
                            </li>
                        )}

                    </ul>
                </div>
            </div>
        </nav>
    );
}

const mapStateToProps = (state: RootState) => ({
    cartItems: state.cart.cartItems,
});

export default connect(mapStateToProps)(Navbar);