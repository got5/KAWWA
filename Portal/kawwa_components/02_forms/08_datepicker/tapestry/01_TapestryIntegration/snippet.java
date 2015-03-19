import java.util.Date;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;

public class DateField {

	@Property
	private Date date1;
	
	@Inject
	private AssetSource as;
	
	public JSONObject getOptions(){
		
		return new JSONObject()
				.put("showOn", "button")
				.put("buttonImage", as.getClasspathAsset("${kawwa2.img-path}/pic_calendar.gif").toClientURL())
				.put("buttonImageOnly", true)
				.put("buttonText", "Click to open/close the calendar");

	}
}
