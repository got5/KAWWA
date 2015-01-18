package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mixins.ui.Widget;
import org.got5.tapestry5.jquery.utils.JQueryUtils;

/**
 * @tapestrydoc
 * @component_version 1.1
 */
public class JsTree {

    /**
     * If the hotkeys boolean parametre, an external jquery.hotkeys javascript file will be loaded.
     */
	@Parameter(value="false")
	private boolean hotkey;

    /**
     * Parameter of the corresponding jQuery widget.
     */
    @Parameter
    private JSONObject options;

    @InjectContainer
    private ClientElement element;

	@Environmental
	private JavaScriptSupport javaScriptSupport;

    public void afterRender(){
        JSONObject json = new JSONObject();
        json.put("id", element.getClientId());

        JSONObject defaults = new JSONObject();
        JSONArray array = new JSONArray("html_data", "ui");
        if (hotkey) {
            array.put("hotkeys");
            javaScriptSupport.require("kawwa/vendor/jstree/jquery.hotkeys");
        }
        defaults.put("plugins", array);

        JQueryUtils.merge(defaults, options);
        json.put("options", defaults);
        javaScriptSupport.require("kawwa/jsTree").with(json);
    }
}