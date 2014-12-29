package net.atos.kawwaportal.components.test.data;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;

import java.util.List;
import java.util.UUID;

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
}
