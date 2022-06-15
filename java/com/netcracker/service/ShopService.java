package com.netcracker.service;

import com.netcracker.entity.Shop;
import com.netcracker.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    //CREATE
    public void create(Shop shop){
        shopRepository.save(shop);
    }

    //READ
    public List<Shop> findAll(){
        return shopRepository.findAll();
    }

    public Shop findById(int id){
        return shopRepository.findById(id);
    }


    //UPDATE
    //Full update - [PUT]
    public void updateFull(int id, Shop shop){
        Shop updatedShop = shopRepository.findById(id);
        updatedShop.setName(shop.getName());
        updatedShop.setDistrict(shop.getDistrict());
        updatedShop.setComission(shop.getComission());
        create(updatedShop);
    }

    //Partial update - [PATCH]
    public Shop updatePart(int id, Shop shop){
        Shop updatedShop = shopRepository.findById(id);
        if((shop.getName() instanceof String) && (shop.getName() != null)){
            updatedShop.setName(shop.getName());
        }
        if((shop.getDistrict() instanceof String) && (shop.getDistrict() != null)){
            updatedShop.setDistrict(shop.getDistrict());
        }
        if(shop.getComission() > 0.0){
            updatedShop.setComission(shop.getComission());
        }
        return shopRepository.save(updatedShop);
    }

    //DELETE
    public void deleteById(int id){
        shopRepository.deleteById(id);
    }


    //----------------------------//
    public List<String> getShopsByDistrict(String[] district){
        return shopRepository.getShopsByDistrict(district);
    }


    public List<Object> findShopsDiscountsFromToAndExcludeDistrict(short discountFrom, short discountTo,
                                                                String excludedDistrict){
        return shopRepository.findShopsDiscontsFromToAndExcludeDistrict(discountFrom,discountTo,excludedDistrict);
    }


}


