package awl.frontsolutions.pages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.func.Worker;
import org.apache.tapestry5.internal.TapestryInternalUtils;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.entities.Panier;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.internal.DownloadDocType;
import awl.frontsolutions.internal.KawwaUtils;
import awl.frontsolutions.internal.LogsUtils;
import awl.frontsolutions.internal.ZipStreamResponse;
import awl.frontsolutions.services.AtosService;
import awl.frontsolutions.services.ComponentZipFiller;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.MailService;
import awl.frontsolutions.services.stack.ThemeStack;
import awl.frontsolutions.treeDescription.TreeNode;

@Import(stack = "themestack", library = { "context:js/downloadForm.js",
		"context:js/ui/jquery.ui.dialog.min.js" })
public class Download {

	@Inject 
	private RequestGlobals request;
	
	@SessionState
	private ChoosenTheme currentTheme;

	@Inject
	private FileSystemIndexer indexer;

	@Inject
	private ComponentZipFiller zipFiller;

	@Inject
	private JavaScriptStackSource stackSource;

	@Inject
	private Messages messages;

	@Inject
	private AssetSource assetSource;

	@Inject
	private JavaScriptSupport js;

	@Inject
	private ComponentResources cr;

	@Property
	private DownloadDocType doctype;

	@Property
	private String theme;

	@Property(read = false)
	private String componentList;

	@Property
	private String email;

	@SessionState
	@Property
	private Panier panier;

	@Inject
	@Symbol(value = ComponentConstants.FILE_INDEXER_ROOT)
	private String componentFolder;

	@Inject
	private MailService mail;

	@Inject
	private AtosService atos;

	public String getComponentList() {

		String list = new String();

		for (int i = 0; i < panier.getListeComponent().size(); i++) {
			list += panier.getListeComponent().get(i) + ",";
		}
		return list;
	}

	public DownloadDocType getXhtml() {
		return DownloadDocType.XHTML;
	}

	public DownloadDocType getHtml5() {
		return DownloadDocType.HTML5;
	}

	@Property
	private TreeNode fileStructure;

	@SetupRender
	public void init() {
		fileStructure = indexer.getFileStructure();
		if (doctype == null) {
			doctype = DownloadDocType.HTML5;
		}
		theme = currentTheme.getThemeName();
	}

	private TreeMap<String, String> getThemeStacks() {
		final TreeMap<String, String> stacks = new TreeMap<String, String>();
		List<String> stackNames = stackSource.getStackNames();

		F.flow(stackNames).filter(new Predicate<String>() {

			public boolean accept(String stackname) {
				return stackname.startsWith(ThemeStack.PREFIX);
			}
		}).filter(new Predicate<String>() {

			
			public boolean accept(String object) {
				String themeName = object.substring(ThemeStack.PREFIX.length());
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

	private void logEmail(String email) {
		if (InternalUtils.isBlank(request.getRequest().getHeader("DNT"))) {
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

			if (p.matcher(email).matches()) {
				LogsUtils.log(LogsUtils.getFile("email", componentFolder),
						email);
				mail.sendMailToDev("Newsletter Subscription", email);
			}
		}
	}

	@OnEvent(EventConstants.SUCCESS)
	public StreamResponse getzipfile() throws IOException {

		if (InternalUtils.isNonBlank(email)) {
			logEmail(email);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(out);

		if (panier.isIncludeTemplate()) {
			zipFiller.fillWithThemeTemplate(zos, panier.getTheme(),
					panier.getDoctype());
		} else {
			zipFiller.fillWithThemeCSS(zos, panier.getTheme());
			zipFiller.fillWithThemeJs(zos, panier.getTheme());
			zipFiller.fillWithThemeImg(zos, panier.getTheme());
		}

		if (StringUtils.isNotEmpty(componentList)) {

			String[] components = componentList.split(",");
			for (String name : components) {
				if (StringUtils.isNotEmpty(name)) {
					zipFiller.fillWithComponent(
							zos,
							name,
							panier.getDoctype(),
							false,
							panier.getTheme(),
							messages.get(panier.getTheme().substring(
									ThemeStack.PREFIX.length())
									+ "-asset-subdir"),
							panier.isIncludeTapestry());
				}
			}
		}

		out.close();
		zos.close();
		return new ZipStreamResponse(out);
	}

	public Asset getInfoIconUrl() {
		Asset retour = null;
		String url = null;
		String themeName = currentTheme.getThemeName().substring(
				ThemeStack.PREFIX.length());
		if (messages.contains(themeName + "-asset-subdir")) {
			url = "img/" + messages.get(themeName + "-asset-subdir")
					+ "/pic_help.png";
			try {
				retour = assetSource.getContextAsset(url, null);
				return retour;
			} catch (Exception e) {
				url = "img/ic_info.png";
				try {
					retour = assetSource.getContextAsset(url, null);
					return retour;
				} catch (Exception e2) {
					return null;
				}

			}
		} else {
			return null;
		}

	}

	@OnEvent(value = "check")
	public void checkComp(@RequestParameter(value = "name") String name,
			@RequestParameter(value = "value") String value) {

		KawwaUtils.updateDownload(panier, name, value);

	}

	public void afterRender() {
		js.addInitializerCall(InitializationPriority.LATE, "downloadPage",
				new JSONObject("url", cr.createEventLink("check", null)
						.toAbsoluteURI()));
	}

}
