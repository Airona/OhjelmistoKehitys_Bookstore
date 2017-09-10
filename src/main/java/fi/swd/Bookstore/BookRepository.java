package fi.swd.Bookstore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.swd.Bookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitle(String title);
//	List<Book> findByAuthor(String author);
//	List<Book> findByYear(String year);
//	List<Book> findByIsbn(String isbn);
}
