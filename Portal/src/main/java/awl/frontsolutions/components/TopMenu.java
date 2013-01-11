package awl.frontsolutions.components;

import org.apache.tapestry5.annotations.Parameter;


public class TopMenu {

	@Parameter
	private Integer activeMenu;
	
	public String getClass(Integer indice){
		
		String classe = new String();
		
		if(indice == 0) classe = "home "; 
			
		if(indice == activeMenu) classe += "active";
		
		return classe;
	}
	
	public Boolean isActive(Integer indice){
		return getClass(indice).contains("active");
	}	
	

}
