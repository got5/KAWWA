package net.atos.kawwaportal.components.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;

/**
 * 
 * @tapestrydoc
 *
 */
public class Layout {
	
	@Parameter(defaultPrefix=BindingConstants.LITERAL)
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
