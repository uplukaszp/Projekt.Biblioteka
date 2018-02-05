package project.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.gui.dialogs.settingsDialogs.ConnectionDialog;
import project.gui.frame.MainFrame;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		System.out.println("Start app");
		try {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
			System.out.println("after context get");
			// test connection

			MainFrame frame = context.getBean(MainFrame.class);
			frame.setVisible(true);
		} catch (Exception e) {
			ConnectionDialog con = new ConnectionDialog();
			con.setVisible(true);
			String message = "";
			if (e instanceof SQLException) {
				message = "B��dne dane logowania";
			} else if (e instanceof IOException) {
				message = "Brak danych logowania";
			} else
				message = e.getLocalizedMessage();
			JOptionPane.showMessageDialog(null, message);
		}
	}
}