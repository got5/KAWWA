package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.ImportJQueryUI;
import org.got5.tapestry5.jquery.mixins.ui.Widget;

/**
 * 
 * @tapestrydoc
 *
 */
@ImportJQueryUI(value = { "jquery.ui.widget", "jquery.ui.accordion" })
@Import(library = "classpath:/net/atos/kawwaportal/components/assets/kawwa.js")
public class Accordion extends Widget {
	@Inject
	private JavaScriptSupport js;

	@AfterRender
	public void addMakeOneLevelBlockClickable() {
		js.addInitializerCall(InitializationPriority.LATE, "verticalMenu",
				new JSONObject());
	}
}
