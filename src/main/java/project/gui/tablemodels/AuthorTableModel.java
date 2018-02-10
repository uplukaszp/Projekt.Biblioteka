package project.gui.tablemodels;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Author;
import project.repositories.AuthorRepository;

@Component
public class AuthorTableModel extends MyAbstractTableModel<Author>{

	private AuthorRepository repo;
	private List<Author> list;
	
	@Autowired
	public AuthorTableModel(AuthorRepository repo) {
		super();
		this.repo=repo;
		update();
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
	@Override
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


	public void find(String text) {
		list=repo.find(text);
		fireTableDataChanged();
		
	}
	@Override
	public Author getData(int index) {
		if(index==-1)return null;
		return list.get(index);
	}
	@Override
	public void removeData(int index) {

		repo.deleteAuthor(list.get(index));
		list.remove(index);
		fireTableDataChanged();
	}
}
