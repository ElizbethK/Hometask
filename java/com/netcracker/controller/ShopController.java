package com.netcracker.controller;

import com.netcracker.entity.Purchase;
import com.netcracker.entity.Shop;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.ShopService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    //CREATE
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(@RequestBody Shop shop){
        shopService.create(shop);
    }


    //READ
    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get")
    public List<Shop> getAll(){
        return shopService.findAll();
    }


    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get/{id}")
    public Shop getById(@PathVariable(value = "id") int id){
        return shopService.findById(id);
    }

    //UPDATE
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> update(@PathVariable(value = "id") int id, @RequestBody Shop shop) throws ResourceNotFoundException {
        Optional<Shop> optionalShop = Optional.ofNullable(shopService.findById(id));
        optionalShop.orElseThrow(() ->
                new ResourceNotFoundException("Shop with id " + id + " is not found"));
        shopService.updateFull(id, shop);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id " + id + " has been updated"));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> patch(@PathVariable (value = "id") Integer id, @RequestBody Shop shop) throws ResourceNotFoundException {
        Optional<Shop> optionalShop = Optional.ofNullable(shopService.findById(id));
        optionalShop.orElseThrow(() ->
                new ResourceNotFoundException("Shop with id " + id + " is not found"));
        shopService.updatePart(id, shop);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id " + id + " has been updated"));
    }

    //DELETE
    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Optional<Shop> optionalShop = Optional.ofNullable(shopService.findById(id));
        optionalShop.orElseThrow(() ->
                new ResourceNotFoundException("Shop with id " + id + " is not found"));
        shopService.deleteById(id);
        return ResponseEntity.ok(new DeleteResponse("Shop with id " + id + " has been deleted"));
    }

    //-----------------------//

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/get-shop/{district}")
    public List<String> getShopsByDistrict(@PathVariable String[] district){
        return shopService.getShopsByDistrict(district);
    }


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("between/{from}/{to}/{district}")
    public List<Object> findShopsDiscountsFromToAndExcludeDistrict(@PathVariable short discountFrom,
                                         @PathVariable short discountTo, @PathVariable String excludedDistrict){
        return shopService.findShopsDiscountsFromToAndExcludeDistrict(discountFrom, discountTo, excludedDistrict);
    }

}
