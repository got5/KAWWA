package awl.frontsolutions.components;


import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.pages.IeFix;
import awl.frontsolutions.services.ComponentUtils;

public class ShowIEFiles {

	@Parameter
	private Boolean xhtml;
	
	@SessionState
	private ChoosenTheme currentTheme;
	
	@Inject
	private JavaScriptStackSource jsss;

	@Inject
	private AssetSource as;
	
	@Inject
	private Messages messages;
	
	@Inject
	private ComponentResources cr;
	
	@Inject
	private ComponentUtils utils;

    @Inject
    private PageRenderLinkSource prls;

	public void beginRender(MarkupWriter writer) {
		writer.element("div","class","k-panel exception");
		
        writer.element("h3", "class", "control");
        writer.write("IE Backward Compatibility");
        writer.end();//h2
        
        writer.element("div","class","content");
        writer.element("p");
        writer.write("In order to correct display bugs for Internet Explorer versions prior to 9, your html pages need to call the following file by Conditional Comments:");
        writer.end();//p
        
        writer.element("ul", "class", "k-links-list");
		addLibraries(writer);
		addStylesheets(writer);
		writer.end();//ul
		writer.element("p", "class", "k-readmore");
		writer.write("If you want to know how to apply those files to your project, please consult ");
		writer.element("a", "href", prls.createPageRenderLink(IeFix.class));
		writer.write("Support for Internet Explorer");
		writer.end();
		writer.end();
		writer.end();//div content
		writer.end();
		
	}


	private void addLibraries(MarkupWriter writer) {
		
		writeJsDependencies(writer, as.getContextAsset("js/ieGeneralFix.js", null));
		
		if(!xhtml){
			writeJsDependencies(writer, as.getContextAsset("js/html5shiv.js", null));
		}
	}


	private void writeJsDependencies(MarkupWriter writer, Asset asset) {
		
		String[] libFileUrl = asset.toClientURL().split("/");
		
		String libFileName = libFileUrl[libFileUrl.length-1];
		
		writer.element("li");
		writer.element("a","href",asset.toClientURL());
		writer.write(libFileName);
		writer.end();
				
		try {
			writer.write(getComment(IOUtils.toString(asset.getResource().openStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		writer.end();
	}


	private void addStylesheets(MarkupWriter writer) {
		List<StylesheetLink> links = jsss.getStack(currentTheme.getThemeName()).getStylesheets();
		for (StylesheetLink l : links) {
			if(l.getOptions()!=null && l.getOptions().condition != null){
				if(l.getOptions().condition.toUpperCase().contains("IE")){
					writer.element("li");
					writer.element("a","href",l.getURL());
					String[] path = l.getURL().split("/");
					writer.write(path[path.length-1]);
					writer.end();//a
					
					int index = path[path.length-1].lastIndexOf('.');
					
					if (index>0&& index <= path[path.length-1].length() - 2 ) {
						try {
							writer.write(getComment(IOUtils.toString(utils.getResourceFormStylesheetLink(l).openStream())));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					writer.end();//li
				}
			}
		}
	}

	private String getComment(String content){
		
		String rgxp = "CB/(.*)CE/";
		
		Pattern p = Pattern.compile(rgxp,Pattern.DOTALL);
		
		Matcher m = p.matcher(content);
		
		if(m.find()) {
			return " : " + m.group(1);
		}
		
		return "";
	}

}
