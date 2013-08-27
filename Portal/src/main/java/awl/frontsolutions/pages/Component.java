package awl.frontsolutions.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.entities.JSDependency;
import awl.frontsolutions.entities.Panier;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.internal.DocumentationStreamResponse;
import awl.frontsolutions.services.ComponentUtils;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.stack.ThemeStack;
import awl.frontsolutions.treeDescription.TreeNode;

@Import(stack="themestack")
public class Component {
	
	@Inject
	private FileSystemIndexer fileSystemIndexer;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private Messages messages;
	
	@Inject
	private ThreadLocale threadLocale;
	
	@Inject
	private JavaScriptSupport javascriptSupport;
	
	@Inject
	private AssetSource assetSource;

	@Inject
	private ComponentUtils componentUtils;
	
	@Persist
	private TreeNode componentInfo;

	@SessionState
	private ChoosenTheme currentTheme;
	
	@Property
	private awl.frontsolutions.entities.Documentation currentDocumentation;
	
	@SuppressWarnings("unused")
	@Property
	private String currentLine;
	
	@SuppressWarnings("unused")
	@Property
	private int index;
	
	@SessionState
	private Panier panier;
	
	@Property
	private boolean panierExists;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void activationNoParam(){
		if(componentInfo==null){
			Set<String> keys = fileSystemIndexer.getLinkToComponent().keySet();
			componentInfo =  fileSystemIndexer.getLinkToComponent().get((String) keys.toArray()[0]);
		}
	}
	
	
	@OnEvent(EventConstants.ACTIVATE)
	public void activation(EventContext ctx){
		String urlTag = ctx.get(String.class, 0);
		if(urlTag!=null){
			componentInfo = fileSystemIndexer.getLinkToComponent().get(urlTag);
		}
	}
	
	@OnEvent(EventConstants.PASSIVATE)
	public Object passivation(){
		return componentInfo.getUrlParam();
	}
	
	
	@SetupRender
	public void init(MarkupWriter writer) throws IOException{
		
		componentInfo = componentUtils.rebuildSources(componentInfo,resources);

		List<JSDependency> jsdeps = componentInfo.getContent().getJsDependencies();
		for (int i = 0; i < jsdeps.size(); i++) {
			javascriptSupport.importJavaScriptLibrary(resources.createEventLink(ComponentConstants.JS_DEP, i).toURI());
		}
		javascriptSupport.importJavaScriptLibrary(resources.createEventLink(ComponentConstants.JS_EVENT).toURI());
	}
	
	
	@OnEvent(ComponentConstants.JS_DEP)
	public StreamResponse loadJSDependency(final int context){
		return new StreamResponse() {
			@Override
			public void prepareResponse(Response response) {}
			
			@Override
			public InputStream getStream() throws IOException {
				return IOUtils.toInputStream(componentInfo.getContent().getJsDependencies().get(context).getContent());
			}
			@Override
			public String getContentType() {return "text/javascript";}
		};
	}
	
	@OnEvent(ComponentConstants.JS_EVENT)
	public StreamResponse loadJSFile(){
		return new StreamResponse() {
			@Override
			public void prepareResponse(Response response) {}
			
			@Override
			public InputStream getStream() throws IOException {
				String content = componentInfo.getContent().getSnippetJS5(currentTheme.getDir());
				if(StringUtils.isEmpty(content)){
					content = "";
				}
				return IOUtils.toInputStream(content);
			}
			
			@Override
			public String getContentType() {return "text/javascript";}
		};
	}
	
	@OnEvent(ComponentConstants.SRC_EVENT)
	public StreamResponse loadSrcFile(final int srcIndex){
		return new StreamResponse() {
			@Override
			public void prepareResponse(Response response) {
				
				//For the pageStructure component, there are links in the snippetHTML. 
				if(componentInfo.getContent().getSrcPaths().get(srcIndex).endsWith(".css") || 
						componentInfo.getContent().getSrcPaths().get(srcIndex).endsWith(".html"))
				
				response.setHeader("Content-Disposition", "attachment; filename="+new File(componentInfo.getContent().getSrcPaths().get(srcIndex)).getName());
			}
			
			@Override
			public InputStream getStream() throws IOException {
				
				return new FileInputStream(componentInfo.getContent().getSrcPaths().get(srcIndex));}
			
			@Override
			public String getContentType() {return "application/octet-stream";}
		};
	}
	
	
	@OnEvent(ComponentConstants.DOC_EVENT)
	public StreamResponse loadDocumentation(final int context){
		return new DocumentationStreamResponse(componentInfo.getContent().getDocumentation().get(context).getPath());
	}
	
	
	
	public String getComponentTitle(){
		if(componentInfo.getParent().getNodeName().equals("root")){
			return "";
		}else{
			return componentInfo.getNodeName();
		}
	}
	
	public String getComponentCssClass(){
		if(StringUtils.isNotEmpty(componentInfo.getCss())){
			return "("+componentInfo.getCss()+")";
		}else{
			return "";
		}
	}
	
	public String getGroupTitle(){
		if(componentInfo.getParent().getNodeName().equals("root")){
			return componentInfo.getNodeName();
		}else{
			return componentInfo.getParent().getNodeName();
		}
	}
	
	public String getCurrentDocumentationDescription(){
		if(Locale.FRENCH.getLanguage().equals(threadLocale.getLocale().getLanguage())){
			return currentDocumentation.getDescriptionFr();
		}else{
			return currentDocumentation.getDescriptionEn();
		}
	}
	
	
	
	
	public boolean showFile(String fileContent){
		
		if(componentInfo.getUrlParam().equalsIgnoreCase("pageStructure")) return false;
		
		return showFile(fileContent, true);
	}
	public boolean showFile(String fileContent, boolean flag){
		
		return StringUtils.isNotEmpty(fileContent);
	}
	public boolean getHasJqueryTag(){
		return componentInfo.containsTag(ComponentConstants.TAG_JQUERY);
	}
	
	public boolean getHasHTML5Tag(){
		return componentInfo.getContent().getHtml5().getSnippetHTML5() != null;
	}
	
	public boolean getHasTapestryTag(){
		return componentInfo.containsTag(ComponentConstants.TAG_TAPESTRY);
	}
    public boolean getHasAngularTag(){
        return componentInfo.containsTag(ComponentConstants.TAG_ANGULAR);
    }

	
	private String getThemeCode(){
		return messages.get(currentTheme.getThemeName().substring(ThemeStack.PREFIX.length())+"-theme-code");
	}
	

	
	public String getSnippetCSS(){
		return componentInfo.getContent().getSnippetCSS(currentTheme.getThemeName());
	}
	
	public List<String> getEscapedSnippetCSS(){
		return componentInfo.getContent().getEscapedSnippetCSS(currentTheme.getThemeName());
	}
	public List<String> getEscapedSnippetCSS3(){
		return componentInfo.getContent().getEscapedSnippetCSS(currentTheme.getThemeName());
	}
	public List<String> getEscapedSnippetHTML(){
		return componentInfo.getContent().getEscapedSnippetHTML(currentTheme.getDir());
	}
	public List<String> getEscapedSnippetHTML5(){
		return componentInfo.getContent().getHtml5().getEscapedSnippetHTML5(currentTheme.getDir());
	}
	public List<String> getEscapedSnippetJS(){
		return componentInfo.getContent().getEscapedSnippetJS(currentTheme.getDir());
	}
	public List<String> getEscapedSnippetJS5(){
		return componentInfo.getContent().getHtml5().getEscapedSnippetJS5(currentTheme.getDir());
	}
	public String getSnippetHTML(){
		return componentInfo.getContent().getSnippetHTML(currentTheme.getDir()); 
	}
	public String getSnippetHTML5(){
		return componentInfo.getContent().getSnippetHTML5(currentTheme.getDir()); 
	}
	public String getSnippetCSS3(){
		return componentInfo.getContent().getSnippetCSS(currentTheme.getThemeName());
	}
	
	public List<String> getEscaped(){
		return componentInfo.getContent().getEscapedSnippetCSS(currentTheme.getThemeName());
	}


	public TreeNode getComponentInfo() {
		return componentInfo;
	}

	public boolean isPageStructureComponent(){
		return componentInfo.getUrlParam().equalsIgnoreCase("pageStructure");
	}
	
	@Inject
	private Block source;
	
	@Inject
	private Block forewords;
	
	public Block getSourceBlock(){
		return source;
	}
	
	public Block getForewords(){
		return forewords;
	}
	
	public String getTitle(){
		return "Components : " + componentInfo.getNodeName();
	}
	
	
	
	@InjectComponent
	private Zone zoneBasket;
	
	@Inject
	private Request request;
	
	@OnEvent(value=EventConstants.ACTION, component="panier")
	public Object addToBasket(){
		
		panier.add(componentInfo.getUrlParam());
		if(request.isXHR())
			return zoneBasket;
		
		return resources.createPageLink("Component", false, componentInfo.getUrlParam());
	}
	
	public boolean getHasHtml(){
		return componentInfo.getContent().getSnippetHTML() != null;
	}
	public boolean getHasJs(){
		return !InternalUtils.isEmptyCollection(componentInfo.getContent().getJsDependencies()) || getHasJqueryTag();
	}
	
	public boolean newTheme(){ return !currentTheme.getThemeName().equalsIgnoreCase(ThemeStack.DEFAULT_THEME);}
	
	public Asset getTapestryPath(){
		return this.assetSource.getContextAsset(String.format("img/%s/jquery-trans.png", currentTheme.getDir()), null);
	}
	
	public Asset getJQueryPath(){
		return this.assetSource.getContextAsset(String.format("img/%s/tapestry-small.png", currentTheme.getDir()), null);
	}
    public Asset getAngularPath(){
        return this.assetSource.getContextAsset(String.format("img/%s/angular-small.png", currentTheme.getDir()), null);
    }
}
