package com.mindhub.skywalkies.models;

import com.mindhub.skywalkies.dtos.Ordered_productDTO;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client_order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billId")
    private Bill billId;

     @OneToMany(mappedBy = "client_order",fetch = FetchType.EAGER)
     private Set<Ordered_product> ordered_products = new HashSet<>();

    public Client_order() {
    }

    public Client_order(Ordered_product ordered_product, Bill Bill) {
        this.id = getId();
        this.billId =  getBillId();
        this.addOrder_products(ordered_product);
    }
    public Client_order(Bill Bill) {
        this.id = getId();
        this.billId =  getBillId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bill getBillId() {
        return billId;
    }

    public void setBillId(Bill billId) {
        this.billId = billId;
    }

    public Set<Ordered_product> getOrdered_products() {
        return ordered_products;
    }

    public void setOrdered_products (Set<Ordered_product> ordered_products) {
        this.ordered_products = ordered_products;
    }

    public void addOrder_products(Ordered_product ordered_product) {
        ordered_product.setClient_order(this);
        ordered_products.add(ordered_product);
    }
}