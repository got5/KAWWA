package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mixins.ui.Widget;

/**
 * 
 * @tapestrydoc
 * @component_version 1.1
 */
public class JsTree extends Widget{
	
	@Parameter(value="false")
	private boolean hotkey;
	
	@Inject @Path("classpath:/net/atos/kawwaportal/components/assets/jstree/jquery.hotkeys.js")
	private Asset hotKeysAsset;
	
	@Inject @Path("classpath:/net/atos/kawwaportal/components/assets/jstree/jquery.cookie.js")
	private Asset cookieAsset;

	@Inject @Path("classpath:/net/atos/kawwaportal/components/assets/jstree/jquery.jstree.js")
	private Asset jsTreeAsset;

	@Environmental
	private JavaScriptSupport javaScriptSupport;
	
	@SetupRender
	void setupRender()
	{
		if (hotkey) javaScriptSupport.importJavaScriptLibrary(hotKeysAsset);
		javaScriptSupport.importJavaScriptLibrary(cookieAsset);
		javaScriptSupport.importJavaScriptLibrary(jsTreeAsset);
	}
}