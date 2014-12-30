package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * This component is added automatically when a form has at least one mandatory field.
 * If at least one input has the k-required CSS class, we will insert this element (a
 * paragraph), just after the form.
 *
 * @tapestrydoc
 * @component_version 1.1
 */
@MixinAfter
public class Mandatory {

	@Parameter(value = "message:mandatory-field")
	private String kawwaMandatoryMessage;
	
	@Parameter(defaultPrefix = "literal", value = "false")
	private boolean disableKawwaMandatory;
	
	@Path("${kawwa2.img-path}/bg_required.png")
	@Inject
	private Asset mandatory;

	@AfterRender
	private void afterRender(MarkupWriter writer){
        if(!disableKawwaMandatory){
			Element el = writer.getElement().getElement(new Predicate<Element>() {
                public boolean accept(Element element) {
                    return element.getAttribute("class") != null && element.getAttribute("class").contains("k-required");
                }
            });

            if(el != null){
                System.out.println(writer.getElement());
				Element required = writer.getElement().element("p", "class", "k-mandatory");
				
				required.raw(String.format(kawwaMandatoryMessage, String.format("<img src='%s' alt='Asterisk'/>", mandatory.toClientURL())));
				
				required.moveToTop(writer.getElement());
			}
		}
	}
}
