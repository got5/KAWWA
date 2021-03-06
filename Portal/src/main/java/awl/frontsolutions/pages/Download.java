package awl.frontsolutions.pages;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.entities.Panier;
import awl.frontsolutions.internal.*;
import awl.frontsolutions.services.ComponentZipFiller;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.MailService;
import awl.frontsolutions.services.stack.ThemeStack;
import awl.frontsolutions.treeDescription.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.*;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

@Import(stack = "themestack", library = { "context:js/downloadForm.js",
		"context:js/ui/jquery.ui.dialog.min.js" })
public class Download {

    @Property
    private String theme;

    @Property(read = false)
    private String componentList;

    @Property
    private String email;

    @Property
    private TreeNode fileStructure;

    @SessionState
    @Property
    private Panier panier;

    @SessionState
    private ChoosenTheme currentTheme;

    @Inject
	private RequestGlobals request;

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

    @Inject
    private MailService mail;

	@Inject
	@Symbol(value = ComponentConstants.FILE_INDEXER_ROOT)
	private String componentFolder;

    @SetupRender
    public void init() {
        fileStructure = indexer.getFileStructure();
    }


    public String getComponentList() {

		String list = new String();

		for (int i = 0; i < panier.getListeComponent().size(); i++) {
			list += panier.getListeComponent().get(i) + ",";
		}
		return list;
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
			zipFiller.fillWithThemeTemplate(zos, currentTheme.getThemeName(),
					DownloadDocType.HTML5);
		} else {
			zipFiller.fillWithThemeCSS(zos, currentTheme.getThemeName());
			zipFiller.fillWithThemeJs(zos, currentTheme.getThemeName());
			zipFiller.fillWithThemeImg(zos, currentTheme.getThemeName());
		}

		if (StringUtils.isNotEmpty(componentList)) {

			String[] components = componentList.split(",");
			for (String name : components) {
				if (StringUtils.isNotEmpty(name)) {
					zipFiller.fillWithComponent(
							zos,
							name,
                            DownloadDocType.HTML5,
							false,
							panier.getTheme(),
							messages.get(currentTheme.getThemeName().substring(
									ThemeStack.PREFIX.length())
									+ "-asset-subdir"),
							panier.isIncludeTapestry(),
                            panier.isIncludeAngular());
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
		js.addInitializerCall(InitializationPriority.LATE, "downloadPage", new JSONObject("url", cr.createEventLink("check").toAbsoluteURI()));
	}

}
