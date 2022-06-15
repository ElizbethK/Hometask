package com.netcracker.repository;

import com.netcracker.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

    Books findById(int id);
    Books deleteById(int id);

    @Query(value = "select distinct book_name, book_price from books", nativeQuery = true)
    List<Books> findBooksNamesPrices();

    @Query(value = "select book_name, book_price from books where book_name LIKE %:name% OR book_price > :price", nativeQuery = true)
    List<Books> findBooksByKeyNameOrPrice(String name, int price);


}
