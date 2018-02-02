package project.gui.tablemodels;

import org.springframework.stereotype.Component;

import project.repositories.LendRepository;

@Component("currentLendTableModel")
public class CurrentLendTableModel extends LendTableModel {

	public CurrentLendTableModel(LendRepository repo) {
		super(repo);
	}
	@Override
	public void update()
	{
		list = repo.getCurrent();
		fireTableDataChanged();
	}

}
