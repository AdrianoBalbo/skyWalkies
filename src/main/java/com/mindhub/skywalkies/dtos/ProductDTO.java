package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.Ordered_product;
import com.mindhub.skywalkies.models.Product;
import com.mindhub.skywalkies.models.ShoeColors;

import java.util.List;

public class ProductDTO {
    private long id;

    private String name,type;
    private List<ShoeColors> shoeColors;

    private Boolean active;

    private List<Integer> sizes;

    private int stock;
    private double price;
    private boolean deleted;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.shoeColors = product.getShoeColors();
        this.active = product.isActive();
        this.sizes = product.getSize();
        this.stock = product.getStock();
        this.type = product.getType();
        this.price = product.getPrice();
        this.deleted = product.isDeleted();
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ShoeColors> getShoeColors() {
        return shoeColors;
    }

    public String getType() {
        return type;
    }

    public Boolean getActive() {
        return active;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

}
