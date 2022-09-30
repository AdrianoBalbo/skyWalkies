package com.mindhub.skywalkies.Service.implementations;

import com.mindhub.skywalkies.Service.ProductService;
import com.mindhub.skywalkies.models.Product;
import com.mindhub.skywalkies.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplements implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){return productRepository.findAll();}
    @Override
    public Product getProductById(long id){return productRepository.findById(id).get();}
    @Override
    public void saveProduct(Product product){productRepository.save(product);}
    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public void delete(Product product) {
        product.setDeleted(true);this.saveProduct(product);}





}
