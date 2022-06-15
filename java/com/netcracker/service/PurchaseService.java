package com.netcracker.service;


import com.netcracker.entity.Books;
import com.netcracker.entity.Customers;
import com.netcracker.entity.Purchase;
import com.netcracker.entity.Shop;
import com.netcracker.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    //CREATE
    public void create(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    //READ
    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Purchase findById(int id){
        return purchaseRepository.findById(id);
    }


    //UPDATE
    //Full update - [PUT]
    public void updateFull(int id, Purchase purchase){
        Purchase updatedPurchase = purchaseRepository.findById(id);
        updatedPurchase.setDate(purchase.getDate());
        updatedPurchase.setQuantity(purchase.getQuantity());
        updatedPurchase.setAmount(purchase.getAmount());
        updatedPurchase.setShop(purchase.getShop());
        updatedPurchase.setCustomers(purchase.getCustomers());
        updatedPurchase.setBooks(purchase.getBooks());
        create(updatedPurchase);
    }

    //Partial update - [PATCH]
    public Purchase updatePart(int id, Purchase purchase){
        Purchase updatedPurchase = purchaseRepository.findById(id);
        if((purchase.getDate() instanceof Date) && (purchase.getDate() != null)){
            updatedPurchase.setDate(purchase.getDate());
        }
        if(purchase.getQuantity() > 0){
            updatedPurchase.setQuantity(purchase.getQuantity());
        }
        if(purchase.getAmount() > 0.0){
            updatedPurchase.setAmount(purchase.getAmount());
        }
        if((purchase.getBooks() instanceof Books) && (purchase.getBooks() != null)){
            updatedPurchase.setBooks(purchase.getBooks());
        }
        if((purchase.getCustomers() instanceof Customers) && (purchase.getCustomers() != null)){
            updatedPurchase.setCustomers(purchase.getCustomers());
        }
        if((purchase.getShop() instanceof Shop) && (purchase.getShop() != null)){
            updatedPurchase.setShop(purchase.getShop());
        }
        return purchaseRepository.save(updatedPurchase);
    }



    //DELETE
    public void deleteById(int id){
        purchaseRepository.deleteById(id);
    }

//----------------------------------//

    public List<Date> getDates(){
        return purchaseRepository.getDates();
    }

    public List<Object> findCustSurnAndShNameForPurchases(){
        return purchaseRepository.findCustSurnAndShNameForPurchases();
    }

    public List<Object> getDateSurnmDiscountNameQuantity(){
        return purchaseRepository.getDateSurnmDiscountNameQuantity();
    }

    public List<Object> getPurchIdDateCustSurnameWhereAmountMoreThanValue(double amount){
        return purchaseRepository.getPurchIdDateCustSurnameWhereAmountMoreThanValue(amount);
    }

    public List<Object> getPurchByDateAndDistinct(Date date){
        return purchaseRepository.getPurchByDateAndDistinct(date);
    }

}
