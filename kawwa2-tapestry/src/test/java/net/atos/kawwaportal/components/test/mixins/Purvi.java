package net.atos.kawwaportal.components.test.mixins;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

public class Purvi {
	
	private Predicate<Element> predicate = new Predicate<Element>() {
		
		
		public boolean accept(Element object) {
			
			if(InternalUtils.isBlank(object.getAttribute("class"))) return false;
			
			return object.getAttribute("class").equals("k-pagination") && object.getElement(new Predicate<Element>() {

				@Override
				public boolean accept(Element object) {
					return InternalUtils.isNonBlank(object.getAttribute("class")) && object.getAttribute("class").equalsIgnoreCase("rows-to-display");
				}
			})==null;
		}
	};
	
	public void afterRender(MarkupWriter writer){

		while(writer.getDocument().getRootElement().getElement(predicate) != null)
		{
			//We retrieve the the k-pagination component
			Element element = writer.getDocument().getRootElement().getElement(predicate);
			
			//We add you new "p" element
			Element newP = element.find("form").elementAt(2, "p" , "class", "rows-to-display");
			
			//We move the select element before the label element
			element.getElementByAttributeValue("class", "lines-to-display").find("label/select")
				.moveBefore(element.getElementByAttributeValue("class", "lines-to-display").find("label"));
			
			//We move the label element inside the new "p" element
			element.getElementByAttributeValue("class", "lines-to-display").find("label").moveToTop(newP);
		}
	}
}
