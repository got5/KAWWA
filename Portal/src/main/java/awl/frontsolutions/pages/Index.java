package awl.frontsolutions.pages;

import awl.frontsolutions.entities.ChoosenTheme;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

/**
 * Start page of application frontsolutions.
 */
@Import(stack="themestack")
public class Index {

	@SessionState
	private ChoosenTheme theme;
	
	@Inject
	private AssetSource as;
	
	@Property
	private String comp;
	
	@OnEvent(value = EventConstants.ACTIVATE)
	public Object Error(EventContext ctx){
		if(ctx.getCount() > 0)
			return ErrorPage.class;
		return null;
	}
	
	public String getBannerUrl(){
		return as.getContextAsset("img/"+theme.getDir()+".jpg", null).toClientURL();
	}
}
