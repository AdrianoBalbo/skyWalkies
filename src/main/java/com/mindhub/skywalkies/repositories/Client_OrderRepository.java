package com.mindhub.skywalkies.repositories;

import com.mindhub.skywalkies.models.Client_order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Client_OrderRepository extends JpaRepository <Client_order, Long>{
    Client_order  findClientOrdersById (long id);
    Client_order findById(Class<Client_order> client_orderClass, long id);
}
