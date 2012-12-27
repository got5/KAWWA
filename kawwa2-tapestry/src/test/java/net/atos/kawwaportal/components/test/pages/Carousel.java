package net.atos.kawwaportal.components.test.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;


public class Carousel {
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private AssetSource assetSource;
	
	public JSONObject getComplexParams(){
		JSONObject retour = new JSONObject();
		retour.put("size", "7");
		retour.put("visible", "4");
		return retour;
	}

}
