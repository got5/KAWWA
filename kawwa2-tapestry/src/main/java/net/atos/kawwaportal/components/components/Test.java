package net.atos.kawwaportal.components.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.FormSupport;

public class Test {
	
	@Property 
	@Parameter
	private String inp;
	
	@Inject
    private Environment environment;
	
	private FormSupport support;
	
	public void setupRender(){
		support = environment.pop(FormSupport.class);
	}
	public void afterRender(){
		support = environment.push(FormSupport.class, support);
	}
}

