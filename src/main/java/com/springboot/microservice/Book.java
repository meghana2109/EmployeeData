package com.springboot.microservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    int id;
    String name;
    String authorName;
    String category;
    int numberOfPages;
}
