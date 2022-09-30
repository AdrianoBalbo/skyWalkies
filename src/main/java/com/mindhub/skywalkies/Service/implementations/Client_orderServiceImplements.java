package com.mindhub.skywalkies.Service.implementations;

import com.mindhub.skywalkies.Service.Client_orderService;
import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.repositories.Client_OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Client_orderServiceImplements implements Client_orderService {
    @Autowired
    public Client_OrderRepository client_orderRepository;
    @Override
    public List <Client_order> getAllClientsOrders(){return client_orderRepository.findAll();}
    @Override
    public Client_order getClientOrdersById(long id){return client_orderRepository.findById(id).get();}

    @Override
    public void saveClientOrders(Client_order client_order){client_orderRepository.save(client_order);}
}
