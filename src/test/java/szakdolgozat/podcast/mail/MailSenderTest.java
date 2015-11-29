package szakdolgozat.podcast.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class MailSenderTest {

	@Before
	public void create() {

	}

	@Test
	public void instanceTest() {
		assertNotNull(MailSender.getInstance());
	}

	@Test
	public void setUpPropertiesTest() {
		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		assertEquals(props.isEmpty(), false);
	}

	@Test
	public void authenticateTest() {
		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("varacskosmokus@gmail.com", "Xyzdopio4");
			}
		});
		assertNotNull(session);
	}

	@Test
	public void sendTest() throws AddressException, MessagingException {
		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("varacskosmokus@gmail.com", "Xyzdopio4");
			}
		});
		final Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("no-reply@podcast.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("varacskosmokus@gmail.com"));
		message.setSubject("Podcast Update");
		message.setText("test");
		Transport.send(message);
	}

	@After
	public void destroy() {

	}
}
