package com.bootcamp.spring.DomainObject;

import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class People {

    /**
     * PersonID int
     * FirstName string
     * LastName string
     * idNumber String
     * typeid int
     */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int personid;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "idnumber")
    private String idnumber;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeid", referencedColumnName = "typeid")
    private Types type;



    protected People(){}

    public People(String firstname, String lastname, String idnumber){
        this.firstname = firstname;
        this.lastname = lastname;
        this.idnumber = idnumber;
    }

    public int getPersonid() {
        return personid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public Types getType() {
        return type;
    }
}
