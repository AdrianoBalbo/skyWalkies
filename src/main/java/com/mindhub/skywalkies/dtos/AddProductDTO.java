package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.models.Ordered_product;
import com.mindhub.skywalkies.models.Product;

public class AddProductDTO {
    private long id;
    private String productName;
    private Integer size;
    private Integer quantity;

    public AddProductDTO() {
    }

    public AddProductDTO(Ordered_product ordered_product, Product product){
        this.id = product.getId();
        this.productName = product.getName();
        this.size = ordered_product.getSize();
        this.quantity = ordered_product.getQuantity();
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
