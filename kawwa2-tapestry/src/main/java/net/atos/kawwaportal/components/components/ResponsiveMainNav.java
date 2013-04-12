package net.atos.kawwaportal.components.components;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;


@Import(library = {"classpath:/net/atos/kawwaportal/components/assets/flexnav.js", 
		"classpath:/net/atos/kawwaportal/components/assets/kawwa.js"})
@SupportsInformalParameters
public class ResponsiveMainNav {


	@Inject
	private JavaScriptSupport js;
	
	@Inject
	private ComponentResources cr;
	
	@Property
	private String id;
	
	@Parameter(name="control")
	private Block pcontrol;
	@Parameter(name="skip")
	private Block pskip;
	@Parameter(name="items")
	private Block pitems;
	
	@Inject 
	private Block control;
	
	@Inject 
	private Block skip;
	
	@Inject 
	private Block items;
	
	
	public Block getDcontrol() {
		return cr.isBound("control") ? pcontrol : control;	
	}
	public Block getDskip() {
		return cr.isBound("skip") ? pskip : skip;
	}
	public Block getDitems() {
		return cr.isBound("items") ? pitems: items;
	}
	/**
	 * Force the ID attribute for the main ul tag
	 */
	@SetupRender
	public void setupRender(MarkupWriter writer) {
		
		id = js.allocateClientId(cr);
		
	}
	/**
	 * We set an id for the <ul> tag in order to use it in our jQuery selector.
	 */
	@AfterRender
	public void setJs(MarkupWriter writer){
		
		js.addInitializerCall("responsiveMainNav", new JSONObject("id", id));
	}
}
