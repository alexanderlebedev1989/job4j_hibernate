package ru.job4j_hibernate.many_to_many.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books = new ArrayList<>();

    public static Author of(String name) {
        Author author = new Author();
        author.name = name;
        return author;
    }

    public List<Book> getBooks() {
        return books;
    }
}
