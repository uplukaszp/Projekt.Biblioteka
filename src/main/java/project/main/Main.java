package project.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.gui.dialogs.AuthorDialog;
import project.model.Book;
import project.repositories.BookRepository;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		// test connection
		AuthorDialog dialog=context.getBean(AuthorDialog.class);
		dialog.setVisible(true);
	}
}