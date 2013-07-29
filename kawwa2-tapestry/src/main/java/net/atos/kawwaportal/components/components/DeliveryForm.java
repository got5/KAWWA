package net.atos.kawwaportal.components.components;

import java.util.List;

import net.atos.kawwaportal.components.data.DeliveryMode;
import net.atos.kawwaportal.components.data.GMapSelectLocation;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.internal.services.StringValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>
 * 		This Component is used to generate a delivery form that will provide:
 * 		<ul>
 * 			<li>A delivery mode radio list</li>
 * 			<li>A delivery location radio list</li>
 * 			<li>A google map linked to the location radio list</li>
 * 		</ul>
 * </p>
 * <p>
 * 		To use it, call the component as the following:
 * 		<br/>
 * 		"&lt;div t:type="kawwa2/DeliveryForm" t:currentDeliveryModesList="&lt;&lt;1&gt;&gt;"
 *			t:deliveryModeSelected="prop:&lt;&lt;2&gt;&gt;" t:deliveryLocationSelected="prop:&lt;&lt;3&gt;&gt;"
 *			t:gMapParameters="prop:&lt;&lt;4&gt;&gt;"/&gt;"
 *		<br/><br/>
 *		The fields must be filled with:
 *		<ul>
 *			<li>&lt;&lt;1&gt;&gt; : The list of deliveryMode objects</li>
 *			<li>&lt;&lt;2&gt;&gt; : The name of the mode reference variable returned on submit</li>
 *			<li>&lt;&lt;3&gt;&gt; : The name of the location reference variable returned on submit</li>
 *			<li>&lt;&lt;4&gt;&gt; : The optional JSON Object that will parameter the gmap plugin</li>
 *		</ul>
 * </p>
 * 
 * @tapestrydoc
 */

@Import(library = "classpath:net/atos/kawwaportal/components/assets/deliveryform/DeliveryForm.js")
public class DeliveryForm {
	
	@Parameter(required = true)
	@Property
	private List<DeliveryMode> currentDeliveryModesList;
	
	@Parameter
	@Property
	@SuppressWarnings("unused")
	private JSONObject gMapParameters;
	
	@Property
	private List<GMapSelectLocation> currentDeliveryLocationsList;
	
	@Property
	@SuppressWarnings("unused")
	private DeliveryMode currentDelieveryMode;
	
	@Parameter
	@Property
	@SuppressWarnings("unused")
	private String deliveryModeSelected;
	
	@Parameter
	@Property
	@SuppressWarnings("unused")
	private String deliveryLocationSelected;
	
	@Property
	@SuppressWarnings("unused")
	private final StringValueEncoder stringEncoder = new StringValueEncoder();
	
	@InjectComponent
	private RadioGroup deliveryModeRadio;
		
	@InjectComponent
	private Zone deliveryDropOffZone;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
    private ComponentResources resources;	
	

	@SetupRender
	public void setupRender() {
		currentDeliveryLocationsList = currentDeliveryModesList.get(0).getDeliveryLocationsList();
	}
	
	@AfterRender
	public void afterRender() {	
		
		String modeName = deliveryModeRadio.getControlName();
		String zoneId = deliveryDropOffZone.getClientId();
		Link link = resources.createEventLink("serveDeliveryModeSelected");
		
		JSONObject opt = new JSONObject();
	    opt.put("modeName", modeName);
	    opt.put("zoneId", zoneId);
	    opt.put("link", link.toURI());
	    
		javaScriptSupport.addInitializerCall("deliveryform", opt);
		
	}
	
	@OnEvent(value="serveDeliveryModeSelected")
	public Object deliveryModeSelected(@RequestParameter(value = "deliveryModeSelectedRef", allowBlank = false) String deliveryModeSelectedRef) {
		
		for (DeliveryMode currentDeliveryMode : currentDeliveryModesList) {
			
			if(currentDeliveryMode.getRef().equals(deliveryModeSelectedRef)) {
				
				deliveryModeSelected = deliveryModeSelectedRef;
				
				if(currentDeliveryMode.getDeliveryLocationsList() != null && currentDeliveryMode.getDeliveryLocationsList().size() != 0) {
					
					currentDeliveryLocationsList = currentDeliveryMode.getDeliveryLocationsList();
				}
				else {
					
					currentDeliveryLocationsList = null;
				}
			}	
		}
		
		return deliveryDropOffZone.getBody();
	}
	
	public boolean getLocationsListSetted() {
		
		return currentDeliveryLocationsList != null;
	}
}
