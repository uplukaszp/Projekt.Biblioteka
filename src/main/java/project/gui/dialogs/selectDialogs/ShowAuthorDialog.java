package project.gui.dialogs.selectDialogs;

import org.springframework.stereotype.Component;

import project.gui.dialogs.updateDialogs.AbstractDialog;
import project.gui.tablemodels.MyAbstractTableModel;
import project.model.Author;

@Component
public class ShowAuthorDialog extends ShowAbstractDialog<Author> {

	public ShowAuthorDialog(AbstractDialog<Author> dialog, MyAbstractTableModel<Author> model) {
		super(dialog, model);
		setTitle("Przegl¹daj autorów");
	}

	
}
