package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mixins.ui.Widget;

/**
 * 
 * @tapestrydoc
 * @component_version 1.1
 */
public class Accordion extends Widget {
	@Inject
	private JavaScriptSupport js;

	@AfterRender
	public void addMakeOneLevelBlockClickable() {
		js.require("kawwa/verticalMenu").priority(InitializationPriority.LATE)
			.with(new JSONObject());
	}
	
}
