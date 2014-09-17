package awl.frontsolutions.components;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import awl.frontsolutions.pages.Component;
import awl.frontsolutions.treeDescription.NodeType;
import awl.frontsolutions.treeDescription.TreeNode;

public class ListDirectory {

	@Parameter(required = true)
	private TreeNode fileStructure;
	
	@Parameter(required=true)
	private String urlParam;
	
	@Inject
	private JavaScriptSupport js;
	
	@Inject
	private PageRenderLinkSource pageRender;
	
	@Persist
	private Map<String,String> linkToResources;
	
	
	public Map<String, String> getLinkToResources() {
		return linkToResources;
	}

	public void beginRender(MarkupWriter writer) {
		linkToResources = new HashMap<String, String>();
		writer.element("ul","class","k-menu","id","kawwaportal_menu");
		List<TreeNode> children = fileStructure.getChildren();
		//TODO Rewrite with tapestry-func
		for (int i=0;i<children.size();i++) {
			TreeNode subMenu = children.get(i);
			listDirectory(subMenu, writer, 1,i);
		}
		writer.end();
	}

	private void listDirectory(TreeNode fileStructure, MarkupWriter writer, int level,int  index) {
		writer.element("li");
		
		if(urlParam.equals(fileStructure.getUrlParam())){
			writer.getElement().addClassName("active");
		}
		
		if (fileStructure.getNodeType().equals(NodeType.GROUP)) {
			if(level>0){
				writer.element("a","href","#","title","Click to open/close sub-menu");
				writer.write(fileStructure.getNodeName());
				writer.end();
			}
			String levelString= "level"+(level+1);
			
			/*
			 * If we are on the first level, we look for the item with urlParam as a Child. 
			 * in order to open automatically the accordion 
			 */
			if(level==1){
				if(fileStructure.hasChild(urlParam)){
					js.addScript(InitializationPriority.LATE, "$('#kawwaportal_menu.k-menu').accordion(%s);", new JSONObject().put("active", index));
				}
			}
			
			writer.element("ul","class",levelString);
			for (TreeNode subMenu : fileStructure.getChildren()) {
				listDirectory(subMenu, writer, level+1,0);
			}
			writer.end();
			
		}
		if(fileStructure.getNodeType().equals(NodeType.COMPONENT)){
			writer.element("a");
			writer.attributes("href",pageRender.createPageRenderLinkWithContext(Component.class, fileStructure.getUrlParam()));
			
			if(level==1) writer.attributes("class","one-level");
				
			writer.write(fileStructure.getNodeName());
			writer.end();
			linkToResources.put(fileStructure.getUrlParam(), fileStructure.getPath());
		}
		writer.end();
	}

}
