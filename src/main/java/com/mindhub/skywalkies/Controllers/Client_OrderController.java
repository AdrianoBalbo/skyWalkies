package com.mindhub.skywalkies.Controllers;

import com.mindhub.skywalkies.Service.AvatarService;
import com.mindhub.skywalkies.Service.ClientService;
import com.mindhub.skywalkies.Service.Client_orderService;
import com.mindhub.skywalkies.dtos.ClientDTO;
import com.mindhub.skywalkies.dtos.Client_orderDTO;
import com.mindhub.skywalkies.models.Client;
import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Client_OrderController {

    @Autowired
    private AvatarService avatarService;
    @Autowired
    private Client_orderService client_orderService;
    @Autowired
    private ClientService clientService;
    @GetMapping("/clientOrder")
    public List<Client_orderDTO> getClients() {
        return client_orderService.getAllClientsOrders().stream().map(client_order -> new Client_orderDTO(client_order)).collect(Collectors.toList());
    }
    @GetMapping("/clientOrder/{id}")
    public Client_orderDTO getClientOrder(@PathVariable long id) {
        return new Client_orderDTO(client_orderService.getClientOrdersById(id));
    }
    @GetMapping("/clients/clientOrder")
    public List<Client_orderDTO> getClientOrders(){
        return client_orderService.getAllClientsOrders().stream().map(client_order -> new Client_orderDTO(client_order)).collect(Collectors.toList());
    }
}