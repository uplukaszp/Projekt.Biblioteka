package project.gui.dialogs.selectDialogs;

import org.springframework.stereotype.Component;

import project.gui.dialogs.updateDialogs.AbstractDialog;
import project.gui.tablemodels.MyAbstractTableModel;
import project.model.Publisher;

@Component
public class ShowPublisherDialog extends ShowAbstractDialog<Publisher> {

	public ShowPublisherDialog(AbstractDialog<Publisher> dialog, MyAbstractTableModel<Publisher> model) {
		super(dialog, model);
		setTitle("Przegl¹daj wydawnictwa");
		item=new Publisher();
	}
}