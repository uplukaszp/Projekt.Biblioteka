package project.gui.components;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ToolTipTable extends JTable {

	public  ToolTipTable(AbstractTableModel m) {
		super();
		setModel(m);
		this.setAutoCreateRowSorter(true);
		this.setAutoResizeMode(AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
	}
	public ToolTipTable()
	{
		super();
	}
	@Override
    public String getToolTipText(MouseEvent e) {
        String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);

        try {
            tip = getValueAt(rowIndex, colIndex).toString();
        } catch (RuntimeException e1) {
            //catch null pointer exception if mouse is over an empty line
        }

        return tip;
    }
}
