package com.mindhub.skywalkies.repositories;

import com.mindhub.skywalkies.models.Ordered_product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Ordered_ProductRepository extends JpaRepository<Ordered_product, Long> {
}
