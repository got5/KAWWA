package net.atos.kawwaportal.components.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;


/**
 * 
 * @tapestrydoc
 * @component_version 1.0
 *
 */
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
		
		js.require("kawwa/responsiveMainNav").with(new JSONObject("id", id));
	}
}
