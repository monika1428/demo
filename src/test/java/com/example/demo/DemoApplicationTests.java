package com.example.demo;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.services.AuthorRepository;
import com.example.demo.services.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	private Author author;
	private Book book;

	@Before
	public void setUp() {
		author = new Author();
		author.setName("Henryk Sienkiewicz");
		book = new Book();
		book.setTitle("W pustyni i w puszczy");
		book.getAuthors().add(author);
		author.getBooks().add(book);
	}

	@Test
	@Transactional
	public void testSaveAuthor() {
		Author authorPersisted = authorRepository.save(author);
		List<Long> authorIds = new ArrayList<>();
		authorIds.add(authorPersisted.getId());
		Assert.assertEquals(1, authorRepository.findAllById(authorIds).size());

	}

	@Test
	@Transactional
	public void testSaveBook() {
		Book bookPersisted = bookRepository.save(book);
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(bookPersisted.getId());
		Assert.assertEquals(1, bookRepository.findAllById(bookIds).size());
		Assert.assertEquals("W pustyni i w puszczy", bookPersisted.getTitle());

	}

	@Test
	@Transactional
	public void testBookFromAuthor() {
		Author authorPersisted = authorRepository.save(author);
		Book bookFromAuthor = authorPersisted.getBooks().stream().findFirst().orElse(null);
		Long bookId = null;
		if(!bookFromAuthor.equals(null)) {
			bookId = bookFromAuthor.getId();
		}
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(bookId);

		Assert.assertEquals("W pustyni i w puszczy", bookFromAuthor.getTitle());
	}

	@Test
	@Transactional
	public void testAuthorFromBook() {
		Book bookPersisted = bookRepository.save(book);
		Long bookId = null;
		if(!bookPersisted.equals(null)) {
			bookId = bookPersisted.getId();
		}
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(bookId);
		Author authorFromBook = bookPersisted.getAuthors().stream().findFirst().orElse(null);
		Assert.assertEquals("Henryk Sienkiewicz", authorFromBook.getName());
	}

	@Test
	@Transactional
	public void testFindByBooks() {
		Author authorPersisted = authorRepository.save(author);
		Book book = authorPersisted.getBooks().stream().findFirst().orElse(null);
		Set<Book> books = new LinkedHashSet<>();
		books.add(book);
		Assert.assertEquals(authorPersisted, authorRepository.findByBooks(books));
	}

	@Test
	@Transactional
	public void testFindByBooksTitle() {
		Author authorPersisted = authorRepository.save(author);
		Book book = authorPersisted.getBooks().stream().findFirst().orElse(null);
		Assert.assertEquals(authorPersisted, authorRepository.findByBooksTitle(book.getTitle()));
	}

}
