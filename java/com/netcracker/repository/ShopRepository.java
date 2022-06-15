package com.netcracker.repository;

import com.netcracker.entity.Shop;
import com.netcracker.service.ShopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Shop findById(int id);

    @Query(value = "select sh_name from shop where sh_district LIKE :district", nativeQuery = true)
    List<String> getShopsByDistrict(String[] district);

    @Query(value = "select distinct s.sh_id, s.sh_name, s.sh_district, s.sh_commission " +
            "from purchase p join shop s on (p.shop_id = sh_id)\n" +
            "join customers c on (p.cust_id = c.cust_id)\n" +
            "where c.cust_discount between :discountFrom " +
            "and :discountTo and s.sh_district <> :excludedDistrict", nativeQuery = true)
    List<Object> findShopsDiscontsFromToAndExcludeDistrict(short discountFrom, short discountTo, String excludedDistrict);
}
