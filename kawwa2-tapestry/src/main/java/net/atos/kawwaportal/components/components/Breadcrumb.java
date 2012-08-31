package net.atos.kawwaportal.components.components;

import java.util.Collections;
import java.util.List;

import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProvider;
import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProviderSource;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Breadcrumb {

	@Parameter(required=true, defaultPrefix = BindingConstants.LITERAL)
	private String home;
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL, value="PackageBasedBreadcrumbListProvider")
	private String breadcrumbProvider;
	
	@Property
	@Parameter(defaultPrefix=BindingConstants.LITERAL, value=" &gt; ")
	private String splittingChar;
	
	@Property
	@Parameter(defaultPrefix=BindingConstants.LITERAL, value=" &gt; ")
	private String lastSplittingChar;

	@Inject
	private ComponentResources componentResources;
	
	@Inject
	private Messages i18Labels;
	
	@Property
	private List<String[]> breadcrumb;

	@Property
	private String[] current;

	@Property
	private int index;

	@Property
	private String shortPageName;

	@Inject
	private BreadcrumbListProviderSource listProviderSource;
	
	@SetupRender
	void init() {

		String pageName = componentResources.getPageName();
		int slashx = componentResources.getPageName().lastIndexOf('/');
		BreadcrumbListProvider listProvider = listProviderSource.getProvider(breadcrumbProvider);
		breadcrumb = listProvider.getBreadcrumbList(home, componentResources);
		this.shortPageName = i18Labels.get(pageName.substring(slashx + 1)
				+ "_BREAD_CRUMB_LABEL");
		Collections.reverse(breadcrumb);
	}

	public String getLink() {
		return current[0];
	}

	public String getPkgName() {
		return current[1];
	}

	public boolean getHastNext() {
		return !(breadcrumb.size() - 1 == index);
	}
}