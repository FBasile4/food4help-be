package com.f4h.food4help.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // mi carica i dati nel db man mano che inseriti
@Table(name = "charities")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="nameCEO")
    private String nameCEO;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    //@Column(name="clientId", columnDefinition="text",  length=20485760)
    @Lob
    private String clientId;

    public User(){
    }
    public User(long id, String name, String nameCEO, String email, String address, String password, String phone, String clientId) {
        this.id = id;
        this.name = name;
        this.nameCEO = nameCEO;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.clientId = clientId;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
