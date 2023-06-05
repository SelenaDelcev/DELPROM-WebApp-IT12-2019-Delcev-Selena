import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export interface Product {
  proizvodId: number;
    sifraProizvoda: string;
    nazivProizvoda: string;
    cena: number;
    nazivProizvodjaca: string;
    boja: string;
    namena: string;
    opisProizvoda?: string;
    slika: string;
    kolicinaNaStanju: number;
    kategorijaId: number;
}

export interface CartItem extends Product {
  quantity: number;
}

export interface CartState {
  products: Product[];
  cartItems: CartItem[];
  currentItem: Product | null;
  loading: boolean;
  error: string | null;
}

interface FetchProductsResponse {
  data: Product[];
}

const CART_STORAGE_KEY = "cartItems";

// Async thunk action to fetch data from the API
export const fetchProducts = createAsyncThunk<
  Product[],
  void,
  { rejectValue: string }
>("cart/fetchProducts", async (_, { rejectWithValue }) => {
  try {
    const response = await axios.get<FetchProductsResponse>(
      "http://localhost:8080/api/proizvod"
    );
    return response.data.data;
  } catch (error) {
    return rejectWithValue("Failed to fetch products.");
  }
});

// Load cart items from localStorage
const loadCartItemsFromLocalStorage = () => {
  try {
    const storedCartItems = localStorage.getItem(CART_STORAGE_KEY);
    if (storedCartItems) {
      return JSON.parse(storedCartItems);
    }
  } catch (error) {
    console.error("Error loading cart items from localStorage:", error);
  }
  return [];
};

// Save cart items to localStorage
const saveCartItemsToLocalStorage = (cartItems: any) => {
  try {
    const serializedCartItems = JSON.stringify(cartItems);
    localStorage.setItem(CART_STORAGE_KEY, serializedCartItems);
  } catch (error) {
    console.error("Error saving cart items to localStorage:", error);
  }
};

const cartSlice = createSlice({
  name: "cart",
  initialState: {
    products: [],
    cartItems: loadCartItemsFromLocalStorage(),
    currentItem: null,
    loading: false,
    error: null,
  } as CartState,
  reducers: {
    addToCart: (state, action) => {
      const { proizvodId } = action.payload;
      const existingItem = state.cartItems.find((item) => item.proizvodId === proizvodId);
    
      // Check if the item is already in the cart
      if (existingItem) {
        // Check if the quantity in stock is greater than the current quantity in the cart
        if (existingItem.quantity < getItemStockQuantity(existingItem)) {
          existingItem.quantity++;
          toast.success("Dodali ste proizvod u korpu");
        } else {
          // Handle the case when the quantity in stock is limited
          // You can show an error message or take appropriate action
          toast.error("To je trenutna kolicina na stanju")
        }
      } else {
        // Item not in cart, add it with a quantity of 1
        toast.success("Dodali ste proizvod u korpu");
        state.cartItems.push({ ...action.payload, quantity: 1 });
      }
      saveCartItemsToLocalStorage(state.cartItems);
    },
    removeFromCart: (state, action) => {
      const { proizvodId } = action.payload;
      state.cartItems = state.cartItems.filter((item) => item.proizvodId !== proizvodId);
      saveCartItemsToLocalStorage(state.cartItems);
    },
    adjustItemQuantity: (state, action) => {
      const { proizvodId, quantity } = action.payload;
      const item = state.cartItems.find((item) => item.proizvodId === proizvodId);
      if (item) {
        item.quantity = quantity;
      }
      saveCartItemsToLocalStorage(state.cartItems);
    },
    loadCurrentItem: (state, action) => {
      state.currentItem = action.payload;
    },
    loadCartItems: (state, action) => {
      state.cartItems = loadCartItemsFromLocalStorage(); // Load cart items from localStorage
    },
    clearCart: (state) => {
      state.cartItems = [];
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchProducts.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchProducts.fulfilled, (state, action) => {
        state.loading = false;
        state.products = action.payload;
      })
      .addCase(fetchProducts.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ?? null;
      });
  },
});

const getItemStockQuantity = (product: Product): number => {
  return product.kolicinaNaStanju;
};

export const {
  addToCart,
  removeFromCart,
  adjustItemQuantity,
  loadCurrentItem,
  loadCartItems,
  clearCart
} = cartSlice.actions;

export default cartSlice.reducer;
