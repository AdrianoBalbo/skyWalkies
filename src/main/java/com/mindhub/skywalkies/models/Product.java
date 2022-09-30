package com.mindhub.skywalkies.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private Set<Ordered_product> ordered_products = new HashSet<>();


    @ElementCollection
    @Column(name = "sizes")
    private List<Integer> size;
    @ElementCollection
    @Column(name = "colors")
    private List<ShoeColors> shoeColors;

    
    private String name,type;

    private boolean active;

    private boolean deleted;
    private int stock;
    private double price;


    public Product() {
    }

    public Product(String name, List<ShoeColors> shoeColors, String type, Boolean active, List<Integer> size, int stock, double price ) {
        this.name = name;
        this.shoeColors = shoeColors;
        this.type = type;
        this.active = active;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.deleted = true;
    }

    public long getId() {
        return id;
    }

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public List<ShoeColors> getShoeColors() {
        return shoeColors;
    }

    public void setShoeColors(List<ShoeColors> shoeColors) {
        this.shoeColors = shoeColors;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Ordered_product> getOrdered_products() {
        return ordered_products;
    }

    public void setOrdered_products(Set<Ordered_product> ordered_products) {
        this.ordered_products = ordered_products;
    }

    public void add(Product product) {
    }
}
