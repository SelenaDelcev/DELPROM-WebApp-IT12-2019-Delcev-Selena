import React, { useEffect, useState } from 'react';
import './App.css';
import Navbar from './layouts/Navbar/Navbar';
import { HomePage } from './layouts/HomePage/HomePage';
import { ProductPage } from './layouts/ProductPage/ProductPage';
import SearchProductsPage from './layouts/SearchProductsPage/SearchProductsPage';
import { Redirect, Route, Switch } from 'react-router-dom';
import Login from './layouts/Auth/Login';
import { Register } from './layouts/Auth/Register';
import Cart from './layouts/CartPage/Cart';
import { Admin } from './layouts/AdminPage/Admin';
import { useDispatch } from 'react-redux';
import { clearCart } from './redux/cart/cartReducer';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import PaymentPage from './layouts/Payment/PaymentPage';


export const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('accessToken') !== null);
  const [isAdmin, setIsAdmin] = useState(localStorage.getItem('isAdmin') === 'true');
  const [accessToken, setAccessToken] = useState('');

  const showCartButton = isLoggedIn;
  const dispatch = useDispatch();

  const handleLogin = (accessToken: string, isAdmin: boolean) => {
    setIsLoggedIn(true);
    setIsAdmin(isAdmin);
    setAccessToken(accessToken);
    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('isAdmin', String(isAdmin));
    // Clear the cart items from the Redux store
    dispatch(clearCart());
  };

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('isAdmin');
    setIsLoggedIn(false);
    setIsAdmin(false);
  };

  useEffect(() => {
    const storedAccessToken = localStorage.getItem('accessToken');
    if (storedAccessToken) {
      setIsLoggedIn(true);
      setAccessToken(storedAccessToken);
    }
  }, []);

  return (
    <div>
      <Navbar isLoggedIn={isLoggedIn} isAdmin={isAdmin} handleLogout={handleLogout} showCartButton={showCartButton} />
      <div>
        <Switch>
          <Route path='/' exact>
            <Redirect to='/home' />
          </Route>
          <Route path='/home'>
            <HomePage isLoggedIn={isLoggedIn}/>
          </Route>
          <Route path='/search'>
            <SearchProductsPage showCartButton={showCartButton} />
          </Route>
          <Route path='/checkout/:proizvodId'>
            <ProductPage isLoggedIn={isLoggedIn} />
          </Route>
          <Route path='/login'>
            <Login handleLogin={handleLogin} />
          </Route>
          <Route path='/register'>
            <Register />
          </Route>
          <Route path='/cart'>
            <Cart />
          </Route>
          <Route path='/admin'>
            <Admin isAdmin={isAdmin} accessToken={accessToken} />
          </Route>
          <Route path="/payment">
            <PaymentPage accessToken={accessToken} />
          </Route>
        </Switch>
      </div>
      <ToastContainer position='bottom-right' hideProgressBar theme='colored' />
    </div>
  );
};

export default App;
