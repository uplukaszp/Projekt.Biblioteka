package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.cj.x.protobuf.Mysqlx.OkOrBuilder;

import project.gui.dialogs.updateDialogs.BookDialog;
import project.gui.tablemodels.BookTableModel;

import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class ShowBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton deleteButton;
	private BookTableModel model;
	private JButton addButton;
	private JButton editButton;
	private JButton okButton;
	
	@Autowired
	private BookDialog dialog;

	@Autowired
	public ShowBookDialog(BookTableModel m) {
		this.model=m;
		setBounds(100, 100, 491, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			table.setModel(model);
			contentPanel.add(table, BorderLayout.CENTER);
			contentPanel.add(table.getTableHeader(),BorderLayout.PAGE_START);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			{
				deleteButton = new JButton("Usu\u0144");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						model.removeBook(table.getSelectedRow());
					}
				});
				deleteButton.setEnabled(false);
			}
			
			addButton = new JButton("Dodaj");
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(true);
					model.update();
				}
			});
			
			editButton = new JButton("Edytuj");
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setBook(model.getBook(table.getSelectedRow()));
					dialog.setVisible(true);
					model.update();
				}
			});
			editButton.setEnabled(false);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(editButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(deleteButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(addButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(11))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(60)
						.addComponent(deleteButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(addButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(editButton)
						.addContainerGap(79, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent e) {
					deleteButton.setEnabled(table.getSelectedRow()!=-1);
					editButton.setEnabled(table.getSelectedRow()!=-1);
					okButton.setEnabled(table.getSelectedRow()!=-1);
					
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
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
	public void setVisible(boolean visible)
	{
		if(visible)model.update();
		super.setVisible(visible);
	}
}
