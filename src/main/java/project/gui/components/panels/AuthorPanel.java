package project.gui.components.panels;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import project.gui.components.PatternTextField;
import project.model.Author;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;

@Component
public class AuthorPanel extends AbstractPanel<Author> {
	private PatternTextField fornameTextField;
	private PatternTextField surnameTextField;
	private JTextArea commentArea;

	public AuthorPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel);

		JLabel label = new JLabel("Imi\u0119");

		JLabel label_1 = new JLabel("Komentarz");

		JLabel label_2 = new JLabel("Nazwisko");

		commentArea = new JTextArea();

		surnameTextField = new PatternTextField(
				"[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[A-Za-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B\\\\-]{1,44}",
				"Powinno zaczyna\u0107 si\u0119 od du\u017Cej liter\n i zawiera\u0107 tylko litery");
		surnameTextField.setColumns(10);

		fornameTextField = new PatternTextField(
				"[A-Z\u0106\u0118\u0141\u0143\u00D3\u015A\u0179\u017B]{1}[a-z\u0105\u0107\u0119\u0142\u0144\u00F3\u015B\u017A\u017C]{1,44}",
				"Powinno zaczyna\u0107 si\u0119 od du\u017Cej litery\n i zawiera\u0107 tylko litery");
		fornameTextField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 235, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addGap(17)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false).addComponent(label)
								.addComponent(label_1).addComponent(label_2))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(commentArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137,
										Short.MAX_VALUE)
								.addComponent(surnameTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137,
										Short.MAX_VALUE)
								.addComponent(fornameTextField, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 235, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addGap(7)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(fornameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
						.addGap(4)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(surnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2))
						.addGap(4)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(commentArea, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
						.addGap(88)));
		panel.setLayout(gl_panel);

	}

	@Override
	protected void readFromFields() {
		if (item == null)
			item = new Author();
		item.setForename(fornameTextField.getText());
		item.setSurname(surnameTextField.getText());
		item.setComment(commentArea.getText());
	}

	@Override
	public void setData(Author a) {
		fornameTextField.setText(a.getForename());
		surnameTextField.setText(a.getSurname());
		commentArea.setText(a.getComment());
		this.item = a;
	}

	@Override
	protected void init() {
		item=new Author();
	}
}
