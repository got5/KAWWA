package awl.frontsolutions.components;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.entities.JSDependency;
import awl.frontsolutions.services.ComponentUtils;

public class ComponentDependencies {
	
	@SessionState
	private ChoosenTheme theme;
	
	@Property
	@Parameter(required=true)
	private List<JSDependency> jsDependencies;
	
	@Property
	@Parameter(required=true)
	private boolean showJquery;
	
	@Inject
	private JavaScriptStackSource stackSource;
	
	@Inject
	private ComponentUtils componentUtils;
	
	@Inject
	@Symbol(JQuerySymbolConstants.JQUERY_VERSION)
	private String jQueryVersion;
	
	@SuppressWarnings("unused")
	@Property
	private List<StylesheetLink> cssDependencies;
	
	@Property
	private StylesheetLink currentCssDependency;
	
	@SuppressWarnings("unused")
	@Property
	private JSDependency currentJsDependency;
	
	@SuppressWarnings("unused")
	@Property
	private int index;
	
	@Inject
	private AssetSource assetSource;
	
	@SuppressWarnings("unused")
	@Property
	private String jQueryName;
	
	@SuppressWarnings("unused")
	@Property
	private String jQueryURL;
	
	@SetupRender
	public void init(){
		
		cssDependencies = stackSource.getStack(theme.getThemeName()).getStylesheets();
		
				
		JavaScriptStack stack = stackSource.getStack("core");
		List<Asset> libs = stack.getJavaScriptLibraries();
		
		for (Asset a : libs) {
			if(a.toClientURL().contains("jquery-"+jQueryVersion)){
				jQueryName = getResourceName(a.getResource());
				jQueryURL = a.toClientURL();
			}
		}
	}
	
	
	
	private Resource getCurrentCss(){
		String[] urlTree = currentCssDependency.getURL().split("/");
		//On supprime les 4 premiers dossiers
		StringBuilder sb = new StringBuilder();
		for(int i=5; i< urlTree.length;i++){
			sb.append("/");
			sb.append(urlTree[i]);
		}
		String ctxPath = "context:"+sb.toString().substring(1);
		Asset a = assetSource.getExpandedAsset(ctxPath);
		
		return a.getResource();
	}
	
	private String getResourceName(Resource r){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			IOUtils.copy(r.openStream(), baos);
			baos.close();
			return r.getFile()+" ["+componentUtils.formatFileSize(baos.size())+"]";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r.getFile();
	}
	
	public String getCurrentCssDependencyName(){
		return getResourceName(getCurrentCss());
		
	}
	
	public boolean getHasJsDependencies(){
		return showJquery || jsDependencies!=null && jsDependencies.size()>0;
	}
}