package project.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.config.AppConfig;
import project.gui.dialogs.settingsDialogs.ConnectionDialog;
import project.gui.frame.MainFrame;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) throws Exception {
		try {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
			// test connection

			MainFrame frame = context.getBean(MainFrame.class);
			frame.setVisible(true);
		} catch (Exception e) {

			String message = "";
			if (e instanceof SQLException) {
				message = "B³êdne dane logowania";
			} else if (e instanceof IOException) {
				message = "Brak danych logowania";
			}

			ConnectionDialog con = new ConnectionDialog(true);			
			con.setVisible(true);
		}
	}
}
