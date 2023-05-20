import React from "react";
import ProizvodModel from "../../../models/ProizvodModel";
import { Link } from "react-router-dom";

export const ReturnBicycle: React.FC<{ product: ProizvodModel }> = (props) => {
    return (
        <div className="col-md-4">
            <div className="cardBicycle mt-3">
                <div className="product-1 align-items-center p-2 text-center">
                    <img src={props.product.slika} alt="bicikla" className="rounded" width="160"/>
                    <h5 className='mt-2'>{props.product.nazivProizvoda}</h5>

                    <div className="mt-3 info">
                        <span className="text1">Naziv proizvodjaca: {props.product.nazivProizvodjaca}</span>
                        <h6 className="text1">Namena: {props.product.namena}</h6>
                    </div>
                    <div className="cost mt-3 text-dark">
                        <span>{props.product.cena}rsd</span>
                    </div>
                </div>
                <div className="p-3 bicycle text-center text-white">
                    <Link className="text-uppercase" to={`checkout/${props.product.proizvodId}`}><i className="fa fa-shopping-cart"></i>Dodaj u korpu</Link>
                </div>
            </div>
        </div>
    );
}