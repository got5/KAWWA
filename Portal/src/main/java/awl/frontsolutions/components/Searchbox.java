package awl.frontsolutions.components;

import awl.frontsolutions.pages.Component;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.treeDescription.TreeNode;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Searchbox {

	@Inject
    private FileSystemIndexer fileIndexer;

    @Property
    private String searchTag;

    @Inject
    private PageRenderLinkSource pageLinkSource;

    @Inject
    private AjaxResponseRenderer ajax;

    @InjectComponent
    private Zone searchZone;

    @OnEvent(value=EventConstants.PROVIDE_COMPLETIONS)
    public List<String> autocompletion(String term){
    	List<String> retour = new ArrayList<String>();
    	List<TreeNode> tns = fileIndexer.searchComponent(term);

    	//TODO Rewrite with tapestry-func
    	for (TreeNode tn : tns) {
    		retour.add(tn.getNodeName());
		}
    	return retour;
    }

    @OnEvent(value=EventConstants.SUCCESS)
    public Object submitForm(){
    	Map<String, TreeNode> linkToComponent = fileIndexer.getLinkToComponent();
    	Set<String> keys = linkToComponent.keySet();
    	//TODO Rewrite with tapestry-func
    	for (String s : keys) {
			if(linkToComponent.get(s).getNodeName().equalsIgnoreCase(searchTag)){
				return pageLinkSource.createPageRenderLinkWithContext(Component.class, s);
			}
		}

        ajax.addCallback(new JavaScriptCallback() {
            @Override
            public void run(JavaScriptSupport javascriptSupport) {
                javascriptSupport.addScript("jQuery('.compo-entry').click();");
            }
        });

    	return searchZone.getBody();

    }
}
