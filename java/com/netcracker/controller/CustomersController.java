package com.netcracker.controller;

import com.netcracker.entity.Books;
import com.netcracker.entity.Customers;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.CustomersService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }


    //CREATE
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(@RequestBody Customers customers){
        customersService.create(customers);
    }


    //READ
    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get")
    public List<Customers> getAll(){
        return customersService.findAll();
    }


    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get/{id}")
    public Customers getById(@PathVariable(value = "id") int id){
        return customersService.findById(id);
    }

    //UPDATE
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> update(@PathVariable(value = "id") int id, @RequestBody Customers customers){
        if (customersService.findById(id) == null) {
            new ResourceNotFoundException("Customer with id " + id + " is not found");
        } else
            customersService.updateFull(id, customers);
        return ResponseEntity.ok(new UpdateResponse("Customer with id " + id + " has been updated"));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> patch(@PathVariable (value = "id") Integer id, @RequestBody Customers customers){
        if (customersService.findById(id) == null) {
            new ResourceNotFoundException("Customer with id " + id + " is not found");
        } else
            customersService.updatePart(id, customers);
        return ResponseEntity.ok(new UpdateResponse("Customer with id " + id + " has been updated"));
    }

    //DELETE
    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteById(@PathVariable(value = "id") int id){
        if (customersService.findById(id) == null) {
            new ResourceNotFoundException("Customer with id " + id + " is not found");
        } else
            customersService.deleteById(id);
        return ResponseEntity.ok(new DeleteResponse("Customer with id " + id + " has been deleted"));
    }

//-------------------------------------------//
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-districts")
    public List<String> getDistricts(){
      return customersService.getDistricts();
   }

}
