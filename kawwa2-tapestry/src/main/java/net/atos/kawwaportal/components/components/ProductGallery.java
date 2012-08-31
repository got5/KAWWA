package net.atos.kawwaportal.components.components;

import java.util.List;

import net.atos.kawwaportal.components.data.ecommerce.FrontProductImage;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.utils.JQueryUtils;

/**
 * @author a175290
 */
@Import(library = {"classpath:net/atos/kawwaportal/components/assets/productgallery/jquery.jqzoom-core.js",
		"classpath:net/atos/kawwaportal/components/assets/productgallery/productGallery.js" })
public class ProductGallery {

	@Property
	private int index;

	@Property
	private FrontProductImage firstProductImage;

	@Property
	private FrontProductImage currentProductImage;

	@Property
	@Parameter(required = true)
	private List<FrontProductImage> productImages;

	@Parameter
	private JSONObject params;

	private String clientId;

	@Inject
	private JavaScriptSupport support;

	@SetupRender
	private void init() {
		if (!productImages.isEmpty()) {
			firstProductImage = productImages.get(0);
		}
	}

	@AfterRender
	public void afterRender() {
		String id = getClientId();
		// Définit JSON Par défaut
		JSONObject def = new JSONObject();
		def.put("zoomType", "standard");
		def.put("lens", true);
		def.put("preloadImages", true);
		def.put("alwaysOn", false);
		JQueryUtils.merge(def, params);
		JSONObject opt = new JSONObject();
		opt.put("id", id);
		opt.put("params", def);
		support.addInitializerCall("initZoom", opt);
	}

	public boolean getFirst() {
		return (index == 0);
	}

	public String getClientId() {
		if (clientId == null) {
			clientId = support.allocateClientId(((Component)this).getComponentResources());
		}
		return clientId;
	}
}
