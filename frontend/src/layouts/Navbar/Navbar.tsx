import { Link, NavLink, useHistory } from 'react-router-dom';
import image from '../../images/delprom-logo.png';

interface Props {
    isLoggedIn: boolean;
    handleLogout: () => void;
    showCartButton: boolean;
}

export const Navbar: React.FC<Props> = ({ isLoggedIn, handleLogout, showCartButton }) => {

    const history = useHistory();

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
                        {!isLoggedIn ?
                            <li className='nav-item m-1'>
                                <Link type='button' className='btn btn-outline-light' to='/login'>Prijava</Link>
                            </li>
                            :
                            <li>
                                <button className='btn btn-outline-light' onClick={handleLogout}>Odjava</button>
                            </li>
                        }
                        {showCartButton && (
                            <li className='nav-item'>
                               <a type='button' className='btn btn-outline-light cart' href='#'>Korpa <i className="fa fa-shopping-cart"><sup> {'0'} </sup></i></a>
                            </li>
                        )}

                    </ul>
                </div>
            </div>
        </nav>
    );
}