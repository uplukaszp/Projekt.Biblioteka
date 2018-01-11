package project.gui.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.gui.dialogs.updateDialogs.AuthorDialog;
import project.gui.dialogs.updateDialogs.PublisherDialog;
import project.gui.dialogs.updateDialogs.ReaderDialog;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	@Autowired
	private AuthorDialog authdialog;
	@Autowired
	private ReaderDialog readdialog;
	@Autowired
	private PublisherDialog publidialog;

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
				
			}
		});
		mnPrzegldaj.add(mntmAutorw);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}