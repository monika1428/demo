package com.example.demo.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String publisherName;

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    private Set<Book> books = new LinkedHashSet<>();

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    private Set<Author> authors = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

}
