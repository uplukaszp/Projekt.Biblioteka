package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import project.gui.components.EmailSenderService;
import project.gui.components.ToolTipTable;
import project.gui.dialogs.settingsDialogs.EmailSettingsDialog;
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
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.SplashScreen;
import javax.swing.JScrollPane;

@Component
public class ShowLendDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private JButton remindButton;
	private JButton returnButton;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;

	@Autowired
	EmailSenderService sender;
	private JScrollPane scrollPane;

	@Autowired
	public ShowLendDialog(@Qualifier("lendTableModel") final LendTableModel archiveModel,
			@Qualifier("currentLendTableModel") final CurrentLendTableModel model) {
		setModal(true);
		setTitle("Przegl¹daj wypo¿yczenia");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new ToolTipTable(model);
			table.setModel(model);
			contentPanel.add(table.getTableHeader(), BorderLayout.NORTH);
		}
		{
			scrollPane = new JScrollPane(table);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			{
				returnButton = new JButton("Oddaj");
				returnButton.setEnabled(false);
				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LendTableModel temp = (LendTableModel) table.getModel();
						float penalty = temp.getPenalty(table.getSelectedRow());
						if (penalty > 0) {
							JOptionPane.showMessageDialog(null,
									"Przekroczono czas wypo¿yczenia, nale¿y uiœciæ op³atê w wysokoœci:" + penalty);
						}
						temp.returnBook(table.getSelectedRow());
						temp.update();
						returnButton.setEnabled(false);
						remindButton.setEnabled(false);
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
			remindButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LendTableModel model = (LendTableModel) table.getModel();

					sender.sendNotify(model.getLend(table.getSelectedRow()));

				}
			});
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
		{
			panel_1 = new JPanel();
			getContentPane().add(panel_1, BorderLayout.SOUTH);
			panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
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
						LendTableModel m = (LendTableModel) table.getModel();
						m.find(textField.getText());
					}
				});
				panel_1.add(btnNewButton);
			}
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() != -1) {
					LendTableModel temp = (LendTableModel) table.getModel();
					Lend currentLend = temp.getLend(table.getSelectedRow());
					if (currentLend.getReturnDate() != null || table.getSelectedRow() == -1) {
						remindButton.setEnabled(false);
						returnButton.setEnabled(false);

					} else {
						remindButton.setEnabled(true);
						returnButton.setEnabled(true);

					}
				}
			}
		});
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			LendTableModel m = (LendTableModel) table.getModel();
			m.update();
		}
		super.setVisible(visible);
	}
}
