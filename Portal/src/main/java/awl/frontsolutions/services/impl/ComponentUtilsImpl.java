package awl.frontsolutions.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.services.ComponentUtils;
import awl.frontsolutions.treeDescription.TreeNode;

public class ComponentUtilsImpl implements ComponentUtils{

	private AssetSource assetSource;

	private ApplicationStateManager asm;
	
	public ComponentUtilsImpl(AssetSource assetSource, ApplicationStateManager asm) {
		super();
		this.assetSource = assetSource;
		this.asm = asm;
	}

	public TreeNode rebuildSources(TreeNode info, ComponentResources resources){
		if(!info.getContent().isRebuilt()){
			List<String> srcPaths = new ArrayList<String>();
			String basedir = info.getPath()+File.separator;
			StringBuilder sb = null;
			if(InternalUtils.isNonBlank(info.getContent().getHtml5().getSnippetHTML5())){
				sb = transformHTML(info.getContent().getHtml5().getSnippetHTML5(),basedir,srcPaths, resources, info.getUrlParam());
				info.getContent().getHtml5().setSnippetHTML5(sb.toString());
			}
			
			
			if(InternalUtils.isNonBlank(info.getContent().getHtml5().getSnippetHTML5())){
				sb = transformHTML(info.getContent().getHtml5().getSnippetHTML5(), basedir, srcPaths, resources, info.getUrlParam());
				info.getContent().getHtml5().setSnippetHTML5(sb.toString());
			}
			
			if(InternalUtils.isNonBlank(info.getContent().getForewords())){
				sb = transformHTML(info.getContent().getForewords(),basedir,srcPaths, resources, info.getUrlParam());
				info.getContent().setForewords(sb.toString());
			}
			
			if(InternalUtils.isNonBlank(info.getContent().getAfterwords())){
				sb = transformHTML(info.getContent().getAfterwords(),basedir,srcPaths, resources, info.getUrlParam());
				info.getContent().setAfterwords(sb.toString());
			}
			
			if(InternalUtils.isNonBlank(info.getContent().getHtml5().getSnippetJS5()))
			{
				StringBuilder js = transformJS(info.getContent().getHtml5().getSnippetJS5(), srcPaths, resources, basedir, info.getUrlParam());
				info.getContent().getHtml5().setSnippetJS5(js.toString());
			}
			
			if(InternalUtils.isNonBlank(info.getContent().getHtml5().getSnippetJS5()))
			{
				StringBuilder js = transformJS(info.getContent().getHtml5().getSnippetJS5(), srcPaths, resources, basedir, info.getUrlParam());
				info.getContent().getHtml5().setSnippetJS5(js.toString());
			}	
			if(info.getContent().getSrcPaths() == null) info.getContent().setSrcPaths(srcPaths);
			info.getContent().setRebuilt(true);
			}
		return info;
	}
	
	private StringBuilder transformJS(String js, List<String> srcPaths, ComponentResources resources, String basedir, String urlParam){
		
		StringBuilder sb = new StringBuilder((int) (js.length()*0.5));
		
		if(urlParam.equalsIgnoreCase("siteSearch")){
			Pattern file = Pattern.compile("./(autocomplete_tags.txt)");
			Matcher m = file.matcher(js);
			int start=0;
			while(m.find()) {
				sb.append(js,start,m.start());
				
				String img = basedir+ComponentConstants.RESOURCES_DIR+File.separator+m.group(0);
				srcPaths.add(img);
				sb.append(resources.createEventLink(ComponentConstants.SRC_EVENT, (srcPaths.size()-1)).toURI());
				
				start = m.end();
			}
			sb.append(js,start,js.length());
		} else sb.append(js);
		
		return sb;
	}
	
	private final static Pattern p = Pattern.compile("(src|href)=\"([^\"]*)\"");
	private StringBuilder transformHTML(String snippetHTML, String basedir, List<String> srcPaths, ComponentResources resources, String urlParam){
		StringBuilder sb = new StringBuilder((int) (snippetHTML.length()*0.5));
		
		int start=0;
		String path = null;
		
		Matcher m = p.matcher(snippetHTML);
		
		while(m.find()) {
			sb.append(snippetHTML,start,m.start());
			sb.append(m.group(1));
			sb.append("=\"");
			try {
				if(m.group(2).indexOf(ComponentConstants.THEME_IMG_DIR)!=-1){
					path = m.group(2);
				}
				else if(!(m.group(1).equals("href") && (m.group(2).startsWith("#") || m.group(2).startsWith("http") )) &&
						m.group(2).indexOf(asm.get(ChoosenTheme.class).getDir())==-1 && !m.group(2).contains("/assets/") &&
						!m.group(2).contains(ComponentConstants.SRC_EVENT) ) {
						
					String img = basedir+ComponentConstants.RESOURCES_DIR+File.separator+m.group(2);
					
					srcPaths.add(img);
					
					path = resources.createEventLink(ComponentConstants.SRC_EVENT, (srcPaths.size()-1)).toURI();
					
				} else path = m.group(2);
				
			} catch (Exception e) {
				path = m.group(2);
			}finally{
				sb.append(path);
			}
			
			sb.append('"');
			start = m.end();
		}
		sb.append(snippetHTML,start,snippetHTML.length());
		
		return sb;
	}
	
	public String formatFileSize(long longSize){
		int decimalPos = 2;
		NumberFormat fmt = NumberFormat.getNumberInstance();
		if (decimalPos >= 0){
			fmt.setMaximumFractionDigits(decimalPos);
		}
		final double size = longSize;
		
		double val = size / (1024 * 1024 * 1024);
		if (val > 1){
			return fmt.format(val).concat(" GB");
		}
		val = size / (1024 * 1024);
		if (val > 1){
			return fmt.format(val).concat(" MB");
		}
		val = size / 1024;
		if (val > 1){
			return fmt.format(val).concat(" KB");
		}
		return fmt.format(size).concat(" bytes");
	}
	
	public Object[] readAndEscapeFile(String fileName) throws FileNotFoundException, IOException{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String s;
		StringBuilder sb = new StringBuilder();
		List<String> escaped = new ArrayList<String>();
		while((s = br.readLine()) != null) {
			sb.append(s);
			escaped.add(s);
		}
		fr.close();
		Object[] fileContent = {sb.toString(),escaped};
		return fileContent;
	}

	
	public Properties getComponentMetadata(String basedir){
		Properties retour = new Properties();
		try {
			retour.load(new FileInputStream(basedir+ComponentConstants.METADATA_FILE_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public Resource getResourceFormStylesheetLink(StylesheetLink link) {
		
		String[] path = link.getURL().split("/");
		
		StringBuilder sb = new StringBuilder();
		
		Boolean flag = false;
		
		for(int i=0; i< path.length;i++){
			
			if(path[i].equalsIgnoreCase("css")) flag=true;
			
			if(flag){
				sb.append("/");
				
				sb.append(path[i]);
			}
		}
		
		String ctxPath = "context:"+sb.toString().substring(1);
			
		Asset a = assetSource.getExpandedAsset(ctxPath);
			
		return a.getResource();
		
	}
	
}
