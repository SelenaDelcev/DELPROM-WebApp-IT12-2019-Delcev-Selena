import React from 'react';
import { Elements } from '@stripe/react-stripe-js';
import { loadStripe } from '@stripe/stripe-js';
import PaymentForm from './PaymentForm';
import { useLocation } from 'react-router-dom';

interface Props {
    accessToken: string;
}

const stripePromise = loadStripe('pk_test_51NBfk7JO6UoTvYm5xFwAh2J01khZ9K8Q0j5K2FcMF77kHRtnp3NnGw9RUQMmo6rAoPW6LOGPwkduvIX7Zgd2NMP0007uiz3hU4');

const PaymentPage: React.FC<Props> = ({ accessToken }) => {

    return (
      <div className="payment-page">
      <Elements stripe={stripePromise}>
        <div className="payment-form-container">
          <PaymentForm />
        </div>
      </Elements>
    </div>
    );
  };
  
  export default PaymentPage;
  

