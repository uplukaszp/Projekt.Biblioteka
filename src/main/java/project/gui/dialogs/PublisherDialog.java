package project.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.model.Author;
import project.model.Publisher;
import project.repositories.PublisherRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class PublisherDialog extends JDialog {

	@Autowired
	private PublisherRepository repo;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField AdressTextField;
	private JTextField CityTextField;
	private JTextField zipCodeTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private Publisher p = new Publisher();

	public PublisherDialog() {
		setBounds(100, 100, 235, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		AdressTextField = new JTextField();
		AdressTextField.setColumns(10);
		CityTextField = new JTextField();
		CityTextField.setColumns(10);
		zipCodeTextField = new JTextField();
		zipCodeTextField.setColumns(10);
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		emailTextField = new JTextField();
		emailTextField.setColumns(10);

		JLabel lblNazwa = new JLabel("Nazwa");

		JLabel lblNewLabel = new JLabel("Adres");

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

}
