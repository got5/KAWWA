package net.atos.kawwaportal.components.test.services;

import net.atos.kawwaportal.components.services.KawwaPortalComponentsModule;
import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProvider;
import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProviderSource;
import net.atos.kawwaportal.components.test.data.IDataSource;
import net.atos.kawwaportal.components.test.data.MockDataSource;
import net.atos.kawwaportal.components.test.services.ratings.RatingService;
import net.atos.kawwaportal.components.test.services.ratings.RatingServiceImpl;
import net.atos.kawwaportal.components.test.services.ratings.ReviewService;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.ApplicationStateContribution;
import org.apache.tapestry5.services.ApplicationStateCreator;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

@SubModule(KawwaPortalComponentsModule.class)
public class AppModule {
	@Contribute(SymbolProvider.class)
	@ApplicationDefaults
	public static void contributeFactoryDefaults(
			MappedConfiguration<String, String> configuration) {
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en, fr");
		configuration.add(JQuerySymbolConstants.JQUERY_ALIAS, "$j");
		configuration.add(JQuerySymbolConstants.SUPPRESS_PROTOTYPE, "true");
	}

	public void contributeApplicationStateManager(
			MappedConfiguration<Class, ApplicationStateContribution> configuration) {

		ApplicationStateCreator<IDataSource> creator = new ApplicationStateCreator<IDataSource>() {
			public IDataSource create() {
				return new MockDataSource();
			}
		};

		configuration.add(IDataSource.class, new ApplicationStateContribution(
				"session", creator));
	}

	@Contribute(BreadcrumbListProviderSource.class)
	public static void addingThePackageBasedBreadcrumbListProvider(
			MappedConfiguration<String, BreadcrumbListProvider> configuration) {
		configuration.addInstance(MyBreadcrumbListProvider.name,
				MyBreadcrumbListProvider.class);
	}

	public static void bind(ServiceBinder binder) {
		binder.bind(RatingService.class, RatingServiceImpl.class).withId(
				"RatingServiceIndexer");
		binder.bind(ReviewService.class, RatingServiceImpl.class).withId(
				"ReviewServiceIndexer");
	}

}