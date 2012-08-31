package net.atos.kawwaportal.components.test.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;

public class Tipsy {
	@Property
	private String value;
	@Property
	private String values;
	
	public JSONObject getOptions(){
		return new JSONObject("gravity", "n", "trigger", "focus");
		
	}
}
