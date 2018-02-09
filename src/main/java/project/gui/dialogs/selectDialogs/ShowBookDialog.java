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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import project.gui.components.ToolTipTable;
import project.gui.dialogs.updateDialogs.BookDialog;
import project.gui.tablemodels.BookTableModel;
import project.model.Book;

import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

@Component
public class ShowBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton deleteButton;
	private BookTableModel model;
	private JButton addButton;
	private JButton editButton;
	private JButton okButton;
	private Book b=new Book();
	@Autowired
	private BookDialog dialog;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private boolean selectMode=true;
	@Autowired
	public ShowBookDialog(BookTableModel m) {
		setModal(true);
		setTitle("Przegl�daj ksi��ki");
		this.model = m;
		setBounds(100, 100, 491, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			{
				deleteButton = new JButton("Usu\u0144");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							model.removeBook(table.getSelectedRow());
						} catch (DataIntegrityViolationException ex) {
							JOptionPane.showMessageDialog(null, "Nie mo�na usun�� ksi��ki, kt�ra zosta�a chocia� raz wypo�yczona");
						}
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
					dialog.setData(model.getBook(table.getSelectedRow()));
					dialog.setVisible(true);
					model.update();
				}
			});
			editButton.setEnabled(false);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(editButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(deleteButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(addButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(11)));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addGap(60).addComponent(deleteButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(addButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(editButton)
							.addContainerGap(79, Short.MAX_VALUE)));
			panel.setLayout(gl_panel);
			{

			}
			{
				table = new ToolTipTable(model);
				contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
				scrollPane = new JScrollPane(table);
				contentPanel.add(scrollPane, BorderLayout.CENTER);
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
					lblNewLabel = new JLabel("Znajd\u017A: ");
					panel_1.add(lblNewLabel);
				}
				{
					textField = new JTextField();
					panel_1.add(textField);
					textField.setColumns(10);
				}
				{
					btnNewButton = new JButton("Wyszukaj");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							model.find(textField.getText());
						}
					});
					panel_1.add(btnNewButton);
				}
			}
			{
				panel_2 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				buttonPane.add(panel_2, BorderLayout.SOUTH);
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
						}
					});
					panel_2.add(cancelButton);
					cancelButton.setActionCommand("Cancel");
				}
				okButton = new JButton("OK");
				panel_2.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						b = model.getBook(table.getSelectedRow());
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void setVisible(boolean visible) {
		if (visible) {
			textField.setText("");
			model.update();
			panel_2.setVisible(selectMode);
			b = new Book();
		}
		super.setVisible(visible);
	}
	public void setSelectMode(boolean selectMode) {
		this.selectMode = selectMode;
	}
	public Book getBook() {
		return b;
	}
}
