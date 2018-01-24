package project.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Book;
import project.repositories.BookRepository;

@Component
public class BookTableModel extends AbstractTableModel {

	@Autowired
	BookRepository repo;
	private List<Book> list;

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return 8;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getTitle();
		case 1:
			return list.get(rowIndex).getAuthor();
		case 2:
			return list.get(rowIndex).getPublicationDate().getYear();
		case 3:
			return list.get(rowIndex).getPublisher();
		case 4:
			return list.get(rowIndex).getISBN();
		case 5:
			return list.get(rowIndex).getType();
		case 6:
			return "TAK";
		case 7:
			return list.get(rowIndex).getKeywords();
			
		default:
			return "";
			
		}
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Tytu³";
		case 1:
			return "Autor";
		case 2: 
			return "Rok wydania";
		case 3: 
			return "Wydawnictwo";
		case 4: 
			return "ISBN";
		case 5: 
			return "Gatunek";
		case 6: 
			return "Dostêpnoœæ";
		case 7: 
			return "S³owa kluczowe";
				
		default:
			return "";
		}
	}

	public void update() {
		list = repo.getAll();
	}
}
