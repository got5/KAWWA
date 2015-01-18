package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

/**
 * Errors is a mixin that is automatically added to all core/Errors components
 * This is done through the ErrorTransformWorker.
 * You can disable it by using the disableKawwaErrors parameter 
 * on the error component.
 * 
 * @tapestrydoc
 * @component_version 1.0
 */
public class Errors {

    /**
     * Header part of the Errors component
     */
	@Parameter(value="message:errors-message")
	private String kawwaErrorMessage;

    /**
     * Flag used to enable/disable this mixin.
     */
	@Parameter(defaultPrefix="literal",value="false")
	private boolean disableKawwaErrors;
	
	@AfterRender
	public void afterRender(MarkupWriter writer){

		if(!disableKawwaErrors){
			Predicate<Element> predicate = new Predicate<Element>() {
				
				
				public boolean accept(Element object) {
					
					if(InternalUtils.isBlank(object.getAttribute("class"))) return false;
					
					return object.getAttribute("class").equals("alert-dismissable alert alert-danger");
				}
			};
			while(writer.getDocument().getRootElement().getElement(predicate) != null)
			{
				Element element = writer.getDocument().getRootElement().getElement(predicate);
				
				element.forceAttributes("class", "k-error-messages");
				
				Element banner = element.find("h4");
				
				Element ul = element.find("ul");
				
				element.removeChildren();
				
				element.element("h3").text(kawwaErrorMessage);
				
				element.element("p").text(banner.getChildMarkup());
				
				element.element("ul").raw(ul.getChildMarkup());
			}
		}
	}
}