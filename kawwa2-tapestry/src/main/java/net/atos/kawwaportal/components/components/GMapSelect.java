package net.atos.kawwaportal.components.components;

import java.util.List;

import net.atos.kawwaportal.components.data.GMapSelectLocation;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.internal.services.StringValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>
 * 		This Component is used to generate a location form that will provide:
 * 		<ul>
 * 			<li>A delivery location radio list</li>
 * 			<li>A google map linked to the location radio list</li>
 * 		</ul>
 * </p>
 * <p>
 * 		To use it, call the component as the following:
 * 		<br/>
 * 		"&lt;div t:type="kawwa2/GMapSelect" t:currentGMapSelectLocationsList="&lt;&lt;1&gt;&gt;"
 * 			t:gMapParameters="prop:&lt;&lt;2&gt;&gt;"/&gt;"
 * 			t:gMapSelectLocationSelected="prop:&lt;&lt;3&gt;&gt;"/&gt;"
 *		<br/><br/>
 *		The fields must be filled with:
 *		<ul>
 *			<li>&lt;&lt;1&gt;&gt; : The list of GMapSelectLocation objects</li>
 *			<li>&lt;&lt;2&gt;&gt; : The optional JSON Object that will parameter the gmap plugin</li>
 *			<li>&lt;&lt;3&gt;&gt; : The name of the location reference variable returned on submit</li>
 *		</ul>
 * </p>
 */

@Import(library = "classpath:net/atos/kawwaportal/components/assets/gmapselect/GMapSelect.js")
public class GMapSelect {
	
	@Parameter(required = true)
	@Property
	private List<GMapSelectLocation> currentGMapSelectLocationsList;
	
	@Parameter
	@Property
	@SuppressWarnings("unused")
	private String gMapSelectLocationSelected;
	
	@Parameter
	@Property
	@SuppressWarnings("unused")
	private JSONObject gMapParameters;
	
	@Property
	@SuppressWarnings("unused")
	private GMapSelectLocation currentGMapSelectLocation;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Property
	@SuppressWarnings("unused")
	private final StringValueEncoder stringEncoder = new StringValueEncoder();
	
	@InjectComponent
	private RadioGroup gMapSelectLocationRadio;
	
	@InjectComponent
	private GMap gmap;
	
	@AfterRender
	public void afterRender() {
		
		String locationName = gMapSelectLocationRadio.getControlName();
		
		String gmapId = gmap.getClientId();
		
		JSONArray gmapMarkers = new JSONArray();
		
		for (GMapSelectLocation currentGMapSelectLocation : currentGMapSelectLocationsList) {
			JSONObject address = new JSONObject();
			address.put("address", currentGMapSelectLocation.getAddress());
			address.put("data", currentGMapSelectLocation.getAddress());
			address.put("tag", currentGMapSelectLocation.getRef());
			gmapMarkers.put(address);
		}
		
		JSONObject opt = new JSONObject();
	    opt.put("locationName", locationName);
	    opt.put("gmapId", gmapId);
	    opt.put("gmapMarkers", gmapMarkers);
	    
		javaScriptSupport.addInitializerCall("gmapselect", opt);
	}
}
