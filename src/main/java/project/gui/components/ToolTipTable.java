package project.gui.components;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ToolTipTable extends JTable {

	public  ToolTipTable(AbstractTableModel m) {
		super();
		setModel(m);
		this.setAutoCreateRowSorter(true);
		this.setAutoResizeMode(AUTO_RESIZE_OFF);
		((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer())
	    .setHorizontalAlignment(JLabel.LEFT);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
	  @Override
      public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
          Component component = super.prepareRenderer(renderer, row, column);
          int rendererWidth = component.getPreferredSize().width;
          TableColumn tableColumn = getColumnModel().getColumn(column);
          tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
          return component;
       }
}
