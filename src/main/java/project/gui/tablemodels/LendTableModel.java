package project.gui.tablemodels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Lend;
import project.repositories.LendRepository;

@Component("lendTableModel")
public class LendTableModel extends AbstractTableModel {

	protected List<Lend> list;
	protected LendRepository repo;
	SimpleDateFormat f = new SimpleDateFormat("EE dd-MM-yyyy");

	@Autowired
	public LendTableModel(LendRepository repo) {
		this.repo = repo;
		update();
	}

	public int getRowCount() {

		return list.size();
	}

	public int getColumnCount() {
		return 5;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return f.format(list.get(rowIndex).getLendDate());
		case 1:
			if (list.get(rowIndex).getReturnDate() == null)
				return "Nie oddano";
			return f.format(list.get(rowIndex).getReturnDate());
		case 2:
			return list.get(rowIndex).getReader().toString();
		case 3:
			return list.get(rowIndex).getBook().getTitle();
		case 4:
			return list.get(rowIndex).getBook().getISBN();

		default:
			return null;
		}
	}

	public void update() {
		list = repo.getAll();
		fireTableDataChanged();
	}

	public void returnBook(int index) {
		repo.returnBook(list.get(index));
	}

	public Lend getLend(int index) {
		return list.get(index);
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Data wypo¿yczenia";
		case 1:
			return "Data oddania";
		case 2:
			return "Wypo¿yczaj¹cy";
		case 3:
			return "Tytu³";
		case 4:
			return "ISBN";
		default:
			return "";
		}
	}

	public float getPenalty(int selectedRow) {
		Lend l = list.get(selectedRow);
		return repo.getPenalty(l);

	}

	public void find(String text) {
		list = repo.find(text);
		fireTableDataChanged();
	}

}
