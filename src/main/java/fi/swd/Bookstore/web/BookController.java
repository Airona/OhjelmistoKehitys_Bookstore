package fi.swd.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.swd.Bookstore.BookRepository;
import fi.swd.Bookstore.domain.Book;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
	public String bookStoreIndex(Model model) {
		return "/index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String booklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "/booklist";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "/addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:/list";
	}
	
	@RequestMapping (value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		repository.delete(bookId);		
		return "redirect:/list";
	}
	
	@RequestMapping (value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findOne(bookId));
		return "editbook";
	}
	
}