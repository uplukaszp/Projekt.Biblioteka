package project.gui.dialogs.settingsDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import project.gui.components.PatternTextField;
import project.gui.components.PatternVerifier;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class EmailSettingsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PatternTextField loginTextField;
	private JPasswordField passwordTextField;
	private PatternTextField hostTextField;
	private PatternTextField portTextField;
	private PatternVerifier verifier;

	public EmailSettingsDialog() {
		setModal(true);
		setTitle("Ustawienia poczty email");
		setBounds(100, 100, 228, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		loginTextField = new PatternTextField(".{1,}", "Nie mo¿e byæ pusty");
		loginTextField.setColumns(10);

		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);

		hostTextField = new PatternTextField(".{1,}", "Nie mo¿e byæ puste");
		hostTextField.setColumns(10);

		portTextField = new PatternTextField(
				"(6553[0-5]|655[0-2][0-9]\\d|65[0-4](\\d){2}|6[0-4](\\d){3}|[1-5](\\d){4}|[1-9](\\d){0,3})",
				"Musi byæ liczb¹ od 1 do 65535");
		portTextField.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");

		JLabel lblHaso = new JLabel("Has\u0142o");

		JLabel lblHost = new JLabel("Host:");

		JLabel lblPort = new JLabel("Port:");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(26)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblLogin)
								.addComponent(lblHaso).addComponent(lblHost).addComponent(lblPort))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(hostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(228, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLogin))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHaso))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(hostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHost))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPort))
						.addContainerGap(109, Short.MAX_VALUE)));
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
		verifier = new PatternVerifier(contentPanel.getComponents());
		loadProperties();
	}

	void loadProperties() {
		File f = new File("./email.properties");
		InputStream stream = null;
		Properties prop = new Properties();

		try {
			stream = new FileInputStream(f);
			prop.load(stream);
			loginTextField.setText(prop.getProperty("mail.username"));
			passwordTextField.setText(prop.getProperty("mail.password"));
			hostTextField.setText(prop.getProperty("mail.host"));
			portTextField.setText(prop.getProperty("mail.port"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveProperties() {
		Properties prop = new Properties();
		prop.setProperty("mail.username", loginTextField.getText());
		prop.setProperty("mail.password", String.valueOf(passwordTextField.getPassword()));
		prop.setProperty("mail.host", hostTextField.getText());
		prop.setProperty("mail.port", portTextField.getText());
		File f = new File("./email.properties");
		try {
			OutputStream stream = new FileOutputStream(f);
			prop.store(stream, "");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			loadProperties();
		}
		super.setVisible(visible);
	}
}
