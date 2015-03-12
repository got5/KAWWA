package awl.frontsolutions.services.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import awl.frontsolutions.entities.ChoosenTheme;

public class ThemeSwitcherStack implements JavaScriptStack {

	private ApplicationStateManager asm;
	private JavaScriptStackSource jss;

	public ThemeSwitcherStack(@Inject final ApplicationStateManager asm,@Inject final JavaScriptStackSource jss) {
		super();
		this.asm = asm;
		this.jss = jss;
	}

	private ChoosenTheme getThemeInSession(){
		ChoosenTheme ct = asm.get(ChoosenTheme.class);
		return ct;
	}


	@Override
	public String getInitialization() {
		return null;
	}


	@Override
	public List<String> getStacks() {
		List<String> stacks = new ArrayList<String>();
		ChoosenTheme ct = getThemeInSession();
		if(jss.getStack(ct.getThemeName())!=null){
			stacks.add(ct.getThemeName());
		}
		return stacks;
	}

	@Override
	public List<Asset> getJavaScriptLibraries() {
		return Collections.emptyList();
	}

	@Override
	public List<StylesheetLink> getStylesheets() {
		return Collections.emptyList();
	}


}
