package com.bootcamp.spring.DomainObject;

import jakarta.persistence.*;


@Entity
@Table(name ="types" )
public class Types {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int typeid;

    @Column(name = "typename")
    private String typename;

    protected Types() {}

    public Types(String typename) {
        this.typename = typename;
    }

    public int getTypeid() {
        return typeid;
    }

    public String getTypename() {
        return typename;
    }


}
