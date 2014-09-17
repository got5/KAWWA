package awl.frontsolutions.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.AtosService;

@Import(stack="themestack")
public class Updates {
	
	@Inject 
	private AtosService atos;
	
	public boolean isAtosMember(){
		return atos.isAtosMember();
	}
}
