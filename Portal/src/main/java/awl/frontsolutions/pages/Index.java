package awl.frontsolutions.pages;

import java.util.Set;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.services.AtosService;
import awl.frontsolutions.services.TopComponent;
import awl.frontsolutions.services.stack.ThemeStack;

/**
 * Start page of application frontsolutions.
 */
@Import(stack="themestack")
public class Index {

	@SessionState
	private ChoosenTheme theme;
	
	@Inject
	private AssetSource as;
	
	@Inject
	private TopComponent topComponent;
	
	@Inject 
	private AtosService atos;
	
	@Property
	private Set<String> topComponents;
	
	@Property
	private String comp;
	
	@OnEvent(value = EventConstants.ACTIVATE)
	public Object Error(EventContext ctx){
		if(ctx.getCount() > 0)
			return ErrorPage.class;
		return null;
	}
	
	@SetupRender
	public void getTopComp(){
		topComponents = topComponent.getTopComponent().keySet();
	}
	
	public String getComponentLibelle(){
		return topComponent.getTopComponent().get(comp);
	}
	
	public String getBannerUrl(){
		return as.getContextAsset("img/"+theme.getDir()+".jpg", null).toClientURL();
	}
	
	public boolean isAtosMember(){
		return atos.isAtosMember();
	}
	public Boolean newTheme(){
		return theme.getThemeName().equalsIgnoreCase(ThemeStack.DEFAULT_THEME);
	}
}
