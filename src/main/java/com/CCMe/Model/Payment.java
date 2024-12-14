package com.CCMe.Model;

import java.math.BigDecimal;
import java.util.Currency;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Payment {
    private Currency currency;
    private BigDecimal amountRangeStart;
    private BigDecimal amountRangeEnd;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
