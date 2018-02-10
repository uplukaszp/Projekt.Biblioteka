package project.gui.dialogs.updateDialogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.components.panels.BookPanel;
import project.model.Book;
import project.repositories.BookRepository;

import javax.swing.JOptionPane;

@Component
public class BookDialog extends AbstractDialog<Book> {

	public BookDialog(BookPanel panel) {
		super(panel);
		setTitle("Dodaj ksi¹¿kê");
	}

	@Autowired
	BookRepository repo;
	
	
	@Override
	protected void cancelAction() {
		contentPane.setData(new Book());
		setVisible(false);
	}

	@Override
	protected void okAction() {
		if (!verifier.areFieldsMatched())
			return;
		Book b = (Book) contentPane.getData();
		if(b.getAuthor().getId()==0||b.getPublisher().getId()==0)
		{
			JOptionPane.showMessageDialog(null, "Nie uzupe³niono wszystkich danych");
			return;
		}
		if (b.getId() == 0)
			repo.addBook(b);
		else
			repo.updateBook(b);
		setVisible(false);
	}
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			setData(new Book());
			verifier.reset();
		}
	}
}
