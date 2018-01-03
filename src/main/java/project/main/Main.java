package project.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.repositories.AuthorRepository;
import project.repositories.mappers.PublisherMapper;
import projekt.model.Author;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		AuthorRepository repo = context.getBean(AuthorRepository.class);
		List<Author> list = repo.getall();
		for (Author author : list) {
			System.out.println(author);
		}
		Author a=repo.getById(31);
		repo.deleteAuthor(a);
		new PublisherMapper();
	}
}
