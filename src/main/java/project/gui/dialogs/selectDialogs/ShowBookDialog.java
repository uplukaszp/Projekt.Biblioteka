package project.gui.dialogs.selectDialogs;

import org.springframework.stereotype.Component;

import project.gui.dialogs.updateDialogs.AbstractDialog;
import project.gui.tablemodels.MyAbstractTableModel;
import project.model.Book;

@Component
public class ShowBookDialog extends ShowAbstractDialog<Book> {

	public ShowBookDialog(AbstractDialog<Book> dialog, MyAbstractTableModel<Book> model) {
		super(dialog, model);
		setTitle("Przegl¹daj ksi¹¿ki");	}

}
