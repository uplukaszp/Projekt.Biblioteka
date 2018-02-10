package project.gui.dialogs.updateDialogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.components.panels.AuthorPanel;
import project.model.Author;
import project.repositories.AuthorRepository;

@Component
public class AuthorDialog extends AbstractDialog<Author> {

	@Autowired
	AuthorRepository repo;

	@Autowired
	public AuthorDialog(AuthorPanel panel) {
		super(panel);
		setTitle("Dodaj autora");
	}

	@Override
	protected void cancelAction() {
		contentPane.setData(new Author());
		setVisible(false);
	}

	@Override
	protected void okAction() {
		if (!verifier.areFieldsMatched())
			return;
		Author a = (Author) contentPane.getData();
		if (a.getId() == 0)
			repo.addAuthor(a);
		else
			repo.updateAuthor(a);
		setVisible(false);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			setData(new Author());
			verifier.reset();
		}
	}

}
