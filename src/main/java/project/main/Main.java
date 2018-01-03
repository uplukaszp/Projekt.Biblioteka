package project.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.model.Author;
import project.model.Publisher;
import project.repositories.AuthorRepository;
import project.repositories.PublisherRepository;
import project.repositories.mappers.PublisherMapper;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		PublisherRepository repo=context.getBean(PublisherRepository.class);
		for (Publisher publisher : repo.getall()) {
			System.out.println(publisher);
		}
		Publisher byId = repo.getById(1);
		byId.setAddress("Zielona 21");
		repo.updatePublisher(byId);
		byId.setEmail("helion@gelion.pl");
		repo.addPublisher(byId);
		repo.deletePublisher(repo.getById(21));
	}
}
