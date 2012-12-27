package awl.frontsolutions.services.atos;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.services.ServiceOverride;

import awl.frontsolutions.services.AtosService;
import awl.frontsolutions.services.Authentification;
import awl.frontsolutions.services.MailService;

public class AtosModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(AtosService.class, AtosServiceImpl.class).withId("atosImplementation");
		binder.bind(Authentification.class, AtosAuthentificationImpl.class).withId("atosAuthentificationImpl");
		binder.bind(MailService.class, AtosMailImpl.class).withId("AtosMailImpl");
	}

	@Contribute(ServiceOverride.class)
	public static void setupApplicationServiceOverrides(
			MappedConfiguration<Class, Object> configuration, @Local AtosService override, 
			@Local Authentification override2, @Local MailService override3) {
		
		configuration.add(AtosService.class, override);
		configuration.add(Authentification.class, override2);
		configuration.add(MailService.class, override3);
	}
}
