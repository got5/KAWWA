package awl.frontsolutions.services.atos;

import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import awl.frontsolutions.services.MailService;

public class AtosMailImpl implements MailService {

	@Override
	public void sendMailForSubscribingToNewsletter(String from) {
		
	}
	
	@Override
	public void sendMailToAdmin(String from, String subject, String body) {

		sendEmail(from, "dl-kawwa@atos.net", subject, body);
	}
	
	@Override
	public void sendMailToDev(String subject, String body) {

		sendEmail("emmanuel.demey@atos.net", "emmanuel.demey@atos.net", "[KAWWA2 - @DEV MAIL] " + subject, body);
	}
	
	private void sendEmail(String from, String toA, String subject, String body) {
		try {
			
			Properties prop = System.getProperties();
			
			prop.put("mail.smtp.host", "mailhost.priv.atos.fr");
			
			Session session = Session.getInstance(prop, null);
			
			MimeMessage mailMessage = new MimeMessage(session);

			mailMessage.setFrom(new InternetAddress(from));
		
			InternetAddress to = null;
			
			StringTokenizer tokenizer = new StringTokenizer(toA, ",");
			while (tokenizer.hasMoreTokens()) {
				to = new InternetAddress(tokenizer.nextToken());
				mailMessage.addRecipient(Message.RecipientType.BCC, to);
			}
			
			mailMessage.setSubject(subject);
			
			mailMessage.setText(body);
			
			Transport.send(mailMessage);
		} catch (Exception e) {
			
		}
	}
}
