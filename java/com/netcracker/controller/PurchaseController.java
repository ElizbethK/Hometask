package com.netcracker.controller;

import com.netcracker.entity.Customers;
import com.netcracker.entity.Purchase;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.PurchaseService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;


    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    //CREATE
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(@RequestBody Purchase purchase){
        purchaseService.create(purchase);
    }


    //READ
    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get")
    public List<Purchase> getAll(){
        return purchaseService.findAll();
    }


    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get/{id}")
    public Purchase getById(@PathVariable(value = "id") int id){
        return purchaseService.findById(id);
    }

    //UPDATE
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> update(@PathVariable(value = "id") int id, @RequestBody Purchase purchase) throws ResourceNotFoundException {
        Optional<Purchase> optionalPurchase = Optional.ofNullable(purchaseService.findById(id));
        optionalPurchase.orElseThrow(() ->
                new ResourceNotFoundException("Purchase with id " + id + " is not found"));
        purchaseService.updateFull(id, purchase);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id " + id + " has been updated"));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> patch(@PathVariable (value = "id") Integer id, @RequestBody Purchase purchase) throws ResourceNotFoundException {
        Optional<Purchase> optionalPurchase = Optional.ofNullable(purchaseService.findById(id));
        optionalPurchase.orElseThrow(() ->
                new ResourceNotFoundException("Purchase with id " + id + " is not found"));
         purchaseService.updatePart(id, purchase);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id " + id + " has been updated"));
    }

    //DELETE
    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Optional<Purchase> optionalPurchase = Optional.ofNullable(purchaseService.findById(id));
        optionalPurchase.orElseThrow(() ->
                new ResourceNotFoundException("Purchase with id " + id + " is not found"));
        purchaseService.deleteById(id);
        return ResponseEntity.ok(new DeleteResponse("Purchase with id " + id + " has been deleted"));
    }



    //------------------------------//



    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/get-dates")
    public List<Date> getDates() {
        return purchaseService.getDates();
    }



    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/cust-surn-sh-name")
    public List<Object> findCustSurnAndShNameForPurchases() {
        return purchaseService.findCustSurnAndShNameForPurchases();
    }



    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/date-surn-disc-name-quant")
    public List<Object> getDateSurnmDiscountNameQuantity() {
        return purchaseService.getDateSurnmDiscountNameQuantity();
    }



    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/date-amount/{amount}")
    public List<Object> getPurchIdDateCustSurnameWhereAmountMoreThanValue(@PathVariable double amount) {
        return purchaseService.getPurchIdDateCustSurnameWhereAmountMoreThanValue(amount);
    }



    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/earlierMonths{date}")
    public List<Object> getPurchByDateAndDistinct(@PathVariable Date date) {
        return purchaseService.getPurchByDateAndDistinct(date);
    }




}
