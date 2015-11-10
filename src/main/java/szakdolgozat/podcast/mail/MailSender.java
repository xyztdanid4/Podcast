package szakdolgozat.podcast.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private static MailSender instance = null;

	private MailSender() {
		final String to = "abcd@gmail.com";
		final String from = "web@gmail.com";
		final String host = "localhost";
		final Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		final Session session = Session.getDefaultInstance(properties);
		try {
			final MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("This is the Subject Line!");
			message.setText("This is actual message");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (final MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	public void send() {

	}

}
