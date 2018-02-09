package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
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
import project.repositories.PublisherRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class PublisherDialog extends JDialog {

	@Autowired
	private PublisherRepository repo;
	private final JPanel contentPanel = new JPanel();
	private PatternTextField nameTextField;
	private PatternTextField AdressTextField;
	private PatternTextField CityTextField;
	private PatternTextField zipCodeTextField;
	private PatternTextField phoneTextField;
	private PatternTextField emailTextField;
	private Publisher p = new Publisher();
	protected PatternVerifier verifier;

	public PublisherDialog() {
		setModal(true);
		setTitle("Dodaj wydawnictwo");
		setResizable(false);
		setBounds(100, 100, 235, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		nameTextField = new PatternTextField(".{1,44}", "Nie pusty ciπg znakÛw o d≥ugoúci 45 znakÛw");
		nameTextField.setColumns(10);
		AdressTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø1-9 ]{1,44}",
				"Nazwa ulicy rozpoczynajπca siÍ od duøej litery i nr posesji, np. Lipowa 15(do 45 znakÛw)");
		AdressTextField.setColumns(10);
		CityTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø ]{1,44}",
				"Rozpoczyna siÍ od duøej litery( nie wiecej niø 45 znakÛw)");
		CityTextField.setColumns(10);
		zipCodeTextField = new PatternTextField("\\d{2}-\\d{3}", "Kod pocztowy np. 42-800");
		zipCodeTextField.setColumns(10);
		phoneTextField = new PatternTextField("\\+{0,1}[0-9 ]{6,15}",
				"9-cio cyfrowy nr poprzedzony nr kierunkowym,odzielony spacjπ lub - np +48 617 025 111");
		phoneTextField.setColumns(10);
		emailTextField = new PatternTextField(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
				"Adres email w formacie nazwa@host.pl");
		emailTextField.setColumns(10);

		JLabel lblNazwa = new JLabel("Nazwa");

		JLabel lblNewLabel = new JLabel("Ulica i nr");

		JLabel lblMiejscowo = new JLabel("Miejscowo\u015B\u0107");

		JLabel lblNewLabel_1 = new JLabel("Kod pocztowy");

		JLabel lblNewLabel_2 = new JLabel("Telefon");

		JLabel lblNewLabel_3 = new JLabel("Email");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblNazwa)
								.addComponent(lblNewLabel).addComponent(lblMiejscowo).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2).addComponent(lblNewLabel_3))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(AdressTextField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(CityTextField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(zipCodeTextField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(phoneTextField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNazwa))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(AdressTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(CityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMiejscowo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(zipCodeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(phoneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
						.addContainerGap(56, Short.MAX_VALUE)));
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
						p.setAddress(AdressTextField.getText());
						p.setCity(CityTextField.getText());
						p.setEmail(emailTextField.getText());
						p.setName(nameTextField.getText());
						p.setPhoneNumber(phoneTextField.getText());
						p.setZipCode(zipCodeTextField.getText());
						if (p.getId() == 0)
							repo.addPublisher(p);
						else
							repo.updatePublisher(p);
						setData(new Publisher());
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
						setData(new Publisher());
						setVisible(false);

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		verifier=new PatternVerifier(contentPanel.getComponents());
	}

	public void setData(Publisher p) {
		AdressTextField.setText(p.getAddress());
		CityTextField.setText(p.getCity());
		emailTextField.setText(p.getEmail());
		nameTextField.setText(p.getName());
		phoneTextField.setText(p.getPhoneNumber());
		zipCodeTextField.setText(p.getZipCode());
		this.p = p;
	}
	@Override
	public void setVisible(boolean visible) {
		if (!visible) {
			setData(new Publisher());
			verifier.reset();
		}
		super.setVisible(visible);
	}
}
