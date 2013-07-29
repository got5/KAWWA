package net.atos.kawwaportal.components.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;


/**
 * 
 * @tapestrydoc
 *
 */
@Import(library = {"classpath:/net/atos/kawwaportal/components/assets/superfish.js", 
		"classpath:/net/atos/kawwaportal/components/assets/kawwa.js"})
public class MainNav {


	@Inject
	private JavaScriptSupport js;
	
	@Inject
	private ComponentResources cr;
	
	@Property
	private String id;
	
	/**
	 * Force the ID attribute for the main ul tag
	 */
	@SetupRender
	public void setupRender(MarkupWriter writer) {
		
		id = js.allocateClientId(cr);
		
		writer.element("ul", "id", id);
		
	}
	/**
	 * We set an id for the <ul> tag in order to use it in our jQuery selector.
	 */
	@AfterRender
	public void setJs(MarkupWriter writer){
		
		cr.renderInformalParameters(writer);
		writer.end();
		js.addInitializerCall("mainNav", new JSONObject("id", id));
	}
}
