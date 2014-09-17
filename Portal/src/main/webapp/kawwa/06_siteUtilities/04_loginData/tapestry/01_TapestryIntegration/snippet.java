@SessionState
private User loggedUser;

@Property
private Boolean loggedUserExists; 

@OnEvent(value=EventConstants.ACTION, component="logout")
public void logout(){
	
	loggedUser = null;
	
}