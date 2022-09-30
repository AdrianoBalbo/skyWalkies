package com.mindhub.skywalkies.models;

import com.mindhub.skywalkies.dtos.AvatarDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Bill> bills = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Avatar avatar;

    private String firstName, lastName, email, password;
    private boolean isVerificated;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password, boolean verificated, Bill bill) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isVerificated = true;
        this.addBill(bill);
    }

    public Client(String firstName, String lastName, String email, String password, boolean verificated, Bill bill, Avatar avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isVerificated = true;
        this.avatar = avatar;
        this.addBill(bill);

    }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isVerificated() {
        return isVerificated;
    }

    public void setVerificated(boolean verificated) {
        isVerificated = verificated;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }
    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void addBill(Bill bill) {
        bill.setClient(this);
        bills.add(bill);
    }
}





