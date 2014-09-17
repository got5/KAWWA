package awl.frontsolutions.components;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.entities.Panier;
import awl.frontsolutions.internal.DownloadDocType;
import awl.frontsolutions.internal.KawwaUtils;
import awl.frontsolutions.internal.ZipStreamResponse;
import awl.frontsolutions.services.ComponentZipFiller;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.stack.ThemeStack;

@Import(library = { "context:js/downloadForm.js"})
public class ListeBasket {

	@Parameter
	private String urlParam;
	
	@SessionState
	@Property
	private Panier panier;
		
	@Inject
	private FileSystemIndexer file;
	
	@Inject
	private ComponentResources cr;
	
	@Property
	private String comp;
	
	@SessionState
	private ChoosenTheme currentTheme;

	@Inject
	private FileSystemIndexer indexer;

	@Inject
	private ComponentZipFiller zipFiller;
	
	@Inject
	private Messages messages;
	
	@Inject
	private JavaScriptSupport js;
	
	public void setupRender(){
		panier.setTheme(currentTheme.getThemeName());
	}
	
	@OnEvent(EventConstants.SUBMIT)
	public ZipStreamResponse downloadBasket() throws IOException{
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(out);
		
		if(panier.isIncludeTemplate()){
			zipFiller.fillWithThemeTemplate(zos, panier.getTheme(), DownloadDocType.HTML5);
		}
		else{
			zipFiller.fillWithThemeCSS(zos, panier.getTheme());
			zipFiller.fillWithThemeJs(zos, panier.getTheme());
			zipFiller.fillWithThemeImg(zos, panier.getTheme());
		}
		
		if (panier.getListeComponent().size()>0) {
			
			//TODO Rewrite with tapestry-func
			for (int i=0; i<panier.getListeComponent().size();i++) {
				if (StringUtils.isNotEmpty(panier.getListeComponent().get(i).toString())) {
					zipFiller.fillWithComponent(
							zos,
							panier.getListeComponent().get(i).toString(),
							panier.getDoctype(),
							false,
							panier.getTheme(),
							messages.get(currentTheme.getThemeName().substring(ThemeStack.PREFIX
									.length()) + "-asset-subdir"), panier.isIncludeTapestry(), panier.isIncludeAngular());
				}
			}
		}
		
		out.close();
		zos.close();
		
		return new ZipStreamResponse(out);
	}
	
	@OnEvent(value=EventConstants.ACTION, component="reset")
	public Link deleteBasket(){
		panier = null;
		return cr.createPageLink("component", false, urlParam);
	}
	
	public int getNumber(){
		return panier.getListeComponent().size();
	}
	
	public String getComponentUrl(){
		return cr.createPageLink("component", false, comp).toAbsoluteURI();
	}
	
	public String getComponentName(){
		return file.getLinkToComponent().get(comp).getNodeName();
	}
	
	public DownloadDocType getXhtmlFlag() {
		return DownloadDocType.XHTML;
	}

	public DownloadDocType getHtml5() {
		return DownloadDocType.HTML5;
	}
	public boolean getData(){
		return panier.getListeComponent().size()>0;
	}
	
	public void afterRender(){
		js.addInitializerCall(InitializationPriority.LATE, 
				"basketDownload", 
				new JSONObject("url", cr.createEventLink("checked", null).toAbsoluteURI()));
	}
	@OnEvent(value="checked")
	public void checkComp(@RequestParameter(value = "name") String name, 
			@RequestParameter(value = "value") String value){
		
		KawwaUtils.updateDownload(panier, name, value);
	}
}	
