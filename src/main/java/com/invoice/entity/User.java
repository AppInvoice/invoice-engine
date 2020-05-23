package com.invoice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "invoice_user")
public class User {

    @Id
    @Column(name = "invoice_user_id")
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "holder", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
