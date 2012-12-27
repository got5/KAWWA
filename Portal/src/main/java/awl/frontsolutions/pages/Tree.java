package awl.frontsolutions.pages;

import java.util.Date;

import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.treeDescription.TreeNode;

public class Tree {

	@Inject
	private FileSystemIndexer indexer;

	public Date getCurrentTime() {
		return new Date();
	}

	public TreeNode getRoot() {
		return indexer.getFileStructure();
	}

	public void onReparse() {
		indexer.reParseFileStructure();
	}
}
