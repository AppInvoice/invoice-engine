package com.invoice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalBalancePayload {

    private String name;
    private String email;
    private BigDecimal balance;

    public String getName() {
        return name;
    }

    public FinalBalancePayload setName(final String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FinalBalancePayload setEmail(final String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public FinalBalancePayload setBalance(final BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}
