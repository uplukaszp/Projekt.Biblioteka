package project.gui.dialogs.settingsDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.awt.event.ActionEvent;

@Component
public class ConnectionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField loginTextField;
	private JPasswordField passwordField;
	private JTextField adresTextField;
	private JTextField portTextField;
	private JTextField nameTextField;

	public ConnectionDialog() {
		setBounds(100, 100, 232, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		loginTextField = new JTextField();
		loginTextField.setColumns(10);
		passwordField = new JPasswordField();
		adresTextField = new JTextField();
		adresTextField.setColumns(10);
		portTextField = new JTextField();
		portTextField.setColumns(10);
		{
			nameTextField = new JTextField();
			nameTextField.setColumns(10);
		}
		JLabel lblLogin = new JLabel("Login:");
		JLabel lblHaso = new JLabel("Has\u0142o:");
		JLabel lblAdres = new JLabel("Adres:");
		JLabel lblPort = new JLabel("Port:");
		JLabel lblNazwaBazy = new JLabel("Nazwa bazy:");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel
								.createParallelGroup(Alignment.TRAILING).addComponent(lblLogin).addComponent(lblHaso)
								.addComponent(lblAdres).addComponent(lblPort).addComponent(lblNazwaBazy))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(portTextField).addComponent(adresTextField).addComponent(passwordField)
								.addComponent(loginTextField).addComponent(nameTextField))
						.addContainerGap(87, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLogin))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHaso))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(adresTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdres))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPort))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNazwaBazy))
						.addContainerGap(84, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int dialogRes = JOptionPane.showConfirmDialog(null,
								"Aby zapisaæ zmiany, nalezy uruchomiæ ponownie aplikacje. Czy chcesz zamkn¹æ teraz aplikacjê?",
								"", JOptionPane.YES_NO_OPTION);
						if (dialogRes == JOptionPane.YES_OPTION) {
							saveProperties();
							System.exit(0);
						} else
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
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			loadProperties();
	}

	void loadProperties() {
		File f = new File("./config.properties");
		InputStream stream = null;
		Properties prop = new Properties();

		try {
			stream = new FileInputStream(f);
			prop.load(stream);
			loginTextField.setText(prop.getProperty("db.login"));
			passwordField.setText(prop.getProperty("db.pw"));
			adresTextField.setText(prop.getProperty("db.url"));
			portTextField.setText(prop.getProperty("db.port"));
			nameTextField.setText(prop.getProperty("db.name"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveProperties() {
		Properties prop = new Properties();
		prop.setProperty("db.login", loginTextField.getText());
		prop.setProperty("db.pw", String.valueOf(passwordField.getPassword()));
		prop.setProperty("db.url", adresTextField.getText());
		prop.setProperty("db.port", portTextField.getText());
		prop.setProperty("db.name", nameTextField.getText());
		File f = new File("./config.properties");
		try {
			OutputStream stream = new FileOutputStream(f);
			prop.store(stream, "");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
