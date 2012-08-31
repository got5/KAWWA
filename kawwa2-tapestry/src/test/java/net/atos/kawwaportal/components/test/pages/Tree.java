package net.atos.kawwaportal.components.test.pages;

import net.atos.kawwaportal.components.TreeConstants;
import net.atos.kawwaportal.components.test.data.User;
import net.atos.kawwaportal.components.tree.DefaultTreeSelectionModel;
import net.atos.kawwaportal.components.tree.TreeModel;
import net.atos.kawwaportal.components.tree.TreeSelectionModel;

import org.apache.tapestry5.annotations.OnEvent;

/**
 * Start page of application stetset.
 */
public class Tree
{
	
	public TreeModel getModel(){
		return User.createTreeModel();
	}
	   
	public TreeSelectionModel getSelectModel(){
		return new DefaultTreeSelectionModel();
	}
	
	@OnEvent(value=TreeConstants.NODE_SELECTED)
	public void nodeSelected(String uuid){
		System.out.println("############## The node with the uid " + uuid + " has been selected");
	}
	
	@OnEvent(value=TreeConstants.NODE_UNSELECTED)
	public void nodeUnSelected(String uuid){
		System.out.println("############## The node with the uid " + uuid + " has been selected");
	}
}
