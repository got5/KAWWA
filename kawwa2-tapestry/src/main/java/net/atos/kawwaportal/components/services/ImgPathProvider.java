package net.atos.kawwaportal.components.services;

import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;

public class ImgPathProvider implements SymbolProvider {

	private JavaScriptStackSource js;
	
	
	public ImgPathProvider(JavaScriptStackSource js) {
		super();
		this.js = js;
	}


	public String valueForSymbol(String symbolName) {
		
		return "classpath:net/atos/kawwaportal/components/theme/img/k-theme0";
	}

}
