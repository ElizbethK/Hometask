package com.netcracker.service;

import com.netcracker.entity.Books;
import com.netcracker.repository.BooksRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    //CREATE
    public void create(Books books){
       booksRepository.save(books);
    }

    //READ
    public List<Books> findAll(){
        return booksRepository.findAll();
    }

    public Books findById(int id){
        return booksRepository.findById(id);
    }


    //UPDATE
    //Full update - [PUT]
    public void updateFull(int id, Books books){
        Books updatedBook = booksRepository.findById(id);
        updatedBook.setName(books.getName());
        updatedBook.setQuantity(books.getQuantity());
        updatedBook.setPrice(books.getPrice());
        updatedBook.setWarehouse(books.getWarehouse());
        booksRepository.save(updatedBook);
    }

    //Partial update - [PATCH]
    public Books updatePart(int id, Books books){
        Books updatedBook = booksRepository.findById(id);
        if((books.getName() instanceof String) && (books.getName() != null)){
                updatedBook.setName(books.getName());
            }
        if(books.getPrice() > 0.0){
            updatedBook.setPrice(books.getPrice());
        }
        if((books.getWarehouse() instanceof String) && (books.getWarehouse() != null)){
            updatedBook.setWarehouse(books.getWarehouse());
        }
        if(books.getQuantity() > 0){
            updatedBook.setQuantity(books.getQuantity());
        }
        return booksRepository.save(updatedBook);
    }

    //DELETE
    public void deleteById(int id){
       booksRepository.deleteById(id);
    }


    //-----------------------//
    public List<Books> findBooksNamesPrices(){
        return booksRepository.findBooksNamesPrices();
    }

    public List<Books> findBooksByKeyNameOrPrice(String name, int price){
        return findBooksByKeyNameOrPrice(name, price);
    }

}


