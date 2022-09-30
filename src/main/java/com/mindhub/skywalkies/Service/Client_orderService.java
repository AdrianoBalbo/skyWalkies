package com.mindhub.skywalkies.Service;

import com.mindhub.skywalkies.models.Client_order;

import java.util.List;

public interface Client_orderService {
    public List<Client_order> getAllClientsOrders();

    public Client_order getClientOrdersById(long id);

    public void saveClientOrders(Client_order client_order);
}
