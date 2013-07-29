package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

/**
 * 
 * @tapestrydoc
 *
 */
@Import(library = "classpath:/net/atos/kawwaportal/components/assets/readMore.js")
public class ReadMore {
	
	@Parameter(value = "message:read-more-header", defaultPrefix = BindingConstants.MESSAGE)
	private String header;
	
	@InjectContainer
	private ClientElement clientElement;
	
    
	@Inject
	@Symbol(JQuerySymbolConstants.JQUERY_ALIAS)
	private String jqueryAlias;
	
	@Inject 
	private JavaScriptSupport javascriptSupport;
	
	@AfterRender
	void afterRender() {
		JSONObject params = new JSONObject();
		params.accumulate("header", header);
		String init = null;
		init = String.format("%s('#%s').readMore(%s);", jqueryAlias, clientElement.getClientId(),params);
		javascriptSupport.addScript(init);		
	}

}
