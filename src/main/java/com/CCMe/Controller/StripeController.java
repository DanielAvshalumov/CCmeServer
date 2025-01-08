package com.CCMe.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMe.Model.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import lombok.Data;

@RestController
@RequestMapping("/api/payment")
public class StripeController {

    
    
    @PostMapping("/create")
    public String createPaymentIntent(){
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(51L)
            .setCurrency("usd")
            .build();
        
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return paymentIntent.getClientSecret();
        } catch(StripeException ex) {
            ex.getStackTrace();
            System.out.println(ex.getMessage());
            return "not found";
        }
    }
    
}

