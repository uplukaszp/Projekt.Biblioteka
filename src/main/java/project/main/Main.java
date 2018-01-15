package project.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.gui.dialogs.updateDialogs.AuthorDialog;
import project.gui.dialogs.updateDialogs.BookDialog;
import project.gui.dialogs.updateDialogs.PublisherDialog;
import project.gui.dialogs.updateDialogs.ReaderDialog;
import project.gui.frame.MainFrame;
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
		BookRepository repo=context.getBean(BookRepository.class);
		BookDialog dialog=context.getBean(BookDialog.class);
		dialog.setBook(repo.getBook(91));
		dialog.setVisible(true);
		//MainFrame frame = context.getBean(MainFrame.class);
		//frame.setVisible(true);

	}
}