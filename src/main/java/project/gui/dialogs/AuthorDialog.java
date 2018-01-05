package project.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JTextField;
import project.model.Author;
import project.repositories.AuthorRepository;

import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class AuthorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fornameTextField;
	private JTextField surnameTextfield;
	private JTextArea commentArea;

	@Autowired
	private AuthorRepository repo;

	public AuthorDialog() {

		setBounds(100, 100, 275, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			fornameTextField = new JTextField();
			fornameTextField.setColumns(10);
		}
		{
			surnameTextfield = new JTextField();
			surnameTextfield.setColumns(10);
		}
		{
			commentArea = new JTextArea();
		}

		JLabel lbforname = new JLabel("Imi\u0119");
		JLabel lblsurname = new JLabel("Nazwisko");
		JLabel lblComment = new JLabel("Komentarz");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel
								.createSequentialGroup().addGap(
										17)
								.addGroup(
										gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 31,
																Short.MAX_VALUE)
														.addComponent(lbforname))
												.addComponent(lblComment).addGroup(
														gl_contentPanel.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED, 7,
																		Short.MAX_VALUE)
																.addComponent(lblsurname)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(commentArea, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
										.addComponent(surnameTextfield, Alignment.LEADING)
										.addComponent(fornameTextField))
								.addGap(29)));
		gl_contentPanel
				.setVerticalGroup(
						gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_contentPanel.createSequentialGroup().addGap(7)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(fornameTextField).addComponent(lbforname))
												.addGap(4)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(surnameTextfield).addComponent(lblsurname))
												.addGap(4)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(commentArea, GroupLayout.PREFERRED_SIZE, 154,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblComment))
												.addGap(88)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Author a = new Author();
						a.setForename(fornameTextField.getText());
						a.setSurname(surnameTextfield.getText());
						a.setComment(commentArea.getText());
						repo.addAuthor(a);
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

	public void setData(Author a) {
		fornameTextField.setText(a.getForename());
		surnameTextfield.setText(a.getSurname());
		commentArea.setText(a.getComment());
	}
}
