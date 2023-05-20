import React from "react";
import ProizvodModel from "../../../models/ProizvodModel";
import { Link } from "react-router-dom";

export const SearchProduct: React.FC<{ product: ProizvodModel }> = (props) => {
    return (
        <div className='card mt-3 shadow p-3 mb-3 bg-body rounded'>
            <div className='row g-0'>
                <div className='col-md-2'>
                    <div className='d-none d-lg-block'>
                        <img src={props.product.slika} alt="proizvod" />
                    </div>
                    <div className='d-lg-none d-flex justify-content-center 
                        align-items-center'>
                        <img src={props.product.slika} alt="proizvod" />
                    </div>
                </div>
                <div className='col-md-6'>
                    <div className='card-body'>
                        <h4 className='card-title'>
                            {props.product.nazivProizvoda}
                        </h4>
                        <h5>
                            {props.product.nazivProizvodjaca}
                        </h5>
                        <p className='card-text'>
                            {props.product.opisProizvoda}
                        </p>
                    </div>
                </div>
                <div className='col-md-4 d-flex justify-content-center align-items-center'>
                    <div>
                        <h4 className='price'>
                            {props.product.cena}rsd
                        </h4>
                    </div>

                    <Link className='btn btn-md main-color text-white' to={`/checkout/${props.product.proizvodId}`}>
                        Pogledaj proizvod
                    </Link>
                </div>
            </div>
        </div>
    );
}