package fi.swd.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.swd.Bookstore.domain.Book;
import fi.swd.Bookstore.domain.BookRepository;
import fi.swd.Bookstore.domain.User;
import fi.swd.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
		
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
		
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, UserRepository urepository) {
		return (args) -> {
			//create users
			User user1 = new User("user", "$2a$07$fWtYtMWmjXVHByLa6SqgNeXAVgrVd6oOxDptC95J/t7nSTkxj8d7G", "user@harjoitus", "USER");
			User user2 = new User("admin", "$2a$07$C.d3uG.8KVbGIzxOOiApI.C4QI7pd1BN40ay88Yi4euw7.TCuSOYm", "admin@harjoitus", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			// save a couple of books
			repository.save(new Book("Binaari","0111","2007","0-7",7));
			repository.save(new Book("Aakkoset","Perus","2001","0-1",2));
			repository.save(new Book("Matematiikka","Kiva","2002","0-2",5));
			repository.save(new Book("Ohjelmointi","Osaaja","2003","0-3",10));
						
//			// fetch all books
//			log.info("Books found with findAll():");
//			log.info("-------------------------------");
//			for (Book book : repository.findAll()) {
//				log.info(book.toString());
//			}
//			log.info("");
//
//			// fetch an individual book by ID
//			Book book = repository.findOne(1L);
//			log.info("Book found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(book.toString());
//			log.info("");
//
//			// fetch books by title
//			log.info("Book found with findByTitle('Kijrannimi'):");
//			log.info("--------------------------------------------");
//			for (Book bauer : repository.findByTitle("Kijrannimi")) {
//				log.info(bauer.toString());
//			}
//			log.info("");
		};
	}
	
}
