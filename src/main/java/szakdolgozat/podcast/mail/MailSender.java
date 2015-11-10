package szakdolgozat.podcast.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import szakdolgozat.podcast.basicinformation.InformationContainer;

public class MailSender {
	private static final String PODCAST_UPDATE = "Podcast Update";
	private static final String NO_REPLY_PODCAST_COM = "no-reply@podcast.com";
	private static final String PASSWORD = "Xyzdopio4";
	private static final String USERNAME = "varacskosmokus@gmail.com";
	private static final String REGARDS = "\n\nRegards!";
	private static final String DEAR_SUBSCRIBER_YOU_HAVE_UPDATES = "Dear"
			+ InformationContainer.getInstance().getOwner() + "!\n\nYou have updates!\n\n";
	private static MailSender instance = null;

	private MailSender() {

	}

	public static MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	public void send(final String to, final String text) {
		final Properties props = new Properties();
		setUpPorperties(props);
		final Session session = authenticate(props);
		sendMail(session, to, text);
	}

	private Session authenticate(final Properties props) {
		final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		return session;
	}

	private void setUpPorperties(final Properties props) {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	private void sendMail(final Session session, final String to, final String text) {
		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(NO_REPLY_PODCAST_COM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(PODCAST_UPDATE);
			message.setText(DEAR_SUBSCRIBER_YOU_HAVE_UPDATES + text + REGARDS);
			Transport.send(message);

		} catch (final MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
