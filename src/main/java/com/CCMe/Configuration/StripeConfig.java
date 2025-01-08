package com.CCMe.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.stripe.Stripe;

@Configuration
public class StripeConfig {
    
    @Value("${stripe.secret-key}")
    private String secretKey;

    public StripeConfig() {
        Stripe.apiKey = "sk_test_51QPW8dC7S250F4rUpY1gG7brJYADf71cqqstF0lv0dM8YaZ8BTSOPq7bVC9HVKeOZXnco8pgvfWGlHfcFQ3x1DCZ00fbADlF0G";
    }
}
