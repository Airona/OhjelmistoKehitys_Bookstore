package fi.swd.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.swd.Bookstore.domain.Book;
import fi.swd.Bookstore.domain.BookRepository;

@CrossOrigin
@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    //@RequestMapping(value = {"", "/", "/index.html", "index"}, method = RequestMethod.GET)
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
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
	
	
//Rest methods
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> booklistJson() {
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping (value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Book getBookJson(@PathVariable("id") Long bookId) {
		return repository.findOne(bookId);
	}

}