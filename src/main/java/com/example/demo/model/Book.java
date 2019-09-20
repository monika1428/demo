package com.example.demo.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    private Set<Author> authors = new LinkedHashSet<>();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public List<Author> getAuthorList() {
        return new ArrayList<>(authors);
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public List<Publisher> getPublisherList() {
        return new ArrayList<>(publishers);
    }

}
