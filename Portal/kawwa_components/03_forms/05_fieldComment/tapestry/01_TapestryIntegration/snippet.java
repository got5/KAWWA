package net.atos.kawwaportal.components.test.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;

public class Form {
	
	@Property
	private String value;
	
	public JSONObject getOptions(){
		return new JSONObject("gravity", "n");
		
	}
}
