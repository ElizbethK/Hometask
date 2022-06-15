package com.netcracker.repository;

import com.netcracker.entity.Books;
import com.netcracker.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {
    Customers findById(int id);

    @Query(value = "select distinct cust_district from customers", nativeQuery = true)
    List<String> getDistricts();

}
