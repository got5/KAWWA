package awl.frontsolutions.entities;

public class DASUser {

	
	
	public DASUser(String login, Boolean guest) {
		super();
		this.login = login;
		this.Guest = guest;
	}


	private String login;
	
	private Boolean Guest;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getGuest() {
		return Guest;
	}
	
	public void setGuest(Boolean guest) {
		Guest = guest;
	}
	
}
