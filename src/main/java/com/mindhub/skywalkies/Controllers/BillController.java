package com.mindhub.skywalkies.Controllers;

import com.mindhub.skywalkies.Service.*;
import com.mindhub.skywalkies.dtos.BillDTO;
import com.mindhub.skywalkies.models.Bill;
import com.mindhub.skywalkies.models.Client;
import com.mindhub.skywalkies.models.Client_order;
import com.mindhub.skywalkies.models.Product;
import com.mindhub.skywalkies.repositories.BillRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private Client_orderService client_orderService;
    @Autowired
    private PDFService pdfService;

    @GetMapping("/bills")
    public List<BillDTO> getBills() {
        return billService.getAllBills().stream().map(BillDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/bills/{id}")
    public BillDTO getbill(@PathVariable long id) {
        return new BillDTO(billService.getBillByid(id));
    }
    @PatchMapping("/bills/payment")
    public ResponseEntity<Object> payment(@RequestParam long idBill, Authentication authentication){
        Client client = clientService.findClientByEmail(authentication.getName());
        Bill bill = billService.getBillByid(idBill);
        bill.setPayed(true);


        Bill bill2 = new Bill(client,LocalDateTime.now(), false, 0);
        billService.saveBill(bill);
        billService.saveBill(bill2);


        return new ResponseEntity<>("Payed successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/bills/pdf/download")
    private ResponseEntity<Object> downloadPDF(@RequestParam long billId, Authentication authentication, HttpServletResponse response){
        Client client = clientService.findClientByEmail(authentication.getName());
        Bill bill = billService.getBillByid(billId);
    if(client == null){
        return new ResponseEntity<>("Client doesn't exist", HttpStatus.FORBIDDEN);
    }
    if(bill == null){
        return new ResponseEntity<>("Bill doesn't exist", HttpStatus.FORBIDDEN);
    }
    if (!client.getBills().contains(bill)){
        return new ResponseEntity<>("The bill doesn't belong to this client", HttpStatus.FORBIDDEN);
    }
    if(!bill.isPayed()){
        return new ResponseEntity<>("The bill in not payed yet", HttpStatus.FORBIDDEN);
    }
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SkyWalkies.pdf";
        response.setHeader(headerKey, headerValue);
        pdfService.generatePDF(response,  bill );
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }





}