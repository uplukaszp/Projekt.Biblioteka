package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import project.gui.components.PatternTextField;
import project.gui.components.PatternVerifier;
import project.gui.dialogs.selectDialogs.ShowAuthorDialog;
import project.gui.dialogs.selectDialogs.ShowPublisherDialog;
import project.model.Author;
import project.model.Book;
import project.model.BookStatus;
import project.model.Publisher;
import project.repositories.AuthorRepository;
import project.repositories.BookRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

@Component
public class BookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PatternTextField titleTextField;
	private PatternTextField yearTextField;
	private PatternTextField isbnTextField;
	private PatternTextField keyWordsTextField;
	private PatternTextField typeTextField;

	@Autowired
	private BookRepository repo;

	@Autowired
	private ShowAuthorDialog showauthdialog;
	@Autowired
	private ShowPublisherDialog showpubdialog;

	private Book b = new Book();
	private JLabel authlabel;
	private JLabel publisherLabel;
	protected PatternVerifier verifier;

	public BookDialog() {
		setTitle("Dodaj ksiπøkÍ");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 363, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		titleTextField = new PatternTextField(".{1,120}", "Dowolny nie pusty ciπg znakÛw, nie d≥uøszy niø 120 symboli");
		titleTextField.setColumns(10);

		yearTextField = new PatternTextField("^[12][0-9]{3}$", "Rok z przedzia≥u 1000-2099");
		yearTextField.setColumns(10);

		isbnTextField = new PatternTextField(
				"^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
				"Dowolny zapis ISBN-10 lub ISBN-13");
		isbnTextField.setColumns(10);

		keyWordsTextField = new PatternTextField(".{0,255}", "Opcjonalne(do 255 znakÛw)");

		keyWordsTextField.setColumns(10);

		typeTextField = new PatternTextField("[A-Z∆ £—”åèØ]{1}[a-zπÊÍ≥ÒÛúüø ]{0,45}",
				"Dowolny nie pusty ciπg znakÛw(do 45 znakÛw)");
		typeTextField.setColumns(10);

		JLabel lblTytu = new JLabel("Tytu\u0142");

		JLabel lblNewLabel = new JLabel("Rok wydania");

		JLabel lblNewLabel_1 = new JLabel("ISBN");

		JLabel lblSowaKluczowe = new JLabel("S\u0142owa kluczowe");

		JLabel lblGatunek = new JLabel("Gatunek");

		JLabel lblAutor = new JLabel("Autor");

		JLabel lblWydawnictwo = new JLabel("Wydawnictwo");

		JButton btnNewButton = new JButton("Przegl\u0105daj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showauthdialog.setSelectMode(true);
				showauthdialog.setVisible(true);
				b.setAuthor(showauthdialog.getAuthor());
				authlabel.setText(b.getAuthor().getForename());
			}
		});

		JButton btnNewButton_1 = new JButton("Przegl\u0105daj");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				showpubdialog.setSelectMode(true);
				showpubdialog.setVisible(true);
				b.setPublisher(showpubdialog.getPublisher());
				publisherLabel.setText(b.getPublisher().getName());
			}
		});

		authlabel = new JLabel("Nie wybrano");

		publisherLabel = new JLabel("Nie wybrano");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(19)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblTytu)
								.addComponent(lblNewLabel).addComponent(lblNewLabel_1).addComponent(lblSowaKluczowe)
								.addComponent(lblGatunek).addComponent(lblAutor).addComponent(lblWydawnictwo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnNewButton_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(publisherLabel,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(authlabel,
												GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(titleTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(yearTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(isbnTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(keyWordsTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(typeTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149,
												Short.MAX_VALUE)))
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTytu))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(yearTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(isbnTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(keyWordsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSowaKluczowe))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(typeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGatunek))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(lblAutor).addComponent(authlabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_1)
								.addComponent(lblWydawnictwo).addComponent(publisherLabel))
						.addContainerGap(48, Short.MAX_VALUE)));
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
						SimpleDateFormat formatter = new SimpleDateFormat("YYYY");
						try {
							java.util.Date date = formatter.parse(yearTextField.getText());
							java.sql.Date dbDate = new java.sql.Date(date.getTime());
							if (b.getId() == 0)
								b.setStatus(BookStatus.available);
							else
								b.setStatus(b.getStatus());
							b.setISBN(isbnTextField.getText());
							b.setKeywords(keyWordsTextField.getText());
							b.setPublicationDate(dbDate);
							b.setTitle(titleTextField.getText());
							b.setType(typeTextField.getText());
							if (b.getId() == 0)
								repo.addBook(b);
							else
								repo.updateBook(b);
							setVisible(false);
						} catch (ParseException e1) {
						}
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

	public void setData(Book b) {

		this.b = b;
		titleTextField.setText(b.getTitle());
		yearTextField.setText("");
		isbnTextField.setText(b.getISBN());
		keyWordsTextField.setText(b.getKeywords());
		typeTextField.setText(b.getType());
		authlabel.setText(b.getAuthor().getForename());
		publisherLabel.setText(b.getPublisher().getName());

		if (b.getPublicationDate() != null) {
			Calendar cal = null;
			cal = Calendar.getInstance();
			cal.setTime(b.getPublicationDate());
			yearTextField.setText(String.valueOf(cal.get(Calendar.YEAR)));
		}
	}

	@Override
	public void setVisible(boolean visible) {
		if (!visible) {
			setData(new Book());
			verifier.reset();
		}
		super.setVisible(visible);
	}
}
