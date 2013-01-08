@Property
@Validate(value="required")
private String login;

@Property
@Validate(value="required")
private String password;

@Component(id="loginForm")
private Form loginForm;

@Inject
private Messages messages;

/*@Inject
private your.business.package.UserManager userManager;*/

@OnEvent(value=EventConstants.VALIDATE, component="loginForm")
public void validation(){
	//Test the values of login and password, or pass the values to your UserManager
	//if(!userManager.successfullyAuthenticates(login, password)){
	if(!"tapestry".equals(login) || !"tapestry".equals(password)){
		String errorMessage = "Wrong login or password";
		if(messages.contains("wrong-login-password"))
			errorMessage = messages.get("wrong-login-password");
		loginForm.recordError(errorMessage);
	}
}

/*
 * This method is only called if you've NOT used the "recordError" method exposed by the Form component
 */
@OnEvent(value=EventConstants.SUCCESS, component="loginForm")
public void loggingSuccess(){
	//save the user in the session
}

/*
 * This method is only called if you've used the "recordError" method exposed by the Form component 
 */
@OnEvent(value=EventConstants.FAILURE, component="loginForm")
public void loggingFailure(){
	//increment a persistent counter to limit the number of tries
}

/*
 * This method is always called 
 */
@OnEvent(value=EventConstants.SUBMIT, component="loginForm")
public void loggingSubmit(){
	//
}