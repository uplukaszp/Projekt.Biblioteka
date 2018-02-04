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

import project.gui.dialogs.updateDialogs.PublisherDialog;
import project.gui.tablemodels.PublisherTableModel;
import project.model.Publisher;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;

@Component
public class ShowPublisherDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private PublisherTableModel model;

	private Publisher p;
	private JButton addButton;

	@Autowired
	private PublisherDialog dialog;
	private JButton deleteButton;
	private JButton editButton;
	private JButton okButton;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblZnajd;
	private JTextField textField;
	private JButton btnWyszukaj;

	@Autowired
	public ShowPublisherDialog(final PublisherTableModel model) {
		setModal(true);
		this.model = model;
		setBounds(100, 100, 486, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			table.setModel(model);
			contentPanel.add(table, BorderLayout.CENTER);
			contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel, BorderLayout.EAST);
				{
					addButton = new JButton("Dodaj");
					addButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dialog.setVisible(true);
							model.update();
						}
					});
				}
				deleteButton = new JButton("Usu\u0144");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						model.removePublisher(table.getSelectedRow());
					}
				});
				deleteButton.setEnabled(false);
				editButton = new JButton("Edytuj");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.setData(model.getPublisher(table.getSelectedRow()));
						dialog.setVisible(true);
						model.update();
					}
				});
				editButton.setEnabled(false);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
										.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
										.addComponent(editButton, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
								.addContainerGap()));
				gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(34).addComponent(deleteButton)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(addButton)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(editButton)
								.addContainerGap(69, Short.MAX_VALUE)));
				panel.setLayout(gl_panel);
			}
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent e) {
					deleteButton.setEnabled(table.getSelectedRow() != -1);
					editButton.setEnabled(table.getSelectedRow() != -1);
					okButton.setEnabled(table.getSelectedRow() != -1);
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.setLayout(new BorderLayout(0, 0));
			}
			{
				panel_1 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				buttonPane.add(panel_1, BorderLayout.NORTH);
				{
					lblZnajd = new JLabel("Znajd\u017A: ");
					panel_1.add(lblZnajd);
				}
				{
					textField = new JTextField();
					panel_1.add(textField);
					textField.setColumns(10);
				}
				{
					btnWyszukaj = new JButton("Wyszukaj");
					btnWyszukaj.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							model.find(textField.getText());
						}
					});
					panel_1.add(btnWyszukaj);
				}
			}
			{
				panel_2 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				buttonPane.add(panel_2, BorderLayout.SOUTH);
				{
					JButton cancelButton = new JButton("Cancel");
					panel_2.add(cancelButton);
					cancelButton.setActionCommand("Cancel");
				}
				okButton = new JButton("OK");
				panel_2.add(okButton);
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						p = model.getPublisher(table.getSelectedRow());
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible)
			model.update();
		super.setVisible(visible);
	}

	public Publisher getPublisher() {

		return p;
	}
}
