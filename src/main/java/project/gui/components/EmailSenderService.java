package project.gui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import project.model.Lend;

@Service
public class EmailSenderService {
	private JavaMailSenderImpl sender;
	private String subject="Wisisz ksi¹¿kê";
	private String message="Witaj %s %s. \nPrzypominamy o oddaniu ksi¹¿ki %s wypo¿yczonej w dniu %s";

	@Autowired
	public EmailSenderService(JavaMailSenderImpl sender) {
		this.sender = sender;
	}

	public void sendNotify(Lend l) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(l.getReader().getEmail());
		message.setSubject(subject);
		message.setText(parseMessage(l));
		sender.send(message);
	}

	private String parseMessage(Lend l) {
		return String.format(message, l.getReader().getForname(),l.getReader().getSurname(),l.getBook().getTitle(),l.getLendDate());
	}
}
