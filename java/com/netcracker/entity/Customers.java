package com.netcracker.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cust_id", nullable = false)
    private int id;

    @Column(name = "cust_surname", nullable = false)
    private String surname;

    @Column(name = "cust_district", nullable = false)
    private String district;

    @Column(name = "cust_discount", nullable = false)
    private double discount;

}
