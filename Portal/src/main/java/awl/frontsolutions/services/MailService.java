package awl.frontsolutions.services;


public interface MailService {
	public void sendMailForSubscribingToNewsletter(String from);
	public void sendMailToAdmin(String from, String subject, String body);
	public void sendMailToDev(String subject, String body);
}
