package com.netcracker.controller;


import com.netcracker.entity.Books;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.BooksService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    //CREATE
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createBook(@RequestBody Books books){
      booksService.create(books);
    }

    //READ
    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get")
    public List<Books> getAllBooks(){
        return booksService.findAll();
    }


    @ResponseStatus(code = HttpStatus.FOUND)
    @GetMapping("/get/{id}")
    public Books getBookById(@PathVariable(value = "id") int id){
          return booksService.findById(id);
    }

    //UPDATE
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> update(@PathVariable(value = "id") int id, @RequestBody Books books) throws ResourceNotFoundException {
        Optional<Books> optionalBooks = Optional.ofNullable(booksService.findById(id));
        optionalBooks.orElseThrow(() ->
                new ResourceNotFoundException("Book with id " + id + " is not found"));
        booksService.updateFull(id, books);
        return  ResponseEntity.ok(new UpdateResponse("Book with id " + id + " has been updated"));
    }


    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> patch(@PathVariable (value = "id") int id, @RequestBody Books books) throws ResourceNotFoundException {
        Optional<Books> optionalBooks = Optional.ofNullable(booksService.findById(id));
        optionalBooks.orElseThrow(() ->
                new ResourceNotFoundException("Book with id " + id + " is not found"));
        booksService.updatePart(id, books);
        return  ResponseEntity.ok(new UpdateResponse("Book with id " + id + " has been updated"));
    }


    //DELETE
    @ResponseStatus(code = HttpStatus.OK)
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteBookById(@PathVariable (value = "id") int id) throws ResourceNotFoundException {
        Optional<Books> optionalBooks = Optional.ofNullable(booksService.findById(id));
        optionalBooks.orElseThrow(() ->
                new ResourceNotFoundException("Book with id " + id + " is not found"));
        booksService.deleteById(id);
        return  ResponseEntity.ok(new DeleteResponse("Book with id " + id + " has been deleted"));
    }



//----------------------//

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/names-prices")
    public List<Books> findBooksNamesPrices() {
        return booksService.findBooksNamesPrices();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/key-name-key-price")
    public List<Books> findBooksByKeyNameOrPrice(@RequestParam String name, @RequestParam int price) {
        return booksService.findBooksByKeyNameOrPrice(name, price);
    }
}
