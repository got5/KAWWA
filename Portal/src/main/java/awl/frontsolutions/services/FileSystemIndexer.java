package awl.frontsolutions.services;

import java.util.List;
import java.util.Map;

import awl.frontsolutions.treeDescription.TreeNode;



public interface FileSystemIndexer {

    public TreeNode getFileStructure();
    
    public void reParseFileStructure();
    
    public Map<String,TreeNode> getLinkToComponent();
    
    public List<TreeNode> searchComponent(final String term);
    
    public void setToRebuilt();
}
