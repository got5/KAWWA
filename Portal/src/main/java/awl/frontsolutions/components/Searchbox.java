package awl.frontsolutions.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import awl.frontsolutions.pages.Component;
import awl.frontsolutions.pages.Components;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.treeDescription.TreeNode;

public class Searchbox {

	@Inject
    private FileSystemIndexer fileIndexer;

    @Property
    private String searchTag;
    
    @Inject
    private PageRenderLinkSource pageLinkSource;
    
    @OnEvent(value=EventConstants.PROVIDE_COMPLETIONS)
    public List<String> autocompletion(String term){
    	List<String> retour = new ArrayList<String>();
    	List<TreeNode> tns = fileIndexer.searchComponent(term);
    	for (TreeNode tn : tns) {
    		retour.add(tn.getNodeName());
		}
    	return retour;
    }
    
    @OnEvent(value=EventConstants.SUCCESS)
    public Object submitForm(){
    	Map<String, TreeNode> linkToComponent = fileIndexer.getLinkToComponent();
    	Set<String> keys = linkToComponent.keySet();
    	for (String s : keys) {
			if(linkToComponent.get(s).getNodeName().equalsIgnoreCase(searchTag)){
				return pageLinkSource.createPageRenderLinkWithContext(Component.class, s);
			}
		}
    	return Components.class;
    	
    }
}
