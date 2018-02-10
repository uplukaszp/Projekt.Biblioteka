package project.gui.dialogs.updateDialogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.components.panels.ReaderPanel;
import project.model.Book;
import project.model.Reader;
import project.repositories.ReaderRepository;

@Component
public class ReaderDialog extends AbstractDialog<Reader> {

	@Autowired
	private ReaderRepository repo;
	
	@Autowired
	public ReaderDialog(ReaderPanel panel) {
		super(panel);
		setTitle("Dodaj czytelnika");
	}



	@Override
	protected void cancelAction() {
		contentPane.setData(new Reader());
		setVisible(false);
	}

	@Override
	protected void okAction() {
		if (!verifier.areFieldsMatched())
			return;
		Reader r=(Reader) contentPane.getData();
		if (r.getId() == 0)
			repo.addReader(r);
		else
			repo.updateReader(r);
		setVisible(false);
	}
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			setData(new Reader());
			verifier.reset();
		}
		
	}
}
