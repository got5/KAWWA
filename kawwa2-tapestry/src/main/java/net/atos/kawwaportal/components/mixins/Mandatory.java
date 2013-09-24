package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * 
 * @tapestrydoc
 * @component_version 1.1
 */
public class Mandatory {

	@Parameter(value="message:mandatory-field")
	private String kawwaMandatoryMessage;
	
	@Parameter(defaultPrefix="literal",value="false")
	private boolean disableKawwaMandatory;
	
	@Path("${kawwa2.img-path}/bg_required.png")
	@Inject
	private Asset mandatory;
	
	@AfterRender
	private void afterRender(MarkupWriter writer){
		
		if(!disableKawwaMandatory){
			Element el = writer.getElement().getElementByAttributeValue("class", "k-required");
			
			if(el!=null){
				
				Element required = writer.getElement().element("p", "class", "k-mandatory");
				
				required.raw(String.format(kawwaMandatoryMessage, String.format("<img src='%s' alt='Asterisk'/>", mandatory.toClientURL())));					
				
				required.moveBefore(writer.getElement().getElementByAttributeValue("class", "t-invisible"));
			}
		}
	}
}
