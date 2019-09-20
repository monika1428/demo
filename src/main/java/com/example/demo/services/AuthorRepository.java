package com.example.demo.services;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{
    public Author findByBooks(Set<Book> books);

    public Author findByBooksTitle(String booktitle);
}
