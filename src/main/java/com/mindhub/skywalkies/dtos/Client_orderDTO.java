package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.models.Ordered_product;

import java.util.Set;
import java.util.stream.Collectors;

public class Client_orderDTO {

    private long id;
    private long billId;

    private Set<Ordered_productDTO> ordered_productDTOS;

    public Client_orderDTO() {
    }

    public Client_orderDTO(Client_order client_order) {
        this.id = client_order.getId();
        this.billId = client_order.getBillId().getId();
        this.ordered_productDTOS = client_order.getOrdered_products().stream().map(ordered_product -> new Ordered_productDTO(ordered_product)).collect(Collectors.toSet());
    }


    public long getId() {
        return id;
    }

    public long getBillId() {
        return billId;
    }

    public Set<Ordered_productDTO> getOrdered_productDTOS() {
        return ordered_productDTOS;
    }
}
