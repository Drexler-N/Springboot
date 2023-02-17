package com.bootcamp.spring.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "dbo",name = "finedtenants")
public class FinedTenants {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int finedtenantid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenantid", referencedColumnName = "tenantid")
    private Tenants tenant;

    @Column(name = "amount")
    private BigDecimal Amount;

    protected FinedTenants() {}

    public FinedTenants(BigDecimal money){
        this.Amount = money;

    }

    public int getId() {
        return finedtenantid;
    }

    public Tenants getTenant(){return tenant;}

    public BigDecimal getAmount() {return Amount;}
}
