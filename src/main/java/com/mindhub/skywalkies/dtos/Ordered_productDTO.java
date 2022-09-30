package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.Ordered_product;
import com.mindhub.skywalkies.models.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class Ordered_productDTO {

    private long product_id, client_order_id, id;

    private int quantity;
    private Integer size;
    private double productsAmount;

    private ProductDTO productDTO;

    public Ordered_productDTO() {
    }

    public Ordered_productDTO(Ordered_product ordered_product) {
        this.id= ordered_product.getId();
        this.product_id = ordered_product.getProduct().getId();
        this.client_order_id = ordered_product.getClient_order().getId();
        this.quantity = ordered_product.getQuantity();
        this.size = ordered_product.getSize();
        this.productsAmount = (ordered_product.getProductsAmount()*ordered_product.getQuantity());
        this.productDTO = new ProductDTO(ordered_product.getProduct());
    }

    public long getProduct_id() {
        return product_id;
    }

    public long getClient_order_id() {
        return client_order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public Integer getSize() {return size;}

    public long getId() {
        return id;
    }

    public double getProductsAmount() {return productsAmount;}
}


