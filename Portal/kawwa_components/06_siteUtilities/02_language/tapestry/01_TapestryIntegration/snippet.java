package net.atos.kawwaportal.components.test.services;

import net.atos.kawwaportal.components.services.KawwaPortalComponentsModule;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;

public class AppModule {

	@Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration)
    {
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en, fr");
    }
}
