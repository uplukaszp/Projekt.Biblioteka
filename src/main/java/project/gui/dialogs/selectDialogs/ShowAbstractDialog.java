package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import project.gui.components.ToolTipTable;
import project.gui.dialogs.updateDialogs.AbstractDialog;
import project.gui.tablemodels.MyAbstractTableModel;

//@Component
public  abstract class ShowAbstractDialog<ModelItem> extends JDialog {

	private final JPanel contentPanel = new JPanel();

	protected MyAbstractTableModel<ModelItem> model;
	protected JTable table;
	private ModelItem item;
	private JButton deleteButton;

	private AbstractDialog<ModelItem> dialog;
	private JButton editButton;
	private JButton okButton;
	protected JTextField textField;
	private JLabel lblZnajd;
	private JButton cancelButton;
	private JScrollPane scrollPane;

	private boolean selectMode;
	private JPanel panel_1;
	protected JButton btn;
	protected int currentRow=-1;
@Autowired
	public ShowAbstractDialog(AbstractDialog<ModelItem> dialog,MyAbstractTableModel<ModelItem> model) {
		setModal(true);
		this.dialog=dialog;
		this.model=model;
		setBounds(100, 100, 517, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		table = new ToolTipTable(model);
		contentPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		table.getSelectionModel().addListSelectionListener(e -> selectionChanged());
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
			btnWyszukaj.addActionListener(e -> findAction());

			panel.add(btnWyszukaj);

			panel_1 = new JPanel();
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
			panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(e->okAction());
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			panel_1.add(okButton);
			panel_1.add(cancelButton);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			{
				deleteButton = new JButton("Usu\u0144");
				deleteButton.setEnabled(false);
				deleteButton.addActionListener(deleteAction());
				deleteButton.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			JButton addButton = new JButton("Dodaj");
			addButton.addActionListener(addAction());

			editButton = new JButton("Edytuj");
			editButton.setEnabled(false);
			editButton.addActionListener(editAction());
			
			btn = new JButton("Wycofaj");
			btn.setEnabled(false);
			btn.setVisible(false);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(editButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(addButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(deleteButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(71)
						.addComponent(deleteButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(addButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(editButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btn)
						.addContainerGap(27, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
	}

	private ActionListener editAction() {
		return e -> {
			dialog.setData(model.getData(currentRow));
			dialog.setVisible(true);
			model.update();
		};
	}

	private ActionListener addAction() {
		return e -> {
			dialog.setVisible(true);
			model.update();
		};
	}

	private ActionListener deleteAction() {
		return e -> {
			try {
				model.removeData(currentRow);
			} catch (DataIntegrityViolationException ex) {
				JOptionPane.showMessageDialog(null, "Nie mo¿na usun¹æ autora przypisanego do ksi¹¿ki!","",JOptionPane.INFORMATION_MESSAGE);
			}
		};
	}

	protected void selectionChanged() {
		if(table.getSelectedRow()!=-1)currentRow=table.getSelectedRow();
		boolean isSomethingSelected = table.getSelectedRow() != -1;
		deleteButton.setEnabled(isSomethingSelected);
		editButton.setEnabled(isSomethingSelected);
		okButton.setEnabled(isSomethingSelected);
		btn.setEnabled(isSomethingSelected);
	}

	void findAction()
	{
		model.find(textField.getText());
	}

	void okAction()
	{
		item=model.getData(currentRow);
		setVisible(false);
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible)

			model.update();
		panel_1.setVisible(selectMode);
		textField.setText("");
		super.setVisible(visible);
	}

	public ModelItem getData() {
		return item;
	}

	public void setSelectMode(boolean selectMode) {
		this.selectMode = selectMode;
	}
}
