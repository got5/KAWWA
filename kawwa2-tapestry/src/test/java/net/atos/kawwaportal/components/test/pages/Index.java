	package net.atos.kawwaportal.components.test.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.StreamPageContent;


@Import(stylesheet="context:s.css")
public class Index {
	@Property private Integer min = 0;
	@Property private Integer max = 50;
	@Property
	private JSONObject rangeParams;
	

	
	@OnEvent(EventConstants.ACTIVATE)
	public void initRangeSlider(){
	    rangeParams = new JSONObject();
	    JSONArray values = new JSONArray();
	    values.put(min);
	    values.put(max);
	    rangeParams.put("values", values);
	    rangeParams.put("min", 0);
	    rangeParams.put("max", 500);
	}
	
	public Object onAction(){
		return new StreamPageContent(Accordion.class);
	}
}
