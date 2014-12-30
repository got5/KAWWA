package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mixins.ui.Widget;

/**
 * 
 * @tapestrydoc
 * @component_version 1.1
 */
public class Tipsy {
    /**
     * The format you want to use for your input.
     */
    @Parameter
    private JSONObject options;

    @InjectContainer
    private ClientElement element;

    @Inject
    private JavaScriptSupport js;

    public void afterRender(){
        JSONObject json = new JSONObject();
        json.put("id", element.getClientId());
        json.put("options", options);
        js.require("kawwa/tipsy").with(json);
    }
}
