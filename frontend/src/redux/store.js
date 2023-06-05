import { configureStore } from "@reduxjs/toolkit";
import cartReducer, { fetchProducts } from "./cart/cartReducer";

const store = configureStore({
  reducer: {
    cart: cartReducer,
  },
});

store.dispatch(fetchProducts()); // Dispatch the fetchProducts action

export default store;
