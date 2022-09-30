package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.Product;

public class SelectedProductDTO {
    private String productName;
    private int quantity;

    public SelectedProductDTO() {
    }
    public SelectedProductDTO(Product product) {
        this.productName = product.getName();
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
