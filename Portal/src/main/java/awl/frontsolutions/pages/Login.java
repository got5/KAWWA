package awl.frontsolutions.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

import awl.frontsolutions.services.Authentification;


@Import(stack = "themestack")
@Secure
public class Login {

	@Property
	private String das, password;

	@SessionState
	private Link comingFromPage;
	
	@InjectComponent
	private Form loginForm;

	@Inject
	private Authentification auth;

	@OnEvent(value = EventConstants.VALIDATE, component = "loginForm")
	private void validation() {
		String message = auth.authentificate(das, password);
		if (InternalUtils.isNonBlank(message))
			loginForm.recordError(message);
	}

	@OnEvent(value = EventConstants.SUCCESS, component = "loginForm")
	private Object saveUserInSession() {
		return "Index";
	}

	public String getCurrentYear() {
		DateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date());
	}
}