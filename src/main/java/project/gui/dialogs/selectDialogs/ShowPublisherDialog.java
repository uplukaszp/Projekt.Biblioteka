package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.tablemodels.PublisherTableModel;

import javax.swing.JTable;

@Component
public class ShowPublisherDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private PublisherTableModel model;

	@Autowired
	public ShowPublisherDialog(PublisherTableModel model) {
		setModal(true);
		this.model = model;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			table.setModel(model);

			contentPanel.add(table, BorderLayout.CENTER);
			contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	@Override
	public void setVisible(boolean visible)
	{
		if(visible)model.update();
		super.setVisible(visible);
	}
}
