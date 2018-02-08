package project.gui.frame;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.dialogs.selectDialogs.ShowAuthorDialog;
import project.gui.dialogs.selectDialogs.ShowBookDialog;
import project.gui.dialogs.selectDialogs.ShowLendDialog;
import project.gui.dialogs.selectDialogs.ShowPublisherDialog;
import project.gui.dialogs.selectDialogs.ShowReaderDialog;
import project.gui.dialogs.settingsDialogs.ConnectionDialog;
import project.gui.dialogs.settingsDialogs.EmailSettingsDialog;
import project.gui.dialogs.settingsDialogs.LendSettingsDialog;
import project.gui.dialogs.updateDialogs.AuthorDialog;
import project.gui.dialogs.updateDialogs.BookDialog;
import project.gui.dialogs.updateDialogs.LendDialog;
import project.gui.dialogs.updateDialogs.PublisherDialog;
import project.gui.dialogs.updateDialogs.ReaderDialog;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@Component
public class MainFrame extends JFrame {

	private JPanel contentPane;

	@Autowired
	private AuthorDialog authdialog;
	@Autowired
	private ReaderDialog readdialog;
	@Autowired
	private PublisherDialog publidialog;
	@Autowired
	private BookDialog bookdialog;
	@Autowired
	private ShowAuthorDialog showauthdialog;
	@Autowired
	private ShowPublisherDialog showpubdialog;
	@Autowired
	private ShowReaderDialog showreaddialog;
	@Autowired
	private ShowBookDialog showbookdialog;
	@Autowired
	private LendDialog lendDialog;
	@Autowired
	private ShowLendDialog showLendDialog;
	@Autowired
	private ConnectionDialog connectiondialog;
	@Autowired
	private LendSettingsDialog lendsettingsdialog;
	@Autowired
	private EmailSettingsDialog emailsettingsdialog;
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);

		JMenuItem mntmZakocz = new JMenuItem("Zako\u0144cz");
		mntmZakocz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		
		JMenuItem mntmUstawieniaPoczenia = new JMenuItem("Ustawienia po\u0142\u0105czenia");
		mntmUstawieniaPoczenia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectiondialog.setVisible(true);
			}
		});
		mnProgram.add(mntmUstawieniaPoczenia);
		
		JMenuItem mntmUstawieniaPoczty = new JMenuItem("Ustawienia poczty");
		mntmUstawieniaPoczty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailsettingsdialog.setVisible(true);
			}
		});
		mnProgram.add(mntmUstawieniaPoczty);
		
		JSeparator separator = new JSeparator();
		mnProgram.add(separator);
		mnProgram.add(mntmZakocz);

		JMenu mnDodaj = new JMenu("Dodaj");
		menuBar.add(mnDodaj);

		JMenuItem mntmCzytelnika = new JMenuItem("Czytelnika");
		mntmCzytelnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readdialog.setVisible(true);
			}
		});
		mnDodaj.add(mntmCzytelnika);

		JMenuItem mntmKsik = new JMenuItem("Ksi\u0105\u017Ck\u0119");
		mntmKsik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookdialog.setVisible(true);
			}
		});
		mnDodaj.add(mntmKsik);

		JMenuItem mntmAutora = new JMenuItem("Autora");
		mntmAutora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authdialog.setVisible(true);
			}
		});
		mnDodaj.add(mntmAutora);

		JMenuItem mntmWydawnictwo = new JMenuItem("Wydawnictwo");
		mntmWydawnictwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publidialog.setVisible(true);
			}
		});
		mnDodaj.add(mntmWydawnictwo);

		JMenu mnPrzegldaj = new JMenu("Przegl\u0105daj");
		menuBar.add(mnPrzegldaj);

		JMenuItem mntmAutorw = new JMenuItem("Autor\u00F3w");
		mntmAutorw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showauthdialog.setSelectMode(false);
				showauthdialog.setVisible(true);
			}
		});
		mnPrzegldaj.add(mntmAutorw);

		JMenuItem mntmWydawnictwa = new JMenuItem("Wydawnictwa");
		mntmWydawnictwa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showpubdialog.setSelectMode(false);
				showpubdialog.setVisible(true);
			}
		});
		mnPrzegldaj.add(mntmWydawnictwa);

		JMenuItem mntmCzytelnikw = new JMenuItem("Czytelnik\u00F3w");
		mntmCzytelnikw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showreaddialog.setSelectMode(false);
				showreaddialog.setVisible(true);
			}
		});
		mnPrzegldaj.add(mntmCzytelnikw);

		JMenuItem mntmKsiki = new JMenuItem("Ksi\u0105\u017Cki");
		mntmKsiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showbookdialog.setSelectMode(false);
				showbookdialog.setVisible(true);
			}
		});
		mnPrzegldaj.add(mntmKsiki);

		JMenu mnWypoyczenia = new JMenu("Wypo\u017Cyczenia");
		menuBar.add(mnWypoyczenia);

		JMenuItem mntmNewMenuItem = new JMenuItem("Wypo\u017Cycz");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lendDialog.setVisible(true);
			}
		});
		mnWypoyczenia.add(mntmNewMenuItem);

		JMenuItem mntmPrzegldaj = new JMenuItem("Przegl\u0105daj");
		mntmPrzegldaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLendDialog.setVisible(true);
			}
		});
		mnWypoyczenia.add(mntmPrzegldaj);
		
		JMenuItem mntmOpcje = new JMenuItem("Opcje");
		mntmOpcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lendsettingsdialog.setVisible(true);
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnWypoyczenia.add(separator_1);
		mnWypoyczenia.add(mntmOpcje);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
