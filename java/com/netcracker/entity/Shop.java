package com.netcracker.entity;

import lombok.Data;


import javax.persistence.*;


@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sh_id", nullable = false)
    private int id;

    @Column(name = "sh_name", nullable = false)
    private String name;

    @Column(name = "sh_disctrict", nullable = false)
    private String district;

    @Column(name = "sh_comission", nullable = false)
    private double comission;

}
