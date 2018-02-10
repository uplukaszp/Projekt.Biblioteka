package project.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

import org.springframework.stereotype.Component;

@Component
public abstract class MyAbstractTableModel<ModelItem> extends AbstractTableModel{
	
	public abstract void update();
	public abstract ModelItem getData(int index);
	public abstract void removeData(int index);
	public abstract void find(String text);
	
}
