package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JTextField;

import project.gui.components.PatternTextField;
import project.gui.components.PatternVerifier;
import project.model.Author;
import project.repositories.AuthorRepository;

import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class AuthorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PatternTextField fornameTextField;
	private PatternTextField surnameTextfield;
	private PatternTextField commentArea;
	private Author a = new Author();

	@Autowired
	private AuthorRepository repo;
	protected PatternVerifier verifier;

	public AuthorDialog() {
		setModal(true);
		setResizable(false);

		setBounds(100, 100, 241, 297);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			fornameTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø]{1,44}","Powinno zaczynaÊ siÍ od duøej litery\n i zawieraÊ tylko litery");
			fornameTextField.setColumns(10);
		}
		{
			surnameTextfield = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø]{1,44}","Powinno zaczynaÊ siÍ od duøej liter\n i zawieraÊ tylko litery");
			surnameTextfield.setColumns(10);
		}
		{
			commentArea = new PatternTextField(".{0,120}","Dowolny ciπg znakÛw, nie d≥uøszy niø 120 symboli");
		}

		JLabel lbforname = new JLabel("Imi\u0119");
		JLabel lblsurname = new JLabel("Nazwisko");
		JLabel lblComment = new JLabel("Komentarz");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(17)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false).addComponent(lbforname)
								.addComponent(lblComment).addComponent(lblsurname))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(commentArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
										Short.MAX_VALUE)
								.addComponent(surnameTextfield, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
										Short.MAX_VALUE)
								.addComponent(fornameTextField, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(7)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(fornameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbforname))
						.addGap(4)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(surnameTextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblsurname))
						.addGap(4)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(commentArea, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
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
						if (!verifier.areFieldsMatched())
							return;
						a.setForename(fornameTextField.getText());
						a.setSurname(surnameTextfield.getText());
						a.setComment(commentArea.getText());
						if (a.getId() == 0)
							repo.addAuthor(a);
						else
							repo.updateAuthor(a);
						setData(new Author());
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
						setData(new Author());
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		verifier=new PatternVerifier(contentPanel.getComponents());
	}

	public void setData(Author a) {
		fornameTextField.clear();
		surnameTextfield.clear();
		commentArea.clear();
		fornameTextField.setText(a.getForename());
		surnameTextfield.setText(a.getSurname());
		commentArea.setText(a.getComment());
		this.a = a;
	}
}
