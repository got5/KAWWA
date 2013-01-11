package net.atos.kawwaportal.components.services.breadcrumb;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.UsesMappedConfiguration;

@UsesMappedConfiguration(BreadcrumbListProvider.class)
public interface BreadcrumbListProviderSource
{
    /*
     * Returns the BreadcrumbListProvider based on the given name
     */
	BreadcrumbListProvider getProvider(String name);

	/*
	 * Returns the list of all the BreadcrumbListProviders.
	 */
    List<String> getProviderNames();
}
