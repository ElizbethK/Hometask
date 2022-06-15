package com.netcracker.entity;



import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private int id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column(name = "book_price", nullable = false)
    private double price;

    @Column(name = "book_warehouse", nullable = false)
    private String warehouse;

    @Column(name = "book_quantity", nullable = false)
    private int quantity;

}
