package com.mindhub.skywalkies.repositories;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mindhub.skywalkies.models.Client;
import org.springframework.stereotype.Repository;


@RepositoryRestResource
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
