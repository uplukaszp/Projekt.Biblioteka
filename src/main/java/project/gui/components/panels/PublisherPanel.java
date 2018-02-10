package project.gui.components.panels;

import project.model.Publisher;
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
public class PublisherPanel extends AbstractPanel<Publisher> {
	private PatternTextField name;
	private PatternTextField street;
	private PatternTextField city;
	private PatternTextField code;
	private PatternTextField phone;
	private PatternTextField mail;
	public PublisherPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel);
		
		JLabel label = new JLabel("Nazwa");
		
		JLabel label_1 = new JLabel("Ulica i nr");
		
		JLabel label_2 = new JLabel("Miejscowo\u015B\u0107");
		
		JLabel label_3 = new JLabel("Kod pocztowy");
		
		JLabel label_4 = new JLabel("Telefon");
		
		JLabel label_5 = new JLabel("Email");
		
		name = new PatternTextField(".{1,44}", "Nie pusty ci\u0105g znak\u00F3w o d\u0142ugo\u015Bci 45 znak\u00F3w");
		name.setColumns(10);
		
		street = new PatternTextField("[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[a-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C1-9 ]{1,44}", "Nazwa ulicy rozpoczynaj\u0105ca si\u0119 od du\u017Cej litery i nr posesji, np. Lipowa 15(do 45 znak\u00F3w)");
		street.setColumns(10);
		
		city = new PatternTextField("[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[a-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C ]{1,44}", "Rozpoczyna si\u0119 od du\u017Cej litery( nie wiecej ni\u017C 45 znak\u00F3w)");
		city.setColumns(10);
		
		code = new PatternTextField("\\d{2}-\\d{3}", "Kod pocztowy np. 42-800");
		code.setColumns(10);
		
		phone = new PatternTextField("\\+{0,1}[0-9 ]{6,15}", "9-cio cyfrowy nr poprzedzony nr kierunkowym,odzielony spacj\u0105 lub - np +48 617 025 111");
		phone.setColumns(10);
		
		mail = new PatternTextField("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", "Adres email w formacie nazwa@host.pl");
		mail.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 229, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(name, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(street, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(city, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(code, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(phone, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(mail, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 236, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(street, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(city, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	@Override
	protected void readFromFields() {
		
		item.setAddress(street.getText());
		item.setCity(city.getText());
		item.setEmail(mail.getText());
		item.setName(name.getText());
		item.setPhoneNumber(phone.getText());
		item.setZipCode(code.getText());
	}
	@Override
	public void setData(Publisher p)
	{
		street.setText(p.getAddress());
		city.setText(p.getCity());
		mail.setText(p.getEmail());
		name.setText(p.getName());
		phone.setText(p.getPhoneNumber());
		code.setText(p.getZipCode());
		this.item = p;
	}

	@Override
	protected void init() {
		item=new Publisher();
	}
}
