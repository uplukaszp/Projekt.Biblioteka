package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.dialogs.selectDialogs.ShowBookDialog;
import project.gui.dialogs.selectDialogs.ShowReaderDialog;
import project.model.Book;
import project.model.Reader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class LendDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Book b;
	private Reader r;
	private Date d;
	private JLabel dateLabel;
	private JLabel bookLabel;
	private JLabel readerLabel;
	
	@Autowired
	private ShowBookDialog bookdialog;
	@Autowired
	private ShowReaderDialog readerdialog;
	
	public LendDialog() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 411, 186);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		JLabel lblWypoyczajcy = new JLabel("Wypo\u017Cyczaj\u0105cy:");
		
		JLabel lblKsika = new JLabel("Ksi\u0105\u017Cka:");
		
		JLabel lblDataWypoyczenia = new JLabel("Data wypo\u017Cyczenia:");
		
		JButton btnWybierz = new JButton("Wybierz");
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readerdialog.setVisible(true);
				r=readerdialog.getReader();
				readerLabel.setText(r.toString());
			}
		});
		
		JButton btnWybierz_1 = new JButton("Wybierz");
		btnWybierz_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookdialog.setVisible(true);
				b=bookdialog.getBook();
				bookLabel.setText(b.toString());
			}
		});
		
		JButton btnTeraz = new JButton("Teraz");
		btnTeraz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d=new Date(System.currentTimeMillis());
				dateLabel.setText(d.toString());
			}
		});
		
		JButton btnWybierz_2 = new JButton("Wybierz");
		
		readerLabel = new JLabel("Brak");
		
		bookLabel = new JLabel("Brak");
		
		dateLabel = new JLabel("Brak");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblWypoyczajcy)
						.addComponent(lblKsika)
						.addComponent(lblDataWypoyczenia))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnTeraz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnWybierz_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dateLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnWybierz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnWybierz_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookLabel)))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWypoyczajcy)
						.addComponent(btnWybierz)
						.addComponent(readerLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKsika)
						.addComponent(btnWybierz_1)
						.addComponent(bookLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataWypoyczenia)
						.addComponent(btnTeraz)
						.addComponent(btnWybierz_2)
						.addComponent(dateLabel))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnWypoyczNastpn = new JButton("Wypo\u017Cycz nast\u0119pn\u0105");
			buttonPane.add(btnWypoyczNastpn);
			{
				JButton okButton = new JButton("Wypo\u017Cycz i zako\u0144cz");
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
}
