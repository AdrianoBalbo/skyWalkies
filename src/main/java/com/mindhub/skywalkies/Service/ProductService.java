package com.mindhub.skywalkies.Service;

import com.mindhub.skywalkies.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(long id);


    public void saveProduct(Product product);
    public Product findByName(String name);

    void delete(Product product);
}
