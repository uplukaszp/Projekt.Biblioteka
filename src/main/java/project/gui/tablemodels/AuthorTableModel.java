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
	}
	public int getRowCount() {
		
		return list.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getForename();
		case 1:
			return list.get(rowIndex).getSurname();
		case 2: 
			return list.get(rowIndex).getComment();
		default:
			return null;
		}
	}
	public void update()
	{
		list=repo.getall();
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int column) {
	        
	        switch (column) {
			case 0:
				return "Imiê";
			case 1:
				return "Nazwisko";
			case 2:
				return "Komentarz";
			default:
				return "";
				
			}
	}
	public Author getAuthor(int i)
	{
		if(i==-1)return null;
		return list.get(i);
	}
	public void removeAuthor(int i)
	{
		repo.deleteAuthor(list.get(i));
		list.remove(i);
		fireTableDataChanged();
	}
	public void find(String text) {
		list=repo.find(text);
		fireTableDataChanged();
		
	}
}
