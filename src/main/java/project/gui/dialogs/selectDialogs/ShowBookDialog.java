package project.gui.dialogs.selectDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.dialogs.updateDialogs.AbstractDialog;
import project.gui.dialogs.updateDialogs.BookDialog;
import project.gui.tablemodels.BookTableModel;
import project.gui.tablemodels.MyAbstractTableModel;
import project.model.Book;
import project.model.BookStatus;

@Component
public class ShowBookDialog extends ShowAbstractDialog<Book> {

	@Autowired
	public ShowBookDialog(BookDialog dialog, BookTableModel model) {
		super(dialog, model);
		setTitle("Przegl¹daj ksi¹¿ki");
		btn.setVisible(true);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.withdrawn(currentRow);
			}
		});
	}
	@Override
	protected void selectionChanged()
	{
		super.selectionChanged();
		Book b=model.getData(currentRow);
		btn.setEnabled(b.getStatus()==BookStatus.available);
	}

}
