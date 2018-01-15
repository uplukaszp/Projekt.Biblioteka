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

import project.gui.dialogs.updateDialogs.AuthorDialog;
import project.gui.tablemodels.AuthorTableModel;
import project.model.Author;
import project.repositories.AuthorRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@Component
public class ShowAuthorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	final private AuthorTableModel model;
	private JTable table;
	private Author a;
	private JButton deleteButton;

	@Autowired
	private AuthorDialog dialog;
	private JButton editButton;
	private JButton okButton;

	@Autowired
	public ShowAuthorDialog(final AuthorTableModel model) {
		setModal(true);
		this.model = model;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		contentPanel.add(table, BorderLayout.CENTER);
		contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				deleteButton.setEnabled(table.getSelectedRow() != -1);
				editButton.setEnabled(table.getSelectedRow() != -1);
				okButton.setEnabled(table.getSelectedRow()!=-1);
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						a = model.getAuthor(table.getSelectedRow());
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			{
				deleteButton = new JButton("Usu\u0144");
				deleteButton.setEnabled(false);
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						model.removeAuthor(table.getSelectedRow());
					}
				});
				deleteButton.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			JButton addButton = new JButton("Dodaj");
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(true);
					model.update();
				}
			});

			editButton = new JButton("Edytuj");
			editButton.setEnabled(false);
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setData(model.getAuthor(table.getSelectedRow()));
					dialog.setVisible(true);
					model.update();
				}
			});
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(editButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)
									.addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)
									.addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addGap(71).addComponent(deleteButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(addButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(editButton)
							.addContainerGap(76, Short.MAX_VALUE)));
			panel.setLayout(gl_panel);
		}
		
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible)
			model.update();
		super.setVisible(visible);
	}

	public Author getAuthor() {
		return a;
	}
}
