package net.atos.kawwaportal.components.components;

import net.atos.kawwaportal.components.KawwaUtils;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Submit;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.PersistentLocale;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @tapestrydoc
 * @component_version 1.1
 *
 */
@Events(EventConstants.PROVIDE_COMPLETIONS)
public class SiteSearch {

	@Component(publishParameters="validate, value, annotationProvider, clientId, disabled, label," +
				"nulls, translate",  
				id="search_tag")
	private TextField SiteSearchInput; 
	
	@Component(publishParameters = "autofocus, clientValidation, zone, context, secure, tracker, " +
									"validationId")
	private Form SiteSearch;
	
	@Component(publishParameters="event, image, mode, defer", 
			id="submit")
	private Submit submitInput;
	
	/**
	 * minChars parameters of the AutoComplete Mixin
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private int minChars;
	
	/**
	 * frequency parameters of the AutoComplete Mixin
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
    private double frequency;
	
	/**
	 * tokens parameters of the AutoComplete Mixin
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String tokens;
	
	/**
	 * String for the Placeholder
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String placeholder;
	
	@Inject
	private JavaScriptSupport js;
	
	@Inject
	private Messages messages;
	
	@Inject
	private PersistentLocale pl;
	
	public int getMinChars() {
		return minChars;
	}

	public double getFrequency() {
		return frequency;
	}

	public String getTokens() {
		return tokens;
	}
	
	public String getPlaceholder(){
		return placeholder;
	}
	/**
	 * @return the value for the value attribute of the submit button
	 */
	public String getSubmitLabel(){
		
		return KawwaUtils.getMessages("sitesearch-submit", messages, pl.get()); 
	}
		
	@AfterRender
	public void addPlaceholderMechanism(){
		
		//In a next version, will use the PlaceHolder mixin of Tapestry5-jQuery
		js.require("kawwa/siteSearch").priority(InitializationPriority.LATE)
			.with(new JSONObject("id",SiteSearchInput.getClientId(), "placeholder", placeholder));
		
	}
}
