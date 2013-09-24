package net.atos.kawwaportal.components.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.atos.kawwaportal.components.KawwaEventsConstants;
import net.atos.kawwaportal.components.KawwaUtils;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.data.BlankOption;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.PersistentLocale;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.ImportJQueryUI;

/**
 * 
 * @tapestrydoc
 * @component_version 1.1
 *
 */
@Import(library = {"classpath:/net/atos/kawwaportal/components/assets/kawwa.js"})
@ImportJQueryUI("jquery.ui.button")
public class LanguageSelection {

	/**
	 * true : the component will use a Radio component
	 * false : the component will use a Select component
	 */
	@Parameter(value="false")
	private Boolean mode;
	
	@Inject
	private Messages messages; 
	
	@Inject 
	private JavaScriptSupport js;
	
	@Inject 
	private ComponentResources cr;
	
	@Property
	private String idForm;
	
	@Inject
	private Request request;
	
	@Inject
	private PersistentLocale pl;
	
	@Inject
	private ThreadLocale tl;
	
	@Property
	private String current_lang;
	
	@Inject
	@Symbol(value = SymbolConstants.SUPPORTED_LOCALES)
	private String langs;
	
	/**
	 * Get the Model for the Select Component
	 * return a String formatted : value=Label,value2=Label2 ...
	 */
	public String getLanguageModel(){
		
		String model = new String();
		String label = new String();
		
		for(String l : getLangues()){
			
			if(model.length() > 0) model += ",";
			
			label = getLangLibelle(l);
			
			model += (new Locale(l).toString() + "=" + label);
		}
		
		return model;
	}
	
	/**
	 * Get the List of Supported Locales. 
	 * We used trim and toLowerCase methods, in order to return the best value
	 */
	public List<String> getLangues(){
		List<String> newValues = new ArrayList<String>();
		 
		 for(String lang : langs.split(",")){
			 newValues.add(lang.trim().toLowerCase());
		 }
		 
		 return newValues;
	}
	
	/**
	 * Used when mode=true, in order to get the label of a radio input
	 */
	public String getLangLibelle(){
		return getLangLibelle(current_lang);
	}
	
	/**
	 * return the label for a locale. Will checked the Bundle. 
	 * If no messages are defined in one bundle, we will use the Locale. 
	 */
	public String getLangLibelle(String l){
		return (messages.contains("LanguageSelection-" + l)) ? messages.get("LanguageSelection-" + l) : new Locale(l).toString();
	}
	
	/**
	 * Get the current locale. 
	 */
	public String getLang() {
		if(pl.get() == null ) return tl.getLocale().toString().toLowerCase();
		return pl.get().toString().toLowerCase();
	}
	
	/**
	 * Method will add additional informations for the select component : xml:lang and lang attributes
	 */
	@AfterRender
	public void addAdditionalTags(MarkupWriter writer){
		
		//Get the Embedded Form ClientId
		idForm = ((Form) cr.getEmbeddedComponent("selectForm")).getClientId();
		
		if(!mode){
			
			//Get the Select Component by using the ClientId attribute.
			Element el = writer.getElement().getElement(new Predicate<Element>() {
				
				public boolean accept(Element object) {
					if(InternalUtils.isBlank(object.getAttribute("id"))) return false;
					return object.getAttribute("id").equals(((Select)cr.getEmbeddedComponent("language")).getClientId()); 
				}
			});
			
			if(el != null){
				for(Node child : el.getChildren()){
					//For each OPTIONS component, we add xml:lang and lang parameters, except for the option of the current locale. 
					if(!((Element) child).getAttribute("value").equalsIgnoreCase(getLang())){
						((Element) child).forceAttributes("lang", ((Element) child).getAttribute("value"), 
								"xml:lang", ((Element) child).getAttribute("value"));
					}
				}
				
			}
			
		}
		
		//Init the JavaScript Code
		js.addInitializerCall("languageSelection", 
							new JSONObject("id", idForm, 
										   "mode", mode.toString(), 
										   "url", cr.createEventLink(KawwaEventsConstants.LANGUAGE_SELECTED, null).toAbsoluteURI()));
		
	}
	
	/**
	 * Return the current Mode
	 */
	public Boolean getMode() {
		return mode;
	}
	
	/**
	 * Used by the Select Component. It will not have a Blank Option
	 */
	public BlankOption getBlank(){
		return BlankOption.NEVER;
	}	
	
	/**
	 * Method catching the Language_Selected event. 
	 * We will redirect the user to the current page.
	 */
	@OnEvent(value=KawwaEventsConstants.LANGUAGE_SELECTED)
	public Object ajaxRequest(@RequestParameter(value = "lang") String l){
		
		pl.set(new Locale(l.trim()));
		
		return cr.getPageName();
		
	}
	
	/**
	 * Return the Legend. If not specify in one of the Client's bundles, we will
	 * use the Kawwa default bundle.
	 */
	public String getLegend(){
		
		return KawwaUtils.getMessages("languageSelection-legend", messages, request.getLocale());
	}
}
