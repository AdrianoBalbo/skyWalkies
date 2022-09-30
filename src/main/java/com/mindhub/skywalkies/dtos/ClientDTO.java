package com.mindhub.skywalkies.dtos;


import com.mindhub.skywalkies.models.Avatar;
import com.mindhub.skywalkies.models.Bill;
import com.mindhub.skywalkies.models.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long id;
    private String firstName,lastName,email;
    private boolean verificated;
    private Avatar avatar;
    private Set<BillDTO> bills;
    public ClientDTO() {
    }

    public ClientDTO (Client client) {
       this.id = client.getId();
       this.firstName = client.getFirstName();
       this.lastName = client.getLastName();
       this.email = client.getEmail();
       this.verificated = client.isVerificated();
       this.avatar = client.getAvatar();
       this.bills = client.getBills().stream().map(bill -> new BillDTO(bill)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerificated() {
        return verificated;
    }

    public Set<BillDTO> getBills() {
        return bills;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
