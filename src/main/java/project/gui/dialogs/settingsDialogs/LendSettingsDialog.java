package project.gui.dialogs.settingsDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import project.gui.components.PatternTextField;
import project.gui.components.PatternVerifier;
import project.repositories.LendSettingsRepository;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class LendSettingsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	@Autowired
	private LendSettingsRepository repo;

	private PatternVerifier verifier;
	private PatternTextField dayTextField;
	private PatternTextField penaltyTextField;

	public LendSettingsDialog() {
		setModal(true);
		setTitle("Opcje wypo¿yczeñ");
		setBounds(100, 100, 329, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		dayTextField = new PatternTextField("^[1-9]+[0-9]*$", "Liczba dni wiêksza od 0");

		penaltyTextField = new PatternTextField("\\d+\\.\\d{2}|\\d", "Wysokoœæ kary za 1 dzieñ zw³oki, np. 0,80 1,50");

		JLabel lblDugoWypoyczenia = new JLabel("D\u0142ugo\u015B\u0107 wypo\u017Cyczenia:");

		JLabel lblKaraZaDzie = new JLabel("Kara za dzie\u0144:");

		JLabel lblDni = new JLabel("dni");

		JLabel lblZ = new JLabel("z\u0142");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblDugoWypoyczenia).addComponent(lblKaraZaDzie))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING,
												gl_contentPanel.createSequentialGroup()
														.addComponent(penaltyTextField, GroupLayout.PREFERRED_SIZE, 108,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblZ))
										.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
												.addComponent(dayTextField, GroupLayout.PREFERRED_SIZE, 108,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDni)))
								.addContainerGap(13, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(20)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblDugoWypoyczenia)
						.addComponent(dayTextField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDni))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(penaltyTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKaraZaDzie).addComponent(lblZ))
				.addContainerGap(13, Short.MAX_VALUE)));
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
						repo.setDaysOfRental(Integer.valueOf(dayTextField.getText()));
						repo.setPenaltyForDay(Float.valueOf(penaltyTextField.getText()));
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
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			dayTextField.setText(String.valueOf(repo.getDaysOfRental()));
			penaltyTextField.setText(String.valueOf(repo.getPenaltyForDay()));
			verifier.reset();
		}
		super.setVisible(visible);
	}
}
