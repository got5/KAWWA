package net.atos.kawwaportal.components.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptAggregationStrategy;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.apache.tapestry5.services.javascript.StylesheetOptions;

/**
 * Default Kawwa2 Stacks.
 * Will only import CSS stylesheets
 */
public class Theme0Stack implements JavaScriptStack {

    /**
     * Root directory containing stylesheets
     */
	public static final String STACK_PATH = "classpath:net/atos/kawwaportal/components/theme/k-theme0";
	
	private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;
    
    public Theme0Stack(final AssetSource assetSource) {
		
    	this.javaScriptStack = Collections.emptyList();
        
        this.stylesheetStack = new ArrayList<StylesheetLink>();
        
        this.stylesheetStack.add(new StylesheetLink(assetSource.getExpandedAsset("net/atos/kawwaportal/components/css/k-structure.css")));
        this.stylesheetStack.add(new StylesheetLink(assetSource.getExpandedAsset("net/atos/kawwaportal/components/theme/css/k-theme0.css")));
        this.stylesheetStack.add(new StylesheetLink(assetSource.getExpandedAsset("net/atos/kawwaportal/components/theme/css/iehacks0.css"), new StylesheetOptions("all","lt IE 9")));
        
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

    public List<String> getModules() {
        return Collections.emptyList();
    }

    public JavaScriptAggregationStrategy getJavaScriptAggregationStrategy() {
        return null;
    }

    public String getInitialization() {
		return null;
	}

}
