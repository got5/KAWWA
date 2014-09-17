package net.atos.kawwaportal.components.test.pages;

import net.atos.kawwaportal.components.TreeConstants;
import net.atos.kawwaportal.components.test.data.User;
import net.atos.kawwaportal.components.tree.DefaultTreeSelectionModel;
import net.atos.kawwaportal.components.tree.TreeModel;
import net.atos.kawwaportal.components.tree.TreeSelectionModel;

import org.apache.tapestry5.annotations.OnEvent;

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

/*
 * And here is the user class:
 */
package net.atos.kawwaportal.components.test.data;

import java.util.List;
import java.util.UUID;

import net.atos.kawwaportal.components.tree.DefaultTreeModel;
import net.atos.kawwaportal.components.tree.TreeModel;
import net.atos.kawwaportal.components.tree.TreeModelAdapter;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;

public class User {

	public final String uuid = UUID.randomUUID().toString();
	
	private String nom;
	
	public final List<User> children = CollectionFactory.newList();
	
	public User(String nom) {
		super();
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public static final User ROOT = new User("<root>");
	
	public User addChildrenNamed(String... names){
		
		for(String name : names){
			children.add(new User(name));
		}
		return this;
	}
	
	public User addChild(User user){
		
		children.add(user);
		
		return this;
	}
	
	public User seek(String uuid){
		
		if(this.uuid.equals(uuid)) return this;
		
		for(User child : children){
			User match = child.seek(uuid);
			
			if(match != null) return match;
		}
		
		return null;
	}
	
	static {
		
		ROOT.addChild(new User("Renault")
						.addChild(new User("Mégane"))
						.addChild(new User("Clio")
							.addChildrenNamed("Clio Campus", "Clio Sport")
						)
					 )
			.addChild(new User("Ferarri").addChildrenNamed("F430", "California"));
		
	}
	
	public static TreeModel<User> createTreeModel(){
		
		ValueEncoder encoder = new ValueEncoder<User>() {

			public String toClient(User arg0) {
				return arg0.uuid;
			}

			public User toValue(String arg0) {
				return User.ROOT.seek(arg0);
			}
		};
		
		TreeModelAdapter<User> adapter = new TreeModelAdapter<User>() {

			public List<User> getChildren(User arg0) {
				return arg0.children;
			}

			public String getLabel(User arg0) {
				return arg0.getNom();
			}

			public boolean hasChildren(User arg0) {
				return !arg0.children.isEmpty();
			}

			public boolean isLeaf(User arg0) {
				return arg0.children.isEmpty();
			}
			
		};
		
		return new DefaultTreeModel<User>(encoder, adapter, User.ROOT.children);
			
	}
}
