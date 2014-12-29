package net.atos.kawwaportal.components.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.AfterRenderTemplate;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * 
 * @tapestrydoc
 * @component_version 1.1
 *
 */
public class CollapsiblePanel {
	
	/**
	 * The JSON Object for jQuery panel widget
	 */
	@Parameter(defaultPrefix = BindingConstants.PROP)
	private JSONObject options;
	
	/**
	 * The text for the header of your title
	 */
	@Parameter(defaultPrefix = BindingConstants.MESSAGE)
	private String header;
	
	/**
	 * autoOpen define if your panel have to be opened by default
	 */
	@Parameter(defaultPrefix = BindingConstants.PROP)
	private Boolean autoOpen;
	
	/**
	 * The value of the title attribute of the <a> tag, in the header of your panel
	 */
	@Parameter(value = "message:panel-title", defaultPrefix = BindingConstants.MESSAGE)
	private String title;
	
	
	/**
	 * The Header of your Collapsible Panel
	 */
	@Parameter(defaultPrefix = BindingConstants.BLOCK)
	private Block headerBlock;
	
	@Inject
	private Block defaultHeaderBlock;
	
	@Environmental
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
	private ComponentResources componentResources;
	
	private String clientId;
	
	@SetupRender
	public void generateId(){
		clientId = javaScriptSupport.allocateClientId(componentResources);
	}
	/**
	 * If autoOpen=true we add the ui-state-active class
	 */
	@AfterRenderTemplate
	public void addClass(MarkupWriter writer){
		
		if(componentResources.isBound("autoOpen")){
		
			if(autoOpen){ 
				writer.getDocument().getElementById(getClientId()).getElement(new Predicate<Element>() {
					
					public boolean accept(Element object) {
						if(InternalUtils.isBlank(object.getAttribute("class"))) return false;
						return object.getAttribute("class").contains("control");
					}
				}).attribute("class",  "control ui-state-active");
			}
		}
	}
	
	/**
	 * Init the Javascript code
	 */
	@AfterRender
	public void initComponent(MarkupWriter writer){	
		
		JSONObject params = new JSONObject("id", getClientId());
		
		params.put("options", options);
		
		javaScriptSupport.require("kawwa/panel").with(params);
	}
	
	public String getClientId(){
		return clientId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getHeader(){
		return header;
	}
	
	public Block getBlock(){
		
		if(componentResources.isBound("headerBlock")) return headerBlock;
		return defaultHeaderBlock;
	}
}
