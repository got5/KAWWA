package awl.frontsolutions.services.stack;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.apache.tapestry5.services.javascript.StylesheetOptions;

public class Theme0Stack extends ThemeStack{

	public Theme0Stack(
			@Inject final AssetSource assetSource,
			@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode,
			@Inject final SymbolSource symbolSource,
			@Inject final ThreadLocale threadLocale) {
		super(assetSource, productionMode, symbolSource, threadLocale);


		addStyleSheet(new StylesheetLink(expand("context:css/k-theme0.css")));
		addStyleSheet(new StylesheetLink(expand("context:css/i-theme0.css")));
		addStyleSheet(new StylesheetLink(expand("context:css/iehacks0.css"),new StylesheetOptions("all").withCondition("lt IE 9")));

		addLibrary(expand("context:js/plugins/jquery.masonry.min.js"));
		addLibrary(expand("context:js/plugins/jquery.kawwa.tabs.js"));
		addLibrary(expand("context:js/i-general.js"));
		addLibrary(expand("context:js/k-general.js"));
		addLibrary(expand("context:js/k-load.js"));
	}






}
