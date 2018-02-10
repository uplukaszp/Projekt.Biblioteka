package project.gui.components.panels;

import project.model.Reader;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import project.gui.components.PatternTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;

@Component
public class ReaderPanel extends AbstractPanel<Reader> {
	private PatternTextField forname;
	private PatternTextField surname;
	private PatternTextField street;
	private PatternTextField city;
	private PatternTextField code;
	private PatternTextField email;

	public ReaderPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel);

		JLabel label = new JLabel("Imi\u0119");

		JLabel label_1 = new JLabel("Nazwisko");

		JLabel label_2 = new JLabel("Ulica i nr");

		JLabel label_3 = new JLabel("Miejscowo\u015B\u0107");

		JLabel label_4 = new JLabel("Kod pocztowy");

		JLabel label_5 = new JLabel("Email");

		forname = new PatternTextField(
				"[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[a-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C]{1,44}",
				"Zaczyna si\u0119 z du\u017Cej litery i zawiera tylko litery(max. 45 znaków)");
		forname.setColumns(10);

		surname = new PatternTextField(
				"[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[A-Za-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B\\-]{1,44}",
				"Zaczyna si\u0119 z du\u017Cej litery i zawiera tylko litery(max. 45 znaków)");
		surname.setColumns(10);

		street = new PatternTextField(
				"[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[a-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C1-9 \\\\.]{1,44}",
				"Nazwa ulicy rozpoczynaj\u0105ca si\u0119 od du\u017Cej litery i nr posesji, np. Lipowa 15(do 45 znak\u00F3w)");
		street.setColumns(10);

		city = new PatternTextField(
				"[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[a-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017CA-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B ]{1,44}",
				"Rozpoczyna si\u0119 od du\u017Cej litery( nie wiecej ni\u017C 45 znak\u00F3w)");
		city.setColumns(10);

		code = new PatternTextField("\\d{2}-\\d{3}", "Kod pocztowy np. 42-800");
		code.setColumns(10);

		email = new PatternTextField(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
				"Adres email w formacie nazwa@adreshostu");
		email.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_4)
						.addComponent(label_5)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(email, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(code, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(city, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(street, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(surname, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(forname, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(forname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(32)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(street, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(city, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(6)
									.addComponent(code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5)))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_4))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(surname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))))
					.addContainerGap(140, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	@Override
	protected void readFromFields() {
		item.setAddress(street.getText());
		item.setCity(city.getText());
		item.setEmail(email.getText());
		item.setForname(forname.getText());
		item.setSurname(surname.getText());
		item.setZipCode(code.getText());
	}

	@Override
	public void setData(Reader r) {
		street.setText(r.getAddress());
		city.setText(r.getCity());
		email.setText(r.getEmail());
		forname.setText(r.getForname());
		surname.setText(r.getSurname());
		code.setText(r.getZipCode());
		this.item = r;
	}

	@Override
	protected void init() {
		item=new Reader();
	}
}
