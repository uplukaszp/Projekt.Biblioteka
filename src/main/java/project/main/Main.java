package project.main;

import java.sql.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.AppConfig;
import project.model.Book;
import project.model.Publisher;
import project.repositories.BookRepository;
import project.repositories.PublisherRepository;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		BookRepository repo = context.getBean(BookRepository.class);
		Book b = repo.getBook(1);
		b.setTitle("aaabbb");
		Date d = new Date(System.currentTimeMillis());
		b.setPublicationDate(d);
		b.setAccessible(true);
		System.out.println(b);
		repo.addBook(b);
	}
}