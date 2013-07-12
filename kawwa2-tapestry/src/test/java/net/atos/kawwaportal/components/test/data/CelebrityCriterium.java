package net.atos.kawwaportal.components.test.data;

import java.util.Date;

/**
 * Used to filter the items presented in the kawwa grid.
 * 
 * @author a136316
 * 
 */
public class CelebrityCriterium {
	

	private String firstName;

	private String lastName;
	
	private Long id;
	
	private String country;
	
	private Date  from;
	
	private Date to;
	

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		
		// Create the filter query string
		StringBuffer buffer = new StringBuffer();
		if (id != null) {
			buffer.append(this.id);
		} else {
			buffer.append(MockDataSource.noValuebyClient);
		}
		buffer.append(",");
				
		if (firstName != null) {
			buffer.append(this.firstName);
		} else {
			buffer.append(MockDataSource.noValuebyClient);
		}
		buffer.append(",");

		if (lastName != null) {
			buffer.append(this.lastName);
		} else {
			buffer.append(MockDataSource.noValuebyClient);
		}
		
		return buffer.toString();
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}


}

