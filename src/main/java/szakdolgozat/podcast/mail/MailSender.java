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

/**
 * The Class MailSender.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MailSender {

	/** The Constant PODCAST_UPDATE. */
	private static final String PODCAST_UPDATE = "Podcast Update";

	/** The Constant NO_REPLY_PODCAST_COM. */
	private static final String NO_REPLY_PODCAST_COM = "no-reply@podcast.com";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = "Xyzdopio4";

	/** The Constant USERNAME. */
	private static final String USERNAME = "varacskosmokus@gmail.com";

	/** The Constant REGARDS. */
	private static final String REGARDS = "\n\nRegards!";

	/** The Constant DEAR_SUBSCRIBER_YOU_HAVE_UPDATES. */
	private static final String DEAR_SUBSCRIBER_YOU_HAVE_UPDATES = "Dear "
			+ InformationContainer.getInstance().getOwner() + "!\n\nYou have updates!\n\n";

	/** The instance. */
	private static MailSender instance = null;

	/**
	 * Instantiates a new mail sender.
	 */
	private MailSender() {

	}

	/**
	 * Gets the single instance of MailSender.
	 *
	 * @return single instance of MailSender
	 */
	public static MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	/**
	 * Send.
	 *
	 * @param to
	 *            the to
	 * @param text
	 *            the text
	 */
	public void send(final String to, final String text) {
		final Properties props = new Properties();
		setUpPorperties(props);
		final Session session = authenticate(props);
		sendMail(session, to, text);
	}

	/**
	 * Authenticate.
	 *
	 * @param props
	 *            the props
	 * @return the session
	 */
	private Session authenticate(final Properties props) {
		final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		return session;
	}

	/**
	 * Sets the up porperties.
	 *
	 * @param props
	 *            the new up porperties
	 */
	private void setUpPorperties(final Properties props) {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	/**
	 * Send mail.
	 *
	 * @param session
	 *            the session
	 * @param to
	 *            the to
	 * @param text
	 *            the text
	 */
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
