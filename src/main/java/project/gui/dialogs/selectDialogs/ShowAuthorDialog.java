package project.gui.dialogs.selectDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class ShowAuthorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	final private AuthorTableModel model;
	private JTable table;
	private Author a;
	

	
	@Autowired
	public ShowAuthorDialog(final AuthorTableModel model) {
		setModal(true);
		this.model=model;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		contentPanel.add(table, BorderLayout.CENTER);
		contentPanel.add(table.getTableHeader(),BorderLayout.PAGE_START);
		table.setModel(model);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						a=model.getAuthor(table.getSelectedRow());
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
	}
	@Override
	public void setVisible(boolean visible)
	{
		if(visible)model.update();
		super.setVisible(visible);
	}
	public Author getAuthor()
	{
		return a;
	}
}
