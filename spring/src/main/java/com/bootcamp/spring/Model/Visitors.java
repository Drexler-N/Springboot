package com.bootcamp.spring.Model;


import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "visitors")
public class Visitors {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int VisitorID;

    @Column(name = "Firstname")
    private String firstname;

    @Column(name = "Lastname")
    private String lastname;

    @Column(name = "Datein")
    private Timestamp dateIn;

    @Column(name = "Dateout")
    private Timestamp dateOut;

    @Column(name = "idnumber")
    private String idnumber;

    @Column(name = "inside")
    private boolean inside;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenantid", referencedColumnName = "tenantid")
    private Tenants tenant;


    protected Visitors() {}

    public Visitors(String firstname, String lastname, String idnumber ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.idnumber = idnumber;


    }

    public int getId() {
        return VisitorID;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }
    public Timestamp getDateIn() {
        return dateIn;
    }

    public Timestamp getDateOut() {return dateOut;}

    public Tenants getTenant() {return tenant;}

    public String getIdnumber() {return idnumber;}

    public boolean isInside() {return inside;}

}
