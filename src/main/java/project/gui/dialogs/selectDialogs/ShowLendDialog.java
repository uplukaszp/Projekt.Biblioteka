package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import project.gui.tablemodels.CurrentLendTableModel;
import project.gui.tablemodels.LendTableModel;
import project.model.Lend;

import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@Component
public class ShowLendDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private JButton remindButton;
	private JButton returnButton;

	@Autowired
	public ShowLendDialog(@Qualifier("lendTableModel") final LendTableModel archiveModel,
			@Qualifier("currentLendTableModel") final CurrentLendTableModel model) {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			table.setModel(model);
			contentPanel.add(table);
			contentPanel.add(table.getTableHeader(), BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			{
				returnButton = new JButton("Oddaj");
				returnButton.setEnabled(false);
				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						archiveModel.returnBook(table.getSelectedRow());
						archiveModel.update();
					}
				});
			}

			final JCheckBox archChckBox = new JCheckBox("Archiwanle");
			archChckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if (archChckBox.isSelected()) {
						table.setModel(archiveModel);
						archiveModel.update();
					} else {
						table.setModel(model);
						model.update();
					}
					
					
				}
			});

			remindButton = new JButton("Przypomnij");
			remindButton.setEnabled(false);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
					gl_panel.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(archChckBox)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(returnButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(remindButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addContainerGap()));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addGap(75).addComponent(returnButton)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(remindButton).addGap(18)
							.addComponent(archChckBox).addContainerGap(60, Short.MAX_VALUE)));
			panel.setLayout(gl_panel);
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				Lend currentLend = archiveModel.getLend(table.getSelectedRow());
				if (currentLend.getReturnDate() != null || table.getSelectedRow() == -1) {
					remindButton.setEnabled(false);
					returnButton.setEnabled(false);

				} else {
					remindButton.setEnabled(true);
					returnButton.setEnabled(true);

				}
			}
		});
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			LendTableModel m=(LendTableModel) table.getModel();
			m.update();
		}
		super.setVisible(visible);
	}
}
