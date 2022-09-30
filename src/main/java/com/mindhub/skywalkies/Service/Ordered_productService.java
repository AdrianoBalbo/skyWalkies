package com.mindhub.skywalkies.Service;

import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.models.Ordered_product;

import java.util.List;

public interface Ordered_productService {
    public List<Ordered_product> getAllOrderProducts();

    public Ordered_product getProdutById(long id);

    public void saveOrderProduct(Ordered_product ordered_product);


}
