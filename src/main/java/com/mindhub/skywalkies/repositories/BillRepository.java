package com.mindhub.skywalkies.repositories;

import ch.qos.logback.core.net.server.Client;
import com.mindhub.skywalkies.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill, Long> {
  Bill findByTicketNumber(Integer number);
}

