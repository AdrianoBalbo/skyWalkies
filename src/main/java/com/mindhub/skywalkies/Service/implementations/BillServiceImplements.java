package com.mindhub.skywalkies.Service.implementations;

import com.mindhub.skywalkies.Service.BillService;
import com.mindhub.skywalkies.models.Bill;
import com.mindhub.skywalkies.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillServiceImplements implements BillService {
    @Autowired
    public BillRepository billRepository;
    @Override
    public List<Bill> getAllBills(){return billRepository.findAll();}
    @Override
    public Bill getBillByid(long id){return billRepository.findById(id).get();}


    @Override
    public void deleteBill(Bill bill){
        billRepository.delete(bill);
    }
    @Override
    public void saveBill(Bill bill){billRepository.save(bill);}

    @Override
    public Bill findByTicketNumber(Integer number) {
        return billRepository.findByTicketNumber(number);
    }

}
