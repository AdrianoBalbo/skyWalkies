package com.mindhub.skywalkies.Service.implementations;

import com.mindhub.skywalkies.Service.Ordered_productService;
import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.models.Ordered_product;
import com.mindhub.skywalkies.repositories.Ordered_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ordered_productServiceImplements implements Ordered_productService {
    @Autowired
    private Ordered_ProductRepository ordered_productRepository;
    @Override
    public List<Ordered_product> getAllOrderProducts(){return ordered_productRepository.findAll();}
    @Override
    public Ordered_product getProdutById(long id){return ordered_productRepository.findById(id).get();}
    @Override
    public void saveOrderProduct(Ordered_product ordered_product){ordered_productRepository.save(ordered_product);}

}
