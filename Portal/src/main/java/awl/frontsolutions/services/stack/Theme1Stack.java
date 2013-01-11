package awl.frontsolutions.services.stack;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.apache.tapestry5.services.javascript.StylesheetOptions;

public class Theme1Stack extends ThemeStack{

	public Theme1Stack(
			@Inject final AssetSource assetSource,
			@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode,
			@Inject final SymbolSource symbolSource,
			@Inject final ThreadLocale threadLocale) {
		super(assetSource, productionMode, symbolSource, threadLocale);
		

		addStyleSheet(new StylesheetLink(expand("context:css/k-structure.css")));
		addStyleSheet(new StylesheetLink(expand("context:css/i-theme1.css")));
		addStyleSheet(new StylesheetLink(expand("context:css/k-theme1.css")));
		addStyleSheet(new StylesheetLink(expand("context:css/iehacks1.css"),new StylesheetOptions("all").withCondition("lt IE 9")));
		
		
		addLibrary(expand("context:js/k-general.js"));
		addLibrary(expand("context:js/k-load.js"));
		
		
	}

	


	

}
