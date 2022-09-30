package com.mindhub.skywalkies.Controllers;

import com.mindhub.skywalkies.Service.AvatarService;
import com.mindhub.skywalkies.Service.ClientService;
import com.mindhub.skywalkies.Service.Client_orderService;
import com.mindhub.skywalkies.Service.Ordered_productService;
import com.mindhub.skywalkies.Utilities.AvatarUtilities;
import com.mindhub.skywalkies.dtos.AvatarDTO;
import com.mindhub.skywalkies.dtos.ClientDTO;
import com.mindhub.skywalkies.dtos.Client_orderDTO;
import com.mindhub.skywalkies.dtos.Ordered_productDTO;
import com.mindhub.skywalkies.models.Avatar;
import com.mindhub.skywalkies.models.Bill;
import com.mindhub.skywalkies.models.Client;
import com.mindhub.skywalkies.models.Ordered_product;
import com.mindhub.skywalkies.repositories.ClientRepository;
import com.mindhub.skywalkies.repositories.Client_OrderRepository;
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
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AvatarService avatarService;
    @Autowired
    private Client_orderService client_orderService;
    @Autowired
    private ClientService clientService;
    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClients (@PathVariable long id) {return  new ClientDTO(clientService.getClientById(id));}

    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return new ResponseEntity<>(
                    "Please complete all the fields.", HttpStatus.FORBIDDEN);
        }
        if (clientService.findClientByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.FORBIDDEN);
        }
        if(!confirmPassword.equals(password)){
            return new ResponseEntity<>("Passwords do not match.", HttpStatus.FORBIDDEN);
        }
        String normalizedFirstName = firstName.toUpperCase().charAt(0)+firstName.substring(1, firstName.length()).toLowerCase();
        String normalizedLastName = lastName.toUpperCase().charAt(0)+lastName.substring(1, lastName.length()).toLowerCase();
        String normalizedEmail = email.toLowerCase();
        Avatar avatar = new Avatar(AvatarUtilities.randomHeadAvatar(),AvatarUtilities.randomFaceAvatar(), AvatarUtilities.randomBodyAvatar(), AvatarUtilities.randomBodyColorAvatar(), AvatarUtilities.randomShoesAvatar());
        Client client = new Client(normalizedFirstName, normalizedLastName, normalizedEmail, passwordEncoder.encode(password), false, new Bill(), avatar);
        clientService.saveClient(client);
        avatarService.saveAvatar(avatar);
        String mailSubject = "Skywalkies mail verification";
        String mailBody= "Hey! We are so close to complete your account registration, just one more thing to do. We need you to verify your email visiting this link: https://skywalkies.herokuapp.com/web//verified.html?id="+ client.getId();
        clientService.sendVerificationMail(client.getEmail(), mailSubject, mailBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {
        return new ClientDTO(clientService.findClientByEmail(authentication.getName()));
    }
    @GetMapping("/clients/verify/{id}")
    public ResponseEntity<Object> verifyClient(@PathVariable long id){
        Client client = clientService.getClientById(id);
        client.setVerificated(true);
        clientService.saveClient(client);
        return new ResponseEntity<>("Verified!", HttpStatus.CREATED);
    }
}