package com.f4h.food4help.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // mi carica i dati nel db man mano che inseriti
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String name;

    private String nameCEO;

    private String email;

    private String address;

    private String password;

    private String phone;

    public User(){
    }
    public User(String name, String nameCEO, String email, String address, String password, String phone) {
        this.name = name;
        this.nameCEO = nameCEO;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCEO() {
        return nameCEO;
    }

    public void setNameCEO(String nameCEO) {
        this.nameCEO = nameCEO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
