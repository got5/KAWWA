package awl.frontsolutions.services.stack;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;
import org.got5.tapestry5.jquery.services.javascript.FormSupportStack;

public class OverrideFormSupportStack extends FormSupportStack {

	public OverrideFormSupportStack(AssetSource assetSource, @Symbol(JQuerySymbolConstants.USE_MINIFIED_JS) boolean minified) {
		super(assetSource, minified);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<StylesheetLink> getStylesheets() {
		return CollectionFactory.newList();
	}

}
