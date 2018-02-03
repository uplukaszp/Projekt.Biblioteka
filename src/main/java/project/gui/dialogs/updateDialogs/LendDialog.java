package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.dialogs.selectDialogs.ShowBookDialog;
import project.gui.dialogs.selectDialogs.ShowReaderDialog;
import project.model.Book;
import project.model.BookStatus;
import project.model.Lend;
import project.model.Reader;
import project.repositories.LendRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class LendDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Lend l = new Lend();
	private JLabel bookLabel;
	private JLabel readerLabel;

	@Autowired
	private ShowBookDialog bookdialog;
	@Autowired
	private ShowReaderDialog readerdialog;

	@Autowired
	private LendRepository repo;

	public LendDialog() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 434, 189);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);

		JLabel lblWypoyczajcy = new JLabel("Wypo\u017Cyczaj\u0105cy:");

		JLabel lblKsika = new JLabel("Ksi\u0105\u017Cka:");

		JButton btnWybierz = new JButton("Wybierz");
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readerdialog.setVisible(true);
				l.setReader(readerdialog.getReader());
				readerLabel.setText(l.getReader().toString());
			}
		});

		JButton btnWybierz_1 = new JButton("Wybierz");
		btnWybierz_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (true) {
					bookdialog.setVisible(true);
					Book b = bookdialog.getBook();
					if (b == null) {
						break;
					}
					switch (b.getStatus()) {
					case available:
						l.setBook(b);
						bookLabel.setText(l.getBook().toString());
						return;
					case lent:
						JOptionPane.showMessageDialog(null, "Ksi¹¿ka aktualnie wypo¿yczona");
						break;
					case withdrawn:
						JOptionPane.showMessageDialog(null, "Ksi¹¿ka wycofana");
						break;
					default:
						break;
					}
				}
			}
		});

		readerLabel = new JLabel("Brak");

		bookLabel = new JLabel("Brak");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(28)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblWypoyczajcy).addComponent(lblKsika))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnWybierz)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(readerLabel))
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnWybierz_1)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(bookLabel)))
								.addContainerGap(111, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblWypoyczajcy)
								.addComponent(btnWybierz).addComponent(readerLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblKsika)
								.addComponent(btnWybierz_1).addComponent(bookLabel))
						.addContainerGap(51, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			JButton btnWypoyczNastpn = new JButton("Wypo\u017Cycz nast\u0119pn\u0105");
			btnWypoyczNastpn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!isEmptyData()) {
						l.setLendDate(new Date(System.currentTimeMillis()));
						repo.add(l);
						l.setBook(null);
						bookLabel.setText("Brak");
					} else {
						JOptionPane.showMessageDialog(null, "Nie uzupe³niono wszystkich danych");
					}
				}
			});
			buttonPane.add(btnWypoyczNastpn);
			{
				JButton okButton = new JButton("Wypo\u017Cycz i zako\u0144cz");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (!isEmptyData()) {
							l.setLendDate(new Date(System.currentTimeMillis()));
							repo.add(l);
							l = new Lend();
							bookLabel.setText("Brak");
							readerLabel.setText("Brak");
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Nie uzupe³niono wszystkich danych");
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Anuluj");
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

	private boolean isEmptyData() {
		return l.getBook() == null || l.getReader() == null;
	}
}
