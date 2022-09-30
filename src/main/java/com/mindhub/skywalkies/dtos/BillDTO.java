package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class BillDTO {

    @Autowired
    private Product product;

    private long id;


    private LocalDateTime date;
    private boolean payed;
    private double iva;
    private double total;
    private double subTotal;

    private Set<Client_orderDTO> client_orders;
    private int numberTicket;


    public BillDTO() {
    }

    public BillDTO(Bill bill) {
        this.id = bill.getId();
        this.date = bill.getDate();
        this.payed = bill.isPayed();
        this.client_orders = bill.getClient_orders().stream().map(client_order -> new Client_orderDTO(client_order)).collect(Collectors.toSet());
        this.numberTicket = bill.getTicketNumber();
        this.subTotal = bill.getSubTotal();
        this.iva = bill.getIva();
        this.total = bill.getTotal();

    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isPayed() {
        return payed;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public Set<Client_orderDTO> getClient_orders() {
        return client_orders;
    }

    public int getNumberTicket() {
        return numberTicket;
    }
}
