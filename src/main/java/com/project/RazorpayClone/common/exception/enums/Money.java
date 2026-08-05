package com.project.RazorpayClone.common.exception.enums;

import jakarta.persistence.Embeddable;



@Embeddable//used to add this class as a field in another entity class

public class Money {
    private final int amountUnit;
    private final String currency;
    public Money(){
        this.amountUnit = 0;
        this.currency = "INR";
    }


public  Money(int amountUnit, String currency) {
        this.amountUnit = amountUnit;
        this.currency = currency;
    }

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
