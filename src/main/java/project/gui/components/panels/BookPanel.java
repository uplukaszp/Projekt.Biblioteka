package project.gui.components.panels;

import project.model.Book;
import project.model.BookStatus;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import project.gui.components.PatternTextField;
import project.gui.dialogs.selectDialogs.ShowAuthorDialog;
import project.gui.dialogs.selectDialogs.ShowPublisherDialog;


import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class BookPanel extends AbstractPanel<Book> {
	private PatternTextField titleField;
	private PatternTextField yearField;
	private PatternTextField iSBNField;
	private PatternTextField keyWordsField;
	private PatternTextField typeField;

	@Autowired
	private ShowAuthorDialog authdialog;
	@Autowired
	private ShowPublisherDialog pubdialog;
	private JLabel authlabel;
	private JLabel publisherlabel;

	
	public BookPanel() {
		setLayout(new BorderLayout(0, 0));
		item=new Book();
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel);

		JLabel label = new JLabel("Tytu\u0142");

		JLabel label_1 = new JLabel("Rok wydania");

		JLabel label_2 = new JLabel("ISBN");

		JLabel label_3 = new JLabel("S\u0142owa kluczowe");

		JLabel label_4 = new JLabel("Gatunek");

		JLabel label_5 = new JLabel("Autor");

		JLabel label_6 = new JLabel("Wydawnictwo");

		JButton publisherButton = new JButton("Przegl\u0105daj");
		publisherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pubdialog.setSelectMode(true);
				pubdialog.setVisible(true);
				if(pubdialog.getData()==null)return;
				item.setPublisher(pubdialog.getData());
				publisherlabel.setText(item.getPublisher().toString());
			}
		});

		publisherlabel = new JLabel("Nie wybrano");

		JButton authorButton = new JButton("Przegl\u0105daj");
		authorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authdialog.setSelectMode(true);
				authdialog.setVisible(true);
				if(authdialog.getData()==null)return;
				item.setAuthor(authdialog.getData());
				authlabel.setText(item.getAuthor().toString());
			}
		});

		authlabel = new JLabel("Nie wybrano");

		titleField = new PatternTextField(".{1,120}",
				"Dowolny nie pusty ci\u0105g znak\u00F3w, nie d\u0142u\u017Cszy ni\u017C 120 symboli");
		titleField.setColumns(10);

		yearField = new PatternTextField("^[12][0-9]{3}$", "Rok z przedzia\u0142u 1000-2099");
		yearField.setColumns(10);

		iSBNField = new PatternTextField(
				"^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
				"Dowolny zapis ISBN-10 lub ISBN-13");
		iSBNField.setColumns(10);

		keyWordsField = new PatternTextField(".{0,255}", "Opcjonalne(do 255 znak\u00F3w)");
		keyWordsField.setColumns(10);

		typeField = new PatternTextField(
				".{0,45}",
				"Dowolny nie pusty ci\u0105g znak\u00F3w(do 45 znak\u00F3w)");
		typeField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5)
						.addComponent(label_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(publisherButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(publisherlabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(authorButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(authlabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(titleField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(yearField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(iSBNField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(keyWordsField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(typeField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(iSBNField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(keyWordsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(authorButton)
						.addComponent(label_5)
						.addComponent(authlabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(publisherButton)
						.addComponent(label_6)
						.addComponent(publisherlabel))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	@Override
	protected void readFromFields() {
		if (item == null) {
			item = new Book();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("YYYY");
		try {
			java.util.Date date = formatter.parse(yearField.getText());
			java.sql.Date dbDate = new java.sql.Date(date.getTime());
			if (item.getId() == 0)
				item.setStatus(BookStatus.available);
			item.setISBN(iSBNField.getText());
			item.setKeywords(keyWordsField.getText());
			item.setPublicationDate(dbDate);
			item.setTitle(titleField.getText());
			item.setType(typeField.getText());
		} catch (ParseException e1) {
		}
	}

	@Override
	public void setData(Book b) {
		this.item = b;
		titleField.setText(b.getTitle());
		yearField.setText("");
		iSBNField.setText(b.getISBN());
		keyWordsField.setText(b.getKeywords());
		typeField.setText(b.getType());
		authlabel.setText(b.getAuthor().getForename());
		publisherlabel.setText(b.getPublisher().getName());

		if (b.getPublicationDate() != null) {
			Calendar cal = null;
			cal = Calendar.getInstance();
			cal.setTime(b.getPublicationDate());
			yearField.setText(String.valueOf(cal.get(Calendar.YEAR)));
		}
	}

	@Override
	protected void init() {
		item=new Book();
	}
}
