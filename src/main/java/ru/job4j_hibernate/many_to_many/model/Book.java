package ru.job4j_hibernate.many_to_many.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Book of(String name) {
        Book book = new Book();
        book.name = name;
        return book;
    }
}
