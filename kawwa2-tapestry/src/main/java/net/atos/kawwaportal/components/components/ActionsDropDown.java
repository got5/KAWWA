package net.atos.kawwaportal.components.components;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.atos.kawwaportal.components.KawwaEventsConstants;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;



@SupportsInformalParameters
@Import(library = {"classpath:/net/atos/kawwaportal/components/assets/actiondropdown/jquery.kawwa.actionsDd.js",
		"classpath:/net/atos/kawwaportal/components/assets/actiondropdown/actionsdropdown.js"})
public class ActionsDropDown {
	
	/**
	 * The title of your dropdown menu
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, value="message:actionsdropdown_title")
	private String title;
	
	/**
	 * The List of items your dropdown menu should display. 
	 */
	@Property
	@Parameter
	private Map<String, List<String>> items;
	
	@Property
	private String id;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
    private ComponentResources resources;
	
	@Property private String key;
	
	@Property private Set<String> keys;
	
	@Property private String item;
	
	@Inject private Block defaultBlock; 
	
	@SetupRender
	public void setupRender() {
		id = javaScriptSupport.allocateClientId(resources);
		keys = items.keySet();
	}
	
	@BeginRender
	public void beginRender(MarkupWriter writer){
		writer.element("div", "id", id, "class", "k-actions-dropdown", "role", "listbox");
		writer.element("button", "aria-haspopup", "true");
		writer.write(title);
		writer.end();
		
		writer.element("div",  "class", "content");
	}
	
	
	@AfterRender
    private void afterRender(MarkupWriter writer) {
    	
		writer.end();
		writer.end();
    	JSONObject option = new JSONObject();
    	option.put("id", id);
	    
    	javaScriptSupport.addInitializerCall("actionsdropdown", option);
    }
	
	public Block getItemBlock(){
		Block override = resources.getBlockParameter(String.format("%s_%s", getCamelKey(), getCamelItem()));
		
		return (override != null) ? override : defaultBlock;
		
	}
	
	public static String camelCase(String... args) {
		if (args == null || args.length == 0) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		buf.append(args[0]);
		for (int i = 1; i < args.length; i++) {
			String arg = args[i];
			if (arg != null && arg.length() > 0) {
				buf.append(Character.toUpperCase(arg.charAt(0)));
				buf.append(arg.substring(1));
			}
		}
		return buf.toString();
	}
	
	public String getCamelKey(){
		return camelCase(key.split("\\s+"));
	}
	public String getCamelItem(){
		return camelCase(item.split("\\s+"));
	}
	public String getUrl(){
		return resources.createEventLink(KawwaEventsConstants.ACTIONSDROPDOWN_SELECT, getCamelKey(), getCamelItem()).toAbsoluteURI();
	}
	
}
