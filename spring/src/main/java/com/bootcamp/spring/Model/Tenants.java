package com.bootcamp.spring.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenants {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int tenantid;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "roomnumber")
    private String roomnumber;

    @Column(name = "idnumber")
    private String IDnumber;

    @Column(name= "hasvisitor")
    private boolean hasvisitor;

    protected Tenants() {}

    public Tenants(String firstname, String lastname, String idnumber, String roomnumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.IDnumber = idnumber;
        this.roomnumber = roomnumber;
    }

    public long getId() {
        return tenantid;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }
    public String getRoomNumber() {
        return roomnumber;
    }

    public String getIDnumber() {return IDnumber;}

    public boolean isHasvisitor() {return hasvisitor;}

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

}

