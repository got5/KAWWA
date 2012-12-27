package net.atos.kawwaportal.components.services.breadcrumb;

import java.util.List;

import org.apache.tapestry5.ComponentResources;

public interface BreadcrumbListProvider {
	/* This method is called within the breadcrumb component.
	 * Parameters :
	 * - home : the first element of the breadcrumb (generally the home page of your application), 
	 * this is the home parameter of the breadcrumb component 
	 * - resources : the componentResources service associated with the current Breadcrumb component
	 * This method has to return a  List<String[]>, with :
	 * - String[0] is the page name (to create the pagelink)
	 * - String[1] is the text to be displayed
	 */
	List<String[]> getBreadcrumbList (String home, ComponentResources resources);
}
