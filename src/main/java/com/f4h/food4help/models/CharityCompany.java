package com.f4h.food4help.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Charity")
public class CharityCompany extends User {
    public CharityCompany(long id, String name, String nameCEO, String email, String address, String password, String phone, String clientId) {
        super(id, name, nameCEO, email, address, password, phone, clientId);
    }

    public CharityCompany(){}
}
