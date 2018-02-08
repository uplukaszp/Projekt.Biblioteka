package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;
import java.awt.Dialog;
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
import project.gui.dialogs.updateDialogs.ReaderDialog;
import project.gui.tablemodels.ReaderTableModel;
import project.model.Reader;

import java.awt.GridBagLayout;
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
public class ShowReaderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton deleteButton;
	private JButton editButton;
	private JButton okButton;
	private Reader r = new Reader();
	@Autowired
	private ReaderDialog dialog;

	private ReaderTableModel model;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	
	private boolean selectMode=true;
	private JPanel panel_2;
	@Autowired
	public ShowReaderDialog(final ReaderTableModel model) {
		setModal(true);
		setTitle("Przegl¹daj czytelników");
		this.model = model;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new ToolTipTable(model);
			contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
			{
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
					lblNewLabel = new JLabel("Znajd\u017A");
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
				FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
				fl_panel_2.setAlignment(FlowLayout.RIGHT);
				buttonPane.add(panel_2, BorderLayout.SOUTH);
				{
					JButton cancelButton = new JButton("Cancel");
					panel_2.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
						}
					});
					cancelButton.setActionCommand("Cancel");
				}
				okButton = new JButton("OK");
				panel_2.add(okButton);
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						r = model.getReader(table.getSelectedRow());
						setVisible(false);
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			{
				deleteButton = new JButton("Usu\u0144");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							model.removeReader(table.getSelectedRow());
						} catch (DataIntegrityViolationException ex) {
							JOptionPane.showMessageDialog(null,
									"Nie mo¿na usun¹æ czytelnika, który dokona³ wypo¿yczenia");
						}

					}
				});
				deleteButton.setEnabled(false);
			}
			JButton addButton = new JButton("Dodaj");
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(true);
					model.update();
				}
			});
			editButton = new JButton("Edytuj");
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setData(model.getReader(table.getSelectedRow()));
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
									.addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
									.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
							.addContainerGap()));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addGap(69).addComponent(deleteButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(addButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(editButton)
							.addContainerGap(78, Short.MAX_VALUE)));
			panel.setLayout(gl_panel);
		}
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible)
			model.update();
		textField.setText("");
		panel_2.setVisible(selectMode);
		super.setVisible(visible);
	}

	public Reader getReader() {
		return r;
	}

	public void setSelectMode(boolean selectMode) {
		this.selectMode = selectMode;
	}
}
