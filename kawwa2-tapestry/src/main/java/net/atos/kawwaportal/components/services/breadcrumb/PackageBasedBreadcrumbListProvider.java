package net.atos.kawwaportal.components.services.breadcrumb;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Messages;

public class PackageBasedBreadcrumbListProvider implements BreadcrumbListProvider{
	
	public static final String name = "PackageBasedBreadcrumbListProvider";
	
	private List<String[]> breadcrumbList;

	public List<String[]> getBreadcrumbList(String home,ComponentResources componentResources){ 

		String pageName = componentResources.getPageName();
		int slashx = componentResources.getPageName().lastIndexOf('/');
		Messages i18Labels = componentResources.getContainerMessages();
		
		breadcrumbList = new ArrayList<String[]>();

		while (slashx > 0) {
			String link = pageName.substring(0, slashx);
			int shortNamex = pageName.lastIndexOf('/', slashx - 1);

			String shortName = (shortNamex > -1) ? pageName.substring(
					shortNamex + 1, slashx) : pageName.substring(0, slashx);

			breadcrumbList.add(new String[] { link,
					i18Labels.get(shortName + "_BREAD_CRUMB_LABEL") });

			slashx = pageName.lastIndexOf('/', slashx - 1);

		}
		breadcrumbList.add(new String[] { home, i18Labels.get(home + "_BREAD_CRUMB_LABEL") });
		return breadcrumbList;
	}
}