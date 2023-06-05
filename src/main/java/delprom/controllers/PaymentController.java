package delprom.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;

import delprom.services.impl.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Value("${stripe.private_key}")
    private String stripePrivateKey;
    
    @Value("${stripe.webhook_key}")
    private String stripeWebhookKey;
    
    @Autowired
    private PaymentService paymentService;


    @GetMapping("/api/get-client-secret")
    public ClientSecretResponse getClientSecret(@RequestParam long total,
                                                @RequestParam String userId) throws StripeException {

        Stripe.apiKey = stripePrivateKey;

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount(total * 100)
                .putMetadata("userId", userId)
                .setCurrency("rsd")
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(createParams);

        // Extract the client secret without the prefix
        String clientSecret = paymentIntent.getClientSecret();

        // Remove the prefix and suffix from the client secret
        clientSecret = clientSecret.substring(clientSecret.lastIndexOf("_secret_") + 8);

        return new ClientSecretResponse(clientSecret);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhookEvent(@RequestBody String payload,
                                                     @RequestHeader("Stripe-Signature") String sigHeader) {
        // Provera potpisa
        try {
            Event event = Webhook.constructEvent(
                payload,
                sigHeader,
                stripeWebhookKey // Replace with your actual webhook secret
            );
            
            // Parse the event payload into a JsonObject
            JsonParser jsonParser = new JsonParser();
            JsonObject eventData = jsonParser.parse(payload).getAsJsonObject();
            
            // Obrada webhook događaja
            if ("payment_intent.succeeded".equals(event.getType())) {
                String paymentIntentId = eventData.get("id").getAsString();
                if (paymentIntentId != null) {
                    PaymentIntent paymentIntent = paymentService.getPaymentIntentById(paymentIntentId);
                    if (paymentIntent != null) {
                        paymentService.updatePaymentStatus(paymentIntent, "Successful");
                        // Dodatna logika koju želite izvršiti kada je plaćanje uspešno
                    }
                }
            }
            
            return new ResponseEntity<>("Webhook handled successfully", HttpStatus.OK);
        } catch (SignatureVerificationException e) {
            // Neuspešna provera potpisa
            return new ResponseEntity<>("Invalid signature", HttpStatus.BAD_REQUEST);
        }
    }

    static class ClientSecretResponse {
        private String clientSecret;

        public ClientSecretResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getClientSecret() {
            return clientSecret;
        }
    }
}

