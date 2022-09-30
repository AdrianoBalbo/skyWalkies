package com.mindhub.skywalkies.models;

import com.mindhub.skywalkies.dtos.ProductDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Ordered_product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_order_id")
    private Client_order client_order;

    private int quantity;
    private Integer size;
    private double productsAmount;
    public Ordered_product() {
    }

    public Ordered_product(Client_order client_order, int quantity,Integer size, double productsAmount ,Product product) {
        this.client_order = client_order;
        this.product = product;
        this.quantity = quantity;
        this.size = size;
        this.productsAmount = productsAmount;
    }

    public long getId() {
        return id;
    }

    public void setProducts(Product product) {
        this.product = product;
    }

    public Client_order getClient_order() {
        return client_order;
    }

    public void setClient_order(Client_order client_order) {
        this.client_order = client_order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public double getProductsAmount() {
        return productsAmount;
    }

    public void setProductsAmount(double productsAmount) {
        this.productsAmount = productsAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }



}
