package project.gui.dialogs.selectDialogs;

import org.springframework.stereotype.Component;

import project.gui.dialogs.updateDialogs.AbstractDialog;
import project.gui.tablemodels.MyAbstractTableModel;
import project.model.Reader;

@Component
public class ShowReaderDialog extends ShowAbstractDialog<Reader> {

	public ShowReaderDialog(AbstractDialog<Reader> dialog, MyAbstractTableModel<Reader> model) {
		super(dialog, model);
		setTitle("Przegl¹daj czytelników");
	}
}