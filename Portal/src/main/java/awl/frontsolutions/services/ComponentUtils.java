package awl.frontsolutions.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import awl.frontsolutions.treeDescription.TreeNode;

public interface ComponentUtils {
	
	public TreeNode rebuildSources(TreeNode info, ComponentResources resources);
	
	public String formatFileSize(long longSize);
	
	public Object[] readAndEscapeFile(String fileName) throws FileNotFoundException, IOException;

	public Properties getComponentMetadata(String basedir);
	
	public Resource getResourceFormStylesheetLink(StylesheetLink link);
}