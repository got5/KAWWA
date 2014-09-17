package awl.frontsolutions.services.stack;

import java.util.List;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.internal.services.javascript.CoreJavaScriptStack;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class OverridePrototypeStack extends CoreJavaScriptStack {

	public OverridePrototypeStack(@Symbol(SymbolConstants.PRODUCTION_MODE) boolean productionMode,
			SymbolSource symbolSource, AssetSource assetSource,
			ThreadLocale threadLocale) {
		super(productionMode, symbolSource, assetSource, threadLocale);

	}

	@Override
	public List<StylesheetLink> getStylesheets() {
		return CollectionFactory.newList();
	}

}
