package awl.frontsolutions.components;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.AtosService;

public class AboutKawwaMenu {
	
	private Map<String, String> presentation = new LinkedHashMap<String, String>();
	private Map<String, String> component = new LinkedHashMap<String, String>();
	private Map<String, String> guidelines = new LinkedHashMap<String, String>();
	
	@Inject
	private AtosService atos;
	
	@Inject 
	private ComponentResources cr;
	
	@SetupRender
	public void populatePage(){
		
		
		presentation.put("KawwaPortal", "The Kawwa Portal");
		
		if(atos.isAtosMember()) presentation.put("AboutKawwa", "The Kawwa2 Project");
        presentation.put("Releases", "Release Notes");

		component.put("ComponentApproach", "The Kawwa Components");
		component.put("HowTo", "How to work with components");
		component.put("Accessibility", "Kawwa and accessibility");
		
		guidelines.put("Tapestry", "Tapestry Integration");
        guidelines.put("Angular", "Angular Integration");
		guidelines.put("IeFix", "Support for Internet Explorer");
		guidelines.put("UnobtrusiveApproach", "Unobtrusive JavaScript Approch");
		
	}
	
	@BeginRender
	public void displayMenu(MarkupWriter writer){
		writer.element("div", "id", "menu");
		
		writer.element("p", "class", "k-skip");
		writer.element("a", "href", "#main");
		writer.write("Skip this menu");
		writer.end();
		writer.end();
		
		writer.element("p", "class", "aid");
		writer.element("a", "href", cr.createPageLink("Components", false, null));
		writer.write("Back to index of components");
		writer.end();
		writer.end();

		writer.element("ul", "class", "i-menu");
		displayMenu(writer, presentation, "Presentation");
		displayMenu(writer, component, "About Components");
		displayMenu(writer, guidelines, "Guidelines");
	}
	
	private void displayMenu(MarkupWriter writer, Map<String, String> pages, String title){
		
		writer.element("li"); //<li>
		writer.element("strong");
		writer.write(title);
		writer.end();
		writer.element("ul", "class", "level2");
		
		Iterator it = pages.keySet().iterator();
		while(it.hasNext()) {	
			Object key = it.next();
			Object val = pages.get(key);	
			
			writer.element("li");
				
			if(cr.getPageName().equalsIgnoreCase((String) key)){
				writer.attributes("class", "active");
				writer.element("strong");
				writer.write((String) val);
				writer.end();
			}
			else {
				writer.element("a", "href", cr.createPageLink((String) key, false, null));
				writer.write((String) val);
				writer.end();
			}
			writer.end();
		}
		
		writer.end();
		
		
		writer.end(); //</li>
	}
	
	@AfterRender
	public void closeUlTag(MarkupWriter writer){
		writer.end();
		writer.end();
	}
}
