package project.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.gui.dialogs.AuthorDialog;
import project.gui.dialogs.PublisherDialog;
import project.model.Book;
import project.model.Publisher;
import project.repositories.BookRepository;
import project.repositories.PublisherRepository;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		PublisherRepository repo=context.getBean(PublisherRepository.class);
		Publisher p = repo.getById(31);
		
		PublisherDialog dialog=context.getBean(PublisherDialog.class);
		dialog.setData(p);
		dialog.setVisible(true);

	}
}