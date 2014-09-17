package awl.frontsolutions.components;


import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.PageRenderLinkSource;

import awl.frontsolutions.treeDescription.NodeType;
import awl.frontsolutions.treeDescription.TreeNode;

public class ComponentFormList {

	
	
	@Inject
	private PageRenderLinkSource pageRender;
	
	@Inject
	private AssetSource assetSource;
		
	@Parameter(defaultPrefix = BindingConstants.PROP, required = true)
	private TreeNode source;

	@Persist
	private Map<String,String> linkToResources;
	
	
	
	
	public Map<String, String> getLinkToResources() {
		return linkToResources;
	}

	public void beginRender(MarkupWriter writer) {
		
		linkToResources = new HashMap<String, String>();
		writer.element("ul");
		for (TreeNode subMenu : source.getChildren()) {
			listDirectory(subMenu, writer, 1);
		}
		writer.end();
	}

	private void listDirectory(TreeNode fileStructure, MarkupWriter writer, int level) {

		writer.element("li");
		
		if (fileStructure.getNodeType().equals(NodeType.GROUP)) {
			
			writer.element("fieldset","class","k-sub-group");
			writer.element("legend");
			writer.element("label");
			writer.element("input", "type","checkbox","class","category-label","title","Select/deselect all components for this category");
			writer.end();//input
			writer.write(" "+fileStructure.getNodeName());
			writer.end();//label
			writer.end();//legend
		
		
			writer.element("ul","class","k-check");
			for (TreeNode subMenu : fileStructure.getChildren()) {
				listDirectory(subMenu, writer, level+1);
			}
			writer.end();//ul
			writer.end();//fieldset
			
		}
		if(fileStructure.getNodeType().equals(NodeType.COMPONENT)){
			
			if(level == 1 ) writer.attributes("class","alone");
			
			writer.element("input", "type","checkbox","id",fileStructure.getUrlParam(),"class","counter");
			writer.end();//input
			writer.element("label","for",fileStructure.getUrlParam());
			writer.write(" "+fileStructure.getNodeName());
			writer.end();//label
			
			
		}
		writer.end();//li
	}

}
