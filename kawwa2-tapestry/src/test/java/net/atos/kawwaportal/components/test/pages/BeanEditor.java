package net.atos.kawwaportal.components.test.pages;


import net.atos.kawwaportal.components.test.data.Celebrity;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

public class BeanEditor {
	
	@Property
	private Celebrity celebrity;
	
	@SetupRender
	public void setupRender(){
		celebrity = new Celebrity();
	}
}
