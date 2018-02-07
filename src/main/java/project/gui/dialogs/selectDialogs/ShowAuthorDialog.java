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

import project.gui.components.ToolTipTable;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;

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
	private JTextField textField;
	private JLabel lblZnajd;
	private JButton cancelButton;
	private JScrollPane scrollPane;

	@Autowired
	public ShowAuthorDialog(final AuthorTableModel model) {
		setModal(true);
		setTitle("Przegl�daj autor�w");
		this.model = model;
		setBounds(100, 100, 470, 312);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));



		table = new ToolTipTable(model);
		contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				deleteButton.setEnabled(table.getSelectedRow() != -1);
				editButton.setEnabled(table.getSelectedRow() != -1);
				okButton.setEnabled(table.getSelectedRow() != -1);
			}
		});
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));

			JPanel panel = new JPanel();
			buttonPane.add(panel, BorderLayout.NORTH);

			textField = new JTextField();
			textField.setColumns(10);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

			lblZnajd = new JLabel("Znajd\u017A: ");
			panel.add(lblZnajd);
			panel.add(textField);

			JButton btnWyszukaj = new JButton("Wyszukaj");
			btnWyszukaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.find(textField.getText());
				}
			});
			panel.add(btnWyszukaj);

			JPanel panel_1 = new JPanel();
			buttonPane.add(panel_1, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
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
				getRootPane().setDefaultButton(okButton);
			}
			panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel_1.add(cancelButton);
			panel_1.add(okButton);
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
