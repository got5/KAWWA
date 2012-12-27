package net.atos.kawwaportal.components.services.breadcrumb;

import java.util.List;
import java.util.Map;

import org.apache.tapestry5.func.F;
import org.apache.tapestry5.ioc.util.AvailableValues;
import org.apache.tapestry5.ioc.util.UnknownValueException;

public class BreadcrumbListProviderSourceImpl implements
		BreadcrumbListProviderSource {

	private final Map<String, BreadcrumbListProvider> configuration;

    public BreadcrumbListProviderSourceImpl(Map<String, BreadcrumbListProvider> configuration)
    {
        this.configuration = configuration;
    }
    
	public BreadcrumbListProvider getProvider(String name) {
		BreadcrumbListProvider provider = configuration.get(name);

        if (provider == null)
            throw new UnknownValueException(String.format("No BreadcrumbListProvider with name '%s'.", name),
                    new AvailableValues("Configured BreadcrumbListProviders :", configuration));
        return provider;
	}

	public List<String> getProviderNames() {
		return F.flow(configuration.keySet()).sort().toList();
	}

}
