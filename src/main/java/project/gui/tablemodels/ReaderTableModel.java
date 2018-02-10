package project.gui.tablemodels;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Reader;
import project.repositories.ReaderRepository;

@Component
public class ReaderTableModel extends MyAbstractTableModel<Reader> {

	private ReaderRepository repo;
	private List<Reader> list;

	@Autowired
	public ReaderTableModel(ReaderRepository repo) {
		super();
		this.repo=repo;
		update();
	}
	public int getRowCount() {
		return list.size();

	}

	public int getColumnCount() {
		return 6;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getForname();
		case 1:
			return list.get(rowIndex).getSurname();
		case 2:
			return list.get(rowIndex).getAddress();
		case 3:
			return list.get(rowIndex).getCity();
		case 4:
			return list.get(rowIndex).getZipCode();
		case 5:
			return list.get(rowIndex).getEmail();
		default:
			return null;
		}
	}

	public void update() {
		list = repo.getAll();
		fireTableDataChanged();
	}

	public String getColumnName(int column) {

		switch (column) {
		case 0:
			return "Imiê";
		case 1:
			return "Nazwisko";
		case 2:
			return "Adres";
		case 3:
			return "Miejscowoœæ";
		case 4:
			return "Kod pocztowy";
		case 5:
			return "Email";
		default:
			return "";

		}
	}
	public Reader getData(int i)
	{
		if(i==-1)return null;
		return list.get(i);
	}
	public void removeData(int i)
	{
		repo.deleteReader(list.get(i));
		list.remove(i);
		fireTableDataChanged();
	}

	public void find(String text) {
		list=repo.find(text);
		fireTableDataChanged();
		
	}
}
