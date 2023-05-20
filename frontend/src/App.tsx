import React, { useState } from 'react';
import './App.css';
import { Navbar } from './layouts/Navbar/Navbar';
import { HomePage } from './layouts/HomePage/HomePage'
import { ProductPage } from './layouts/ProductPage/ProductPage';
import { SearchProductsPage } from './layouts/SearchProductsPage/SearchProductsPage';
import { Redirect, Route, Switch } from 'react-router-dom';
import Login from './layouts/Auth/Login';

export const App = () => {

  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('accessToken') !== null);
  const showCartButton = isLoggedIn;

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div>
      <Navbar isLoggedIn={isLoggedIn} handleLogout={handleLogout} showCartButton={showCartButton}/>
      <div>
        <Switch>
          <Route path='/' exact>
            <Redirect to='/home' />
          </Route>
          <Route path='/home'>
            <HomePage />
          </Route>
          <Route path='/search'>
            <SearchProductsPage />
          </Route>
          <Route path='/checkout/:proizvodId'>
            <ProductPage isLoggedIn={isLoggedIn}/>
          </Route>
          <Route path='/login'>
            <Login handleLogin={handleLogin} />
          </Route>
        </Switch>
      </div>
    </div>
  );
}