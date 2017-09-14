package fi.swd.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.swd.Bookstore.BookRepository;
import fi.swd.Bookstore.domain.Book;

@RestController
public class RestBookController {

	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> booklistJson() {
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping (value = "/book/{id}", method = RequestMethod.GET)
	public Book getBookJson(@PathVariable("id") Long bookId) {
		return repository.findOne(bookId);
	}
}
