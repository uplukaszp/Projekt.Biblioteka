package project.gui.dialogs.updateDialogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.components.panels.PublisherPanel;
import project.model.Book;
import project.model.Publisher;
import project.repositories.PublisherRepository;

@Component
public class PublisherDialog extends AbstractDialog<Publisher> {

	@Autowired
	public PublisherDialog(PublisherPanel panel) {
		super(panel);
		setTitle("Dodaj wydawnictwo");
	}

	@Autowired
	private PublisherRepository repo;

	@Override
	protected void cancelAction() {
		contentPane.setData(new Publisher());
		setVisible(false);
	}

	@Override
	protected void okAction() {
		if (!verifier.areFieldsMatched())
			return;
		Publisher p = (Publisher) contentPane.getData();
		if (p.getId() == 0)
			repo.addPublisher(p);
		else
			repo.updatePublisher(p);
		setVisible(false);

	}
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			setData(new Publisher());
			verifier.reset();
		}
	}
}
