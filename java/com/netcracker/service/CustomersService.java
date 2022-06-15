package com.netcracker.service;


import com.netcracker.entity.Customers;
import com.netcracker.repository.CustomersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    //CREATE
    public void create(Customers customers){
        customersRepository.save(customers);
    }

    //READ
    public List<Customers> findAll(){
        return customersRepository.findAll();
    }

    public Customers findById(int id){
        return customersRepository.findById(id);
    }


    //UPDATE
    //Full update - [PUT]
    public void updateFull(int id, Customers customers){
        Customers updatedCustomer = customersRepository.findById(id);
        updatedCustomer.setSurname(customers.getSurname());
        updatedCustomer.setDistrict(customers.getDistrict());
        updatedCustomer.setDiscount(customers.getDiscount());
        create(updatedCustomer);
    }

    //Partial update - [PATCH]
    public Customers updatePart(int id, Customers customers){
        Customers updatedCustomer = customersRepository.findById(id);
        if((customers.getSurname() instanceof String) && (customers.getSurname() != null)){
            updatedCustomer.setSurname(customers.getSurname());
        }
        if((customers.getDistrict() instanceof String) && (customers.getDistrict() != null)){
            updatedCustomer.setDistrict(customers.getDistrict());
        }
        if(customers.getDiscount() > 0.0){
            updatedCustomer.setDiscount(customers.getDiscount());
        }

       return customersRepository.save(updatedCustomer);
    }



    //DELETE
    public void deleteById(int id){
        customersRepository.deleteById(id);
    }

//---------------------------------//

    public List<String> getDistricts(){
        return customersRepository.getDistricts();
    }




}
