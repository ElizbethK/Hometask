package com.netcracker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prch_id", nullable = false)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "prch_date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sh_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cust_id", nullable = false)
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id", nullable = false)
    private Books books;

    @Column(name = "prch_quantity", nullable = false)
    private int quantity;

    @Column(name = "prch_amount", nullable = false)
    private double amount;

}
