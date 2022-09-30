package com.mindhub.skywalkies.Service;

import com.mindhub.skywalkies.models.Bill;
import com.mindhub.skywalkies.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BillService {

    public List<Bill> getAllBills();

    public Bill getBillByid(long id);

    public void deleteBill(Bill bill);

    public void saveBill(Bill bill);
    public Bill findByTicketNumber(Integer number);
}
