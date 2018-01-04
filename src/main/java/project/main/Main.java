package project.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.AppConfig;
import project.model.Publisher;
import project.repositories.PublisherRepository;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		PublisherRepository repo=context.getBean(PublisherRepository.class);
		for (Publisher publisher : repo.getall()) {
			System.out.println(publisher);
		}
	}
}