package project.gui.tablemodels;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Publisher;
import project.repositories.PublisherRepository;

@Component
public class PublisherTableModel extends MyAbstractTableModel<Publisher> {

	private PublisherRepository repo;
	private List<Publisher> list;

	@Autowired
	public PublisherTableModel(PublisherRepository repo) {
		super();
		this.repo = repo;
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
			return list.get(rowIndex).getName();
		case 1:
			return list.get(rowIndex).getAddress();
		case 2:
			return list.get(rowIndex).getCity();
		case 3:
			return list.get(rowIndex).getZipCode();
		case 4:
			return list.get(rowIndex).getPhoneNumber();
		case 5:
			return list.get(rowIndex).getEmail();
		default:
			return "";
		}
	}

	public void update() {
		list = repo.getall();
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Nazwa";
		case 1:
			return "Adres";
		case 2:
			return "Miejscowość";
		case 3:
			return "Kod pocztowy";
		case 4:
			return "Telefon";
		case 5:
			return "Email";
		default:
			return "";
		}
	}

	public Publisher getData(int selectedRow) {
		return list.get(selectedRow);

	}

	public void removeData(int selectedRow) {
		repo.deletePublisher(list.get(selectedRow));
		list.remove(selectedRow);
		fireTableDataChanged();
	}

	public void find(String text) {
		list = repo.find(text);
		fireTableDataChanged();
	}

}
