package com.invoice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_currency")
public class Currency {

    @Id
    @Column(name = "invoice_currency_id")
    private Long id;

    private String iso;

    private String code;

    @OneToOne(mappedBy = "currency")
    private RatingExchange ratingExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RatingExchange getRatingExchange() {
        return ratingExchange;
    }

    public void setRatingExchange(RatingExchange ratingExchange) {
        this.ratingExchange = ratingExchange;
    }


}
