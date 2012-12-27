package awl.frontsolutions.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.MailService;

@Import(stack="themestack")
public class Contact
{
	@Property
	@Validate(value="required")
	private String bodycontact;
	
	@Property
	@Validate(value="required")
	private String namecontact;
	
	@Property
	@Validate(value="required,email")
	private String emailcontact;
	
	@Inject
	private MailService mail;
	
	public void onSubmit(){
		StringBuilder sb = new StringBuilder();
		sb.append("Name : " + namecontact).append("\n");
		sb.append("Message : ").append("\n");
		sb.append(bodycontact);
		
		mail.sendMailToAdmin(emailcontact, "Contact Form", sb.toString());
	}
}
