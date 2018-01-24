package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

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
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

@Component
public class BookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField titleTextField;
	private JTextField yearTextField;
	private JTextField isbnTextField;
	private JTextField keyWordsTextField;
	private JTextField typeTextField;
	
	@Autowired
	private BookRepository repo;
	
	
	@Autowired
	private ShowAuthorDialog showauthdialog;
	@Autowired
	private ShowPublisherDialog showpubdialog;

	private Book b=new Book();
	private JLabel authlabel;
	private JLabel publisherLabel;
	
	public BookDialog() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 306, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		titleTextField = new JTextField();
		titleTextField.setColumns(10);
		
		yearTextField = new JTextField();
		yearTextField.setColumns(10);
		
		isbnTextField = new JTextField();
		isbnTextField.setColumns(10);
		
		keyWordsTextField = new JTextField();
		keyWordsTextField.setColumns(10);
		
		typeTextField = new JTextField();
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
				showauthdialog.setVisible(true);
				b.setAuthor(showauthdialog.getAuthor());
				authlabel.setText(b.getAuthor().getForename());
			}
		});
		
		JButton btnNewButton_1 = new JButton("Przegl\u0105daj");
		btnNewButton_1.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				showpubdialog.setVisible(true);
				b.setPublisher(showpubdialog.getPublisher());
				publisherLabel.setText(b.getPublisher().getName());
			}
		});
		
		authlabel = new JLabel("Nie wybrano");
		
		publisherLabel = new JLabel("Nie wybrano");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTytu)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblSowaKluczowe)
						.addComponent(lblGatunek)
						.addComponent(lblAutor)
						.addComponent(lblWydawnictwo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(isbnTextField, Alignment.TRAILING, 137, 182, Short.MAX_VALUE)
						.addComponent(typeTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(keyWordsTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(yearTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(titleTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(authlabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(publisherLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTytu))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(yearTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(isbnTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(keyWordsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSowaKluczowe))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGatunek))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(lblAutor)
						.addComponent(authlabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(lblWydawnictwo)
						.addComponent(publisherLabel))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SimpleDateFormat formatter=new SimpleDateFormat("YYYY");
						try {
							java.util.Date date=formatter.parse(yearTextField.getText());
							java.sql.Date dbDate=new java.sql.Date(date.getTime());
							if(b.getId()==0)b.setStatus(BookStatus.available);
							else b.setStatus(b.getStatus());
							b.setISBN(isbnTextField.getText());
							b.setKeywords(keyWordsTextField.getText());
							b.setPublicationDate(dbDate);
							b.setTitle(titleTextField.getText());
							b.setType(typeTextField.getText());
							if(b.getId()==0)repo.addBook(b);
							else repo.updateBook(b);
							setVisible(false);
						} catch (ParseException e1) {
							System.out.println(e1.getMessage());
						}
						
						
					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void setBook(Book b)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(b.getPublicationDate());
		
		this.b=b;
		titleTextField.setText(b.getTitle());
		yearTextField.setText(String.valueOf(cal.get(Calendar.YEAR)));
		isbnTextField.setText(b.getISBN());
		keyWordsTextField.setText(b.getKeywords());
		typeTextField.setText(b.getType());
		authlabel.setText(b.getAuthor().getForename());
		publisherLabel.setText(b.getPublisher().getName());
	
	}
}
