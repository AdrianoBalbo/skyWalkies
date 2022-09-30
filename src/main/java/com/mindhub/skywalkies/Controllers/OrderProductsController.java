package com.mindhub.skywalkies.Controllers;

import com.mindhub.skywalkies.Service.Ordered_productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindhub.skywalkies.dtos.Ordered_productDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderProductsController {
    @Autowired
    private Ordered_productService ordered_productService;

    @GetMapping("/orderProducts")
    public List<Ordered_productDTO> getOrderProductsDTO(){
        return ordered_productService.getAllOrderProducts().stream().map(ordered_product -> new Ordered_productDTO(ordered_product)).collect(Collectors.toList());
    }
    @GetMapping("/orderProducts/{id}")
    public Ordered_productDTO getOrderProduct(@PathVariable long id){
        return new Ordered_productDTO();
    }
}
