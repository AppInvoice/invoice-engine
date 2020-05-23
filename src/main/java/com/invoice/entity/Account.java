package com.invoice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_account")
public class Account {

    @Id
    @Column(name = "invoice_account_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "holder")
    private User holder;

    @ManyToOne
    @JoinColumn(name = "currency")
    private Currency currency;

    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getHolder() {
        return holder;
    }

    public Account setHolder(User holder) {
        this.holder = holder;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Account setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}
