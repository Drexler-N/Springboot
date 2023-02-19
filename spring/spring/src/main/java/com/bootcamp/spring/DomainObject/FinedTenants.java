package com.bootcamp.spring.DomainObject;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "finedtenants")
public class FinedTenants {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int finedtenantsid;

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
        return finedtenantsid;
    }

    public Tenants getTenant(){return tenant;}

    public BigDecimal getAmount() {return Amount;}

    public void setTenant(Tenants tenant) {this.tenant = tenant;}

    public void setAmount(BigDecimal amount) {Amount = amount;}
}
