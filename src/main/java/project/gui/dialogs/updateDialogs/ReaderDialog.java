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

import project.gui.components.PatternTextField;
import project.gui.components.PatternVerifier;
import project.model.Publisher;
import project.model.Reader;
import project.repositories.ReaderRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class ReaderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PatternTextField fornameTextField;
	private PatternTextField surnameTextField;
	private PatternTextField addressTextField;
	private PatternTextField CityTextField;
	private PatternTextField zipCodeTextField;
	private PatternTextField emailTextField;

	@Autowired
	private ReaderRepository repo;
	private Reader r = new Reader();
	protected PatternVerifier verifier;

	public ReaderDialog() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 229, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		fornameTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø]{1,44}",
				"Powinno zaczynaÊ siÍ od duøej litery\\n i zawieraÊ tylko litery");
		fornameTextField.setColumns(10);

		surnameTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø]{1,44}",
				"Powinno zaczynaÊ siÍ od duøej litery\\n i zawieraÊ tylko litery");
		surnameTextField.setColumns(10);

		addressTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø1-9 ]{1,44}",
				"Nazwa ulicy rozpoczynajπca siÍ od duøej litery i nr posesji, np. Lipowa 15(do 45 znakÛw)");
		addressTextField.setColumns(10);

		CityTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø ]{1,44}",
				"Rozpoczyna siÍ od duøej litery( nie wiecej niø 45 znakÛw)");
		CityTextField.setColumns(10);

		zipCodeTextField = new PatternTextField("\\\\d{2}-\\\\d{3}", "Kod pocztowy np. 42-800");
		zipCodeTextField.setColumns(10);

		emailTextField = new PatternTextField(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
				"Adres email w formacie nazwa@host.pl");
		emailTextField.setColumns(10);

		JLabel lblImi = new JLabel("Imi\u0119");

		JLabel lblNazwisko = new JLabel("Nazwisko");

		JLabel lblAdres = new JLabel("Adres");

		JLabel lblMiejscowo = new JLabel("Miejscowo\u015B\u0107");

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");

		JLabel lblEmail = new JLabel("Email");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(10)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblImi)
								.addComponent(lblNazwisko).addComponent(lblAdres).addComponent(lblMiejscowo)
								.addComponent(lblKodPocztowy).addComponent(lblEmail))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(fornameTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 120,
										Short.MAX_VALUE)
								.addComponent(surnameTextField, Alignment.TRAILING)
								.addComponent(addressTextField, Alignment.TRAILING)
								.addComponent(CityTextField, Alignment.TRAILING)
								.addComponent(zipCodeTextField, Alignment.TRAILING)
								.addComponent(emailTextField, Alignment.TRAILING))
						.addGap(2)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(fornameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImi))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(surnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNazwisko))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(addressTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdres))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(CityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMiejscowo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(zipCodeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblKodPocztowy))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_contentPanel.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail))
						.addGap(70)));
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
						r.setAddress(addressTextField.getText());
						r.setCity(CityTextField.getText());
						r.setEmail(emailTextField.getText());
						r.setForname(fornameTextField.getText());
						r.setSurname(surnameTextField.getText());
						r.setZipCode(zipCodeTextField.getText());
						if (r.getId() == 0)
							repo.addReader(r);
						else
							repo.updateReader(r);
						setData(new Reader());
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
						setData(new Reader());
						setVisible(false);

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		verifier=new PatternVerifier(contentPanel.getComponents());
	}

	public void setData(Reader r) {
		addressTextField.setText(r.getAddress());
		CityTextField.setText(r.getCity());
		emailTextField.setText(r.getEmail());
		fornameTextField.setText(r.getForname());
		surnameTextField.setText(r.getSurname());
		zipCodeTextField.setText(r.getZipCode());
		this.r = r;
	}
}
