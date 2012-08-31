package net.atos.kawwaportal.components.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.apache.tapestry5.services.javascript.StylesheetOptions;

/**
 * Stack for k-theme1
 */
public class Theme1Stack implements JavaScriptStack {

	public static final String STACK_PATH = "classpath:net/atos/kawwaportal/components/theme/k-theme1";
	
	private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;
    
    public Theme1Stack(final AssetSource assetSource) {
		
    	this.javaScriptStack = Collections.emptyList();
        
        this.stylesheetStack = new ArrayList<StylesheetLink>();
        
        this.stylesheetStack.add(new StylesheetLink(assetSource.getExpandedAsset("net/atos/kawwaportal/components/css/k-structure.css")));
        this.stylesheetStack.add(new StylesheetLink(assetSource.getExpandedAsset("net/atos/kawwaportal/components/theme/css/k-theme1.css")));
        this.stylesheetStack.add(new StylesheetLink(assetSource.getExpandedAsset("net/atos/kawwaportal/components/theme/css/iehacks1.css"), new StylesheetOptions("all","lt IE 9")));
        
	}
    
	public List<String> getStacks() {
		return Collections.emptyList();
	}

	public List<Asset> getJavaScriptLibraries() {
		return javaScriptStack;
	}

	public List<StylesheetLink> getStylesheets() {
		return stylesheetStack;
	}

	public String getInitialization() {
		return null;
	}

}
