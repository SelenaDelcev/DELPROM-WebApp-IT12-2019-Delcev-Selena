package delprom.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class PaymentService {

    // Stripe API secret key
	@Value("${stripe.private_key}")
    private String stripePrivateKey;

    public PaymentIntent getPaymentIntentById(String paymentIntentId) {
        Stripe.apiKey = stripePrivateKey;

        try {
            return PaymentIntent.retrieve(paymentIntentId);
        } catch (StripeException e) {
            // Handle any exceptions or errors
            e.printStackTrace();
            return null;
        }
    }

    public void updatePaymentStatus(PaymentIntent paymentIntent, String newStatus) {
        // Implement your logic to update the payment status here
        // For example, you can set the payment status in the PaymentIntent object
        paymentIntent.setStatus(newStatus);
    }
}
