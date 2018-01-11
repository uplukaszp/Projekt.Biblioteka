package project.gui.tablemodels;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Author;
import project.repositories.AuthorRepository;

@Component
public class AuthorTableModel extends AbstractTableModel{

	private AuthorRepository repo;
	private List<Author> list;

	@Autowired
	public AuthorTableModel(AuthorRepository repo) {
		this.repo=repo;
		list=repo.getall();
	}
	public int getRowCount() {
		return 4;
	}

	public int getColumnCount() {
		list=repo.getall();
		return list.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getForename();
		case 1:
			return list.get(rowIndex).getSurname();
		case 2: 
			return list.get(rowIndex).getComment();
		case 3:
			return new JButton("Wybierz");
		default:
			return null;
		}
	}
	
}
