package awl.frontsolutions.components;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Worker;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.PageRenderLinkSource;

import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.pages.Component;
import awl.frontsolutions.treeDescription.NodeType;
import awl.frontsolutions.treeDescription.TreeNode;

public class ComponentList {
	
	@Inject
	private PageRenderLinkSource pageRender;
	
	@Inject
	private AssetSource assetSource;
		
	@Parameter(required = true)
	private TreeNode fileStructure;

	@Persist
	private Map<String,String> linkToResources;
	
	
	private String jQueryIconUrl;
	
	private String tapestryIconUrl;
	

	
	public Map<String, String> getLinkToResources() {
		return linkToResources;
	}

	public void beginRender(MarkupWriter writer) {
		jQueryIconUrl = assetSource.getContextAsset("img/jquery-trans.png",null).toClientURL();
		tapestryIconUrl = assetSource.getContextAsset("img/tapestry-small.png",null).toClientURL();
		
		
		linkToResources = new HashMap<String, String>();
		writer.element("ul" ,"class","k-sitemap");
		for (TreeNode subMenu : fileStructure.getChildren()) {
			listDirectory(subMenu, writer, 1);
		}
		writer.end();
	}

	private void listDirectory(TreeNode fileStructure, final MarkupWriter writer, final int level) {
		writer.element("li");
		if (fileStructure.getNodeType().equals(NodeType.GROUP)) {
			if(level>0){
				writer.element("strong");
				writer.write(fileStructure.getNodeName());
				writer.end();
			}
			writer.element("ul");
			
			F.flow(fileStructure.getChildren())
					.sort(new Comparator<TreeNode>() {

						@Override
						public int compare(TreeNode o1, TreeNode o2) {
							return o1.getNodeName().compareToIgnoreCase(
									o2.getNodeName());
						}
					}).each(new Worker<TreeNode>() {

						@Override
						public void work(TreeNode element) {
							listDirectory(element, writer, level+1);

						}
					});
		
			writer.end();
			
		}
		if(fileStructure.getNodeType().equals(NodeType.COMPONENT)){
			
			if(level==1 && fileStructure.getChildren().size()==0) writer.attributes("class", "one-level");
			
			writer.element("a");
			writer.attributes("href",pageRender.createPageRenderLinkWithContext(Component.class, fileStructure.getUrlParam()));
			writer.write(fileStructure.getNodeName());
			writer.end();
			if(fileStructure.containsTag(ComponentConstants.TAG_JQUERY)){
				writer.write(" ");
				writer.element("img","src", jQueryIconUrl, "alt", "jQuery", "title", "jQuery Interactive feature");
				writer.end();
			}
			if(fileStructure.containsTag(ComponentConstants.TAG_TAPESTRY)){
				writer.write(" ");
				writer.element("img", "src", tapestryIconUrl, "alt", "Tapestry", "title", "Tapestry Integration");
				writer.end();
			}
			
			linkToResources.put(fileStructure.getUrlParam(), fileStructure.getPath());
		}
		writer.end();
	}

}
