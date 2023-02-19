package com.bootcamp.spring.DomainObject;

import jakarta.persistence.*;

@Entity
@Table(name = "tenants")

public class Tenants {

    /** TenantID int
     * PersonID int
     * HasVisitor boolean
     * Roomnumber String
     * */


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Primary key
    private int tenantId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personid", referencedColumnName = "personid")
    private People people;

    @Column(name= "hasVisitor")
    private boolean hasVisitor;

    @Column(name= "roomnumber")
    private String roomNumber;

    protected Tenants() {}

    public Tenants(String roomNumber, boolean hasvisitor ) {
        this.hasVisitor = hasvisitor;
        this.roomNumber = roomNumber;
    }


    public int getTenantId() {return tenantId;}

    public boolean isHasVisitor() {return hasVisitor;}

    public String getRoomNumber() {return roomNumber;}

    public People getPeople() {
        return people;
    }

    public void setHasVisitor(boolean hasVisitor) {this.hasVisitor = hasVisitor;}

    public void setRoomNumber(String roomNumber) {this.roomNumber = roomNumber;}
}

