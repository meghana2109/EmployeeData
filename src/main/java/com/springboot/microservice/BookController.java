package com.springboot.microservice;

import net.minidev.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
//    Book[] bookArray;
//    Book b1 = new Book();
    List<Book> books = new ArrayList<Book>();
    //{ b1, b2, b3}

    @GetMapping
    public String sampleMethod() {
        return "Hi this is book page";
    }

    @GetMapping(value = "/search")
    public Book getBookById(@RequestParam int id){
//        Book b1 = new Book(1001, "How it Ends", "Laura Weiss", "Fiction", 244);
//        Book b2 = new Book(1002, "Malgudi Days", "R K Narayan", "Fiction", 322);
        for(Book book : books) {
            if (id == book.id) {
                return book;
            }
        }
        return null;
    }

    @GetMapping(value = "/search/{name}")
    public Book getBookByName(@PathVariable @NotNull String name) {
//        Book b1 = new Book(1001, "Sita", "Amish", "Fiction", 244);
//        Book b2 = new Book(1002, "Ravan", "Amish", "Fiction", 322);
        for(Book book : books) {
            if (name.equalsIgnoreCase(book.name)) {
                return book;
            }
        }

        return null;
    }
    @GetMapping(value = "/search/all")
    public List<Book> getAllBooks(){
        return books;
    }

    @PostMapping(value = "/add")
    public String addNewBook(@RequestBody Book b1) {
        Book b = new Book(b1.id, b1.name, b1.authorName, b1.category, b1.numberOfPages);
        books.add(b);
        return "Book added successfully";
    }

    @PutMapping(value = "/update")
    public String updateBookDetails(@RequestBody Book b1) throws Exception {
       // Book b3 = new Book(1003, "Ram", "Amish", "Fiction", 244);
        for(Book book : books) {
            if (book.id == b1.id) {
                BeanUtils.copyProperties(b1, book);
                return "Book details updated successfully";
            }
        }
        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteBookById(@PathVariable int id) throws Exception {
//        Book b4 = new Book(1004, "Half GirlFriend", "Chethan Bhagat", "Fiction", 210);
//        Book b5 = new Book(1005, "Two States", "Chethan Bhagat", "Fiction", 270);
        for(Book book : books) {
            if (id == book.id) {
                books.remove(book);
                return book.name + " deleted successfully";
            }
        }
        return null;
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        JSONObject json = new JSONObject();
        headers.forEach((key, value) -> {
            //console.info(String.format("Header '%s' = %s", key, value));
            json.appendField(key, value);
            System.out.println(json);
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping(value = "/exist")
    public String checkIfBookExist(@RequestHeader int id) {
        Book b6 = new Book(1006, "The 3 mistakes of my Life", "Chetan Bhagat", "Fiction", 250);
        if (id == b6.id) {
            return b6.name + " by" + b6.authorName + " exists";
        } else {
            return "Book not found";
        }
    }

}
