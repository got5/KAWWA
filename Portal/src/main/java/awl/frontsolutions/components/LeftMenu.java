package awl.frontsolutions.components;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.treeDescription.TreeNode;


public class LeftMenu {

	@Inject
	private FileSystemIndexer indexer;

	@Component(publishParameters="urlParam")
	private ListDirectory liste;
	
	public TreeNode getRoot() {
		return indexer.getFileStructure();
	}

	public void onReparse() {
		indexer.reParseFileStructure();
	}

}
