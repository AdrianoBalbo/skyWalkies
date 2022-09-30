package com.mindhub.skywalkies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    @OneToOne(mappedBy = "avatar")
    private Client client;

    private Integer head,face,body,bodyColor,shoes;

    public Avatar() {
    }

    public Avatar(Integer head, Integer face, Integer body, Integer bodyColor, Integer shoes) {
        this.head = head;
        this.face = face;
        this.body = body;
        this.bodyColor = bodyColor;
        this.shoes = shoes;

    }


    public long getId() {
        return id;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Integer bodyColor) {
        this.bodyColor = bodyColor;
    }

    public Integer getShoes() {
        return shoes;
    }

    public void setShoes(Integer shoes) {
        this.shoes = shoes;
    }
    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
