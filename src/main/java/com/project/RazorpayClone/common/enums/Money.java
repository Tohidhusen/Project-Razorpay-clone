package com.project.RazorpayClone.common.enums;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Embeddable//used to add this class as a field in another entity class
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Money {


    private  Integer amountUnit;
    private String currency;



    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add Money with different currencies");
        }
        return new Money(this.amountUnit + other.amountUnit, this.currency);
    }
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract Money with different currencies");
        }
        return new Money(this.amountUnit - other.amountUnit, this.currency);
    }
    public   Money of(int amountUnit, String currency) {
        return new Money(amountUnit, currency);
    }
    public static Money inr(int amountUnit){
        return new Money(amountUnit, "INR");
    }
}
