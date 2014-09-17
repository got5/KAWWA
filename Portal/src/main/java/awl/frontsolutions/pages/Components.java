package awl.frontsolutions.pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.treeDescription.TreeNode;

@Import(stack="themestack")
public class Components {

	@Inject
	private FileSystemIndexer indexer;
	
	@Inject
	private PageRenderLinkSource pageRenderLinkSource;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void activationNoParam(){
		
	}
	
	@OnEvent(EventConstants.ACTIVATE)
	public Object activationWithParam(String componentName){
		URL url = null;
		try {
			url = new URL(pageRenderLinkSource.createPageRenderLinkWithContext(Component.class, componentName).toAbsoluteURI());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
		
	}
	
	public TreeNode getRoot() {
		return indexer.getFileStructure();
	}
	
}
