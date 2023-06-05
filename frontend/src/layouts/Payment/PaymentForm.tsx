import React, { useState } from 'react';
import { CardElement, useStripe, useElements } from '@stripe/react-stripe-js';

const PaymentForm: React.FC = () => {
    const stripe = useStripe();
    const elements = useElements();
    const [error, setError] = useState<string | undefined>();
    const [isProcessing, setIsProcessing] = useState<boolean>(false);

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        setIsProcessing(true);

        if (!stripe || !elements) {
            setIsProcessing(false);
            return;
        }

        const cardElement = elements.getElement(CardElement);

        if (!cardElement) {
            setIsProcessing(false);
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/get-client-secret?total=100&&userId=1', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.json();

            const clientSecret = data.clientSecret;
            const cleanClientSecret = clientSecret.substring(clientSecret.lastIndexOf("_secret_") + 8);

            const { error, paymentIntent } = await stripe.confirmCardPayment(cleanClientSecret, {
                payment_method: {
                    card: cardElement,
                    billing_details: {

                    },
                },
            });

            setIsProcessing(false);

            if (error) {
                setError(error.message);
            } else {
                // Payment successful, call the backend endpoint to update the stripeId
                const response = await fetch('http://localhost:8080/api/update-stripe-id', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ paymentId: paymentIntent.id }),
                });

                if (response.ok) {
                    // Stripe ID updated successfully
                    console.log('Stripe ID updated');
                } else {
                    // Failed to update the Stripe ID
                    console.error('Failed to update Stripe ID');
                }
            }
        } catch (error) {
            setIsProcessing(false);
        }
    };

    return (
         <form onSubmit={handleSubmit}>
            <CardElement />
            {error && <div className="error-message">{error}</div>}
            <button type="submit" disabled={!stripe || isProcessing} className="submit-button">
                {isProcessing ? 'Sacekajte...' : 'Plati'}
            </button>
        </form>
    );
};

export default PaymentForm;
