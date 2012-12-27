package awl.frontsolutions.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library={"context:js/plugins/jquery.showsource.js","context:js/components/ShowSource.js"})
public class ShowSource implements ClientElement{

	@SuppressWarnings("unused")
	@Property
	@Parameter(required=true, defaultPrefix = BindingConstants.LITERAL)
	private String type;
	
	@SuppressWarnings("unused")
	@Property
	@Parameter(required=true, defaultPrefix = BindingConstants.LITERAL)
	private String title;
	
	@SuppressWarnings("unused")
	@Property
	@Parameter(required=true, defaultPrefix = BindingConstants.PROP)
	private List<String> escapedCode;
	
	@Property
	@Parameter(required=true, defaultPrefix = BindingConstants.PROP)
	private String readMore;
	
	@Parameter(value="false")
	private Boolean autoOpen;
	
	@SuppressWarnings("unused")
	@Property
	private String currentLine;
	
	@Inject
	private JavaScriptSupport jsSupport;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private AssetSource assetSource;
	
	public boolean getHasReadMore(){
		return StringUtils.isNotEmpty(readMore);
	}
	
	@AfterRender
	public void finish(){
		Link link = resources.createEventLink("getzeroclipboard");
		JSONObject jso = new JSONObject();
		JSONObject params = new JSONObject();
		params.put("swfUrl",link.toAbsoluteURI());
		jso.put("params", params);
		jso.put("id", type + "-code");
		jsSupport.addInitializerCall(InitializationPriority.EARLY, "showSource", jso);
	}
	
	@OnEvent("getzeroclipboard")
	public StreamResponse loadZeroClipboard(){
		final Asset swf = assetSource.getExpandedAsset("context:swf/ZeroClipboard10.swf");
		return new StreamResponse() {
			@Override public void prepareResponse(Response response) {}
			
			@Override
			public InputStream getStream() throws IOException {
				return swf.getResource().openStream();
			}
			
			@Override
			public String getContentType() {return "application/x-shockwave-flash";}
		};
		
	}
	

	public String getClassOpen(){
		return (autoOpen) ? "ui-state-active" : "";
	}
	
	@Override
	public String getClientId() {
		return jsSupport.allocateClientId(resources);
	}
}
