package awl.frontsolutions.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.services.AtosService;
import awl.frontsolutions.services.stack.ThemeStack;


public class TopMenu {

	@Parameter
	private Integer activeMenu;
	
	@SessionState
    private ChoosenTheme choosen;
    
	public String getClass(Integer indice){
		
		String classe = new String();
		
		if(indice == 0) classe = "home "; 
			
		if(indice == activeMenu) classe += "active";
		
		return classe;
	}
	
	public Boolean isActive(Integer indice){
		return getClass(indice).contains("active");
	}	
	
	
	@Inject
	private AtosService atos;
	
	public Boolean isAtos(){
		return atos.isAtosMember();
	}	
	
	public Boolean newTheme(){
		return choosen.getThemeName().equalsIgnoreCase(ThemeStack.DEFAULT_THEME);
	}
	
}
