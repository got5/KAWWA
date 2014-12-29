package net.atos.kawwaportal.components.services;

import net.atos.kawwaportal.components.Kawwa2ComponentParameterConstants;
import net.atos.kawwaportal.components.KawwaConstants;
import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProvider;
import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProviderSource;
import net.atos.kawwaportal.components.services.breadcrumb.BreadcrumbListProviderSourceImpl;
import net.atos.kawwaportal.components.services.breadcrumb.PackageBasedBreadcrumbListProvider;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.FactoryDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.*;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.javascript.StackExtension;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;
import org.got5.tapestry5.jquery.services.WidgetParams;

public class KawwaPortalComponentsModule {
	@Contribute(SymbolProvider.class)
	@FactoryDefaults
	public static void contributeSymboleProvider(
			MappedConfiguration<String, Object> configuration) {
		configuration.add(KawwaConstants.KAWWA_COOKIE_ENABLE, true);
		configuration.add(KawwaConstants.KAWWA_IMG_PATH,
				"classpath:net/atos/kawwaportal/components/theme/img/k-theme0");
		configuration.add(KawwaConstants.KAWWA_INCLUDE_STACK, true);
		configuration.override(JQuerySymbolConstants.JQUERY_UI_DEFAULT_THEME,
				"classpath:net/atos/kawwaportal/components/css/library.css");

		configuration
				.add(Kawwa2ComponentParameterConstants.KAWWA2GRIDPAGER_PAGE_RANGE,
						5);
	}

    @Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public static void contributeApplicationDefault(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
    }

    @Contribute(JavaScriptStack.class)
    @Core
    public static void setupCoreJavaScriptStack(OrderedConfiguration<StackExtension>
                                     configuration) {
        configuration.override("bootstrap.css", null);
    }

    @Contribute(BreadcrumbListProviderSource.class)
	public static void addingThePackageBasedBreadcrumbListProvider(
			MappedConfiguration<String, BreadcrumbListProvider> configuration) {
		configuration.addInstance(PackageBasedBreadcrumbListProvider.name,
				PackageBasedBreadcrumbListProvider.class);
	}

	/**
	 * Add the Theme Stack If the user want to change the theme, he will
	 * override the KawwaConstants.STACK_ID stack
	 */
	public static void contributeJavaScriptStackSource(
			MappedConfiguration<String, JavaScriptStack> configuration) {
		configuration.addInstance(KawwaConstants.STACK_ID, Theme0Stack.class);
	}

	public static void contributeClasspathAssetAliasManager(
			MappedConfiguration<String, String> configuration) {
		configuration.add("kawwa2_asset", "net/atos/kawwaportal/components");
	}

	public static void contributeComponentClassTransformWorker(
			OrderedConfiguration<ComponentClassTransformWorker2> configuration) {

		configuration.addInstance("ErrorTransformWorker",
				ErrorsTransformWorker.class);
		configuration.addInstance("FormTransformWorker",
				FormTransformWorker.class);
	}

	public static void contributeComponentClassResolver(
			Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("kawwa2",
				"net.atos.kawwaportal.components"));
	}

	/**
     *
     */
	public static void contributeMarkupRenderer(
			OrderedConfiguration<MarkupRendererFilter> configuration,
			final Environment environment, final JavaScriptSupport js,
			final @Symbol(KawwaConstants.KAWWA_INCLUDE_STACK) Boolean flag,
			final RequestGlobals response) {

		MarkupRendererFilter validationDecorator = new MarkupRendererFilter() {

			public void renderMarkup(MarkupWriter writer,
					MarkupRenderer renderer) {
				ValidationDecorator decorator = new KawwaValidationDecorator(
						environment, writer);

				environment.push(ValidationDecorator.class, decorator);

				renderer.renderMarkup(writer);

				environment.pop(ValidationDecorator.class);

			}
		};

		MarkupRendererFilter rendererFilter = new MarkupRendererFilter() {
			public void renderMarkup(MarkupWriter writer,
					MarkupRenderer renderer) {

				try {
					renderer.renderMarkup(writer);
					Element el = writer.getDocument().getRootElement()
							.find("head");
					if (el != null) {
						el.elementAt(0, "meta", "content", "IE=edge",
								"http-equiv", "X-UA-Compatible");
						el.elementAt(1, "meta", "content",
								"text/html; charset=utf-8", "http-equiv",
								"Content-Type");
						el.elementAt(2, "meta", "name", "viewport", "content",
								"width=device-width, initial-scale=1.0");
					}
				} catch (NullPointerException ex) {
					throw new NullPointerException(
							"Can you check that your page have template file.");
				}
			}

		};
		MarkupRendererFilter injectKawwaStylesheet = new MarkupRendererFilter() {
			public void renderMarkup(MarkupWriter writer,
					MarkupRenderer renderer) {
				/**
				 * We do not include the Kawwa Stack for the Exception Report
				 * Page
				 */
				if (flag
						&& !response.getHTTPServletResponse().containsHeader(
								"X-Tapestry-ErrorMessage")) {
					js.importStack(KawwaConstants.STACK_ID);
				}

				renderer.renderMarkup(writer);
			}
		};

		MarkupRendererFilter BeanEditorMarkupRendererFilter = new MarkupRendererFilter() {
			public void renderMarkup(MarkupWriter writer,
					MarkupRenderer renderer) {
				
				Predicate<Element> predicate = new Predicate<Element>() {
					public boolean accept(Element el) {
						return el.getAttribute("class") != null && el.getAttribute("class").contains(
								"t-beaneditor-row");
					}
				};

				renderer.renderMarkup(writer);

				while (writer.getDocument().getRootElement().getElement(predicate) != null) {
					Element el = writer.getDocument().getRootElement()
							.getElement(predicate);

					String input = el.getChildMarkup();

					Element newE = el.wrap("p");
					
					newE.removeChildren();

					newE.raw(input);
				}
			}
		};

		configuration.add("KawwaValidationDecorator", validationDecorator,
				"after:*");
		configuration.add("meta", rendererFilter, "before:*");
		configuration.add("injectKawwaStylesheet", injectKawwaStylesheet,
				"after:InjectDefaultStyleheet");
		configuration.add("BeanEditorMarkupRendererFilter",
				BeanEditorMarkupRendererFilter, "after:*");
	}

	@Contribute(WidgetParams.class)
	public void addOptionsToWidget(
			MappedConfiguration<String, JSONObject> configuration) {

		configuration.add("tipsy", new JSONObject("gravity", "w", "fade",
				"true"));

	}

	public static void bind(ServiceBinder binder) {
		binder.bind(BreadcrumbListProviderSource.class,
				BreadcrumbListProviderSourceImpl.class);
	}

}
