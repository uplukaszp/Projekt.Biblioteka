package project.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.gui.dialogs.AuthorDialog;
import project.gui.dialogs.PublisherDialog;
import project.gui.dialogs.ReaderDialog;
import project.model.Book;
import project.model.Publisher;
import project.model.Reader;
import project.repositories.BookRepository;
import project.repositories.PublisherRepository;
import project.repositories.ReaderRepository;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		ReaderRepository repo=context.getBean(ReaderRepository.class);
		Reader p = repo.getById(31);
		
		ReaderDialog dialog=context.getBean(ReaderDialog.class);
		dialog.setData(p);
		dialog.setVisible(true);

	}
}