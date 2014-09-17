package awl.frontsolutions.components;

import java.util.List;
import java.util.TreeMap;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.func.Worker;
import org.apache.tapestry5.internal.TapestryInternalUtils;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.services.AtosService;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.stack.ThemeStack;

@Import(library = "ThemeSwitcher.js")
public class ThemeSwitcher {

	@Parameter
	private String urlParam;

	@Property
	private String theme;

	@SessionState
	private ChoosenTheme choosen;
	private boolean choosenExists;

	@Inject
	private JavaScriptSupport javascriptsupport;

	@Inject
	private JavaScriptStackSource stackSource;

	@Inject
	private Messages messages;

	@Inject
	private ComponentResources cr;

	@Inject
	private AtosService atos;

	@Inject
	private FileSystemIndexer indexer;

	@SetupRender
	public void init() {
		if (!choosenExists) {
			theme = ThemeStack.DEFAULT_THEME;
		} else {
			theme = choosen.getThemeName();
		}
	}

	@AfterRender
	public void finish() {
		javascriptsupport.addInitializerCall("themeswitcher", new JSONObject());
	}

	private TreeMap<String, String> getThemeStacks() {
		final TreeMap<String, String> stacks = new TreeMap<String, String>();
		List<String> stackNames = stackSource.getStackNames();

		F.flow(stackNames).filter(new Predicate<String>() {

			public boolean accept(String element) {
				return element.startsWith(ThemeStack.PREFIX);
			}
		}).filter(new Predicate<String>() {

			public boolean accept(String element) {
				String themeName = element.substring(ThemeStack.PREFIX.length());
				return (!messages.contains(themeName + "-fitler") || (messages
						.get(themeName + "-fitler").equalsIgnoreCase("yes") && atos
						.isAtosMember()));
			}
		}).each(new Worker<String>() {

			public void work(String stackname) {
				String themeName = stackname.substring(ThemeStack.PREFIX
						.length());
				if (messages.contains(themeName + "-theme-label")) {
					stacks.put(stackname,
							messages.get(themeName + "-theme-label"));
				} else {
					stacks.put(stackname,
							TapestryInternalUtils.toUserPresentable(themeName));
				}
			}
		});

		return stacks;

	}

	public SelectModel getThemeModel() {
		return TapestryInternalUtils.toSelectModel(getThemeStacks());
	}

	@OnEvent(EventConstants.SUCCESS)
	public Link changeTheme() {
		String themeName = theme.substring(ThemeStack.PREFIX.length());
		choosen = new ChoosenTheme(theme, messages.get(themeName
				+ "-asset-subdir"));

		indexer.setToRebuilt();

		if (InternalUtils.isNonBlank(urlParam))
			return cr.createPageLink("Component", false, urlParam);
		else
			return cr.createPageLink(cr.getPageName(), false, null);
	}

	public Boolean getIndex() {
		return cr.getPageName().equals("Index");
	}

}
