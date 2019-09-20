package com.example.demo.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    private Set<Book> books = new LinkedHashSet<>();

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    private Set<Publisher> publishers = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public List<Book> getBookList() {
        return new ArrayList<>(books);
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public List<Publisher> getPublisherList() {
        return new ArrayList<>(publishers);
    }
}
