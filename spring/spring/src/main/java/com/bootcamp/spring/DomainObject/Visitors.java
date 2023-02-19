package com.bootcamp.spring.DomainObject;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "visitors")
public class Visitors {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int VisitorID;

    /**
     * VisitorID int
     * tenantID int
     * personID int
     * datein timestamp
     * dateout timestamp
     * isInside bool
     */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenantid", referencedColumnName = "tenantid")
    private Tenants tenant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personid", referencedColumnName = "personid")
    private People people;

    @Column(name = "Datein")
    private Timestamp dateIn;

    @Column(name = "Dateout")
    private Timestamp dateOut;

    @Column(name = "inside")
    private boolean isinside;


    protected Visitors() {}

    public Visitors(int personid) {


    }

    public Timestamp getDateIn() {return dateIn;}

    public Timestamp getDateOut() {return dateOut;}

    public Tenants getTenant() {return tenant;}

    public boolean isInside() {return isinside;}

}
