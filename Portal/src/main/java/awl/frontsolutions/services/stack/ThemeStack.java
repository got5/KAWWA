package awl.frontsolutions.services.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class ThemeStack implements JavaScriptStack{

	public static final String PREFIX = "themestack:";
	
	public static final String DEFAULT_THEME = PREFIX+"defaultTheme";

	private AssetSource assetSource;
	
	@SuppressWarnings("unused")
	private boolean productionMode;
	
	private SymbolSource symbolSource;
	
	private ThreadLocale threadLocale;
	
	private List<StylesheetLink> stylesheets;
	
	private List<Asset> libraries;
	
	
	public ThemeStack( AssetSource assetSource,
			boolean productionMode,
			SymbolSource symbolSource, ThreadLocale threadLocale) {
		super();
		this.productionMode = productionMode;
		this.symbolSource = symbolSource;
		this.assetSource = assetSource;
		this.threadLocale = threadLocale;
		libraries = new ArrayList<Asset>();
		stylesheets = new ArrayList<StylesheetLink>();
	}

	
	@Override
	public String getInitialization() {
		return null;
	}

	@Override
	public List<Asset> getJavaScriptLibraries() {
		return libraries;
	}

	@Override
	public List<String> getStacks() {
		return Collections.emptyList();
	}

	@Override
	public List<StylesheetLink> getStylesheets() {
		return stylesheets;
	}
	
	protected void setLibraries(List<String> libraries){
		this.libraries = convertToAssets(libraries);
	}
	
	protected void setStylesheets(List<StylesheetLink> stylesheets){
		this.stylesheets = stylesheets;
	}
	
	//TODO Rewrite with tapestry-func
	protected final List<Asset> convertToAssets(List<String> paths) {
		List<Asset> assets = CollectionFactory.newList();

		if (paths != null) {
			for (String path : paths) {
				assets.add(expand(path));
			}
		}

		return Collections.unmodifiableList(assets);
	}

	protected final Asset expand(String path){
		return expand(path, threadLocale.getLocale());
	}
	
	private final Asset expand(String path, Locale locale) {
		String expanded = symbolSource.expandSymbols(path);

		return assetSource.getAsset(null, expanded, locale);
	}


	protected void addStyleSheet(StylesheetLink stylesheetLink) {
		this.stylesheets.add(stylesheetLink);
	}
	
	protected void addLibrary(Asset library){
		this.libraries.add(library);
	}
	

}
