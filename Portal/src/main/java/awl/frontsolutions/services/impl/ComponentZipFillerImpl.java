package awl.frontsolutions.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.services.ApplicationGlobals;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

import awl.frontsolutions.entities.ComponentContent;
import awl.frontsolutions.entities.JSDependency;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.internal.DownloadDocType;
import awl.frontsolutions.internal.KawwaUtils;
import awl.frontsolutions.services.ComponentZipFiller;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.stack.ThemeStack;
import awl.frontsolutions.treeDescription.TreeNode;

public class ComponentZipFillerImpl implements ComponentZipFiller {

	private JavaScriptStackSource stackSource;

    private AssetSource assetSource;

	private FileSystemIndexer fileSystemIndexer;

	private Messages messages;

	private String root;

	private ApplicationGlobals globals;

	private String jQueryVersion;

	public ComponentZipFillerImpl(JavaScriptStackSource stackSource,
			AssetSource assetSource, FileSystemIndexer fileSystemIndexer,
			Messages messages, ApplicationGlobals globals,
			@Symbol(ComponentConstants.FILE_INDEXER_ROOT) String root,
			@Symbol(JQuerySymbolConstants.JQUERY_VERSION) String jQueryVersion) {
		super();
		this.stackSource = stackSource;
		this.assetSource = assetSource;
		this.fileSystemIndexer = fileSystemIndexer;
		this.messages = messages;
		this.root = root;
		this.globals = globals;
		this.jQueryVersion = jQueryVersion;
	}

	/* (non-Javadoc)
	 * @see awl.frontsolutions.services.ComponentZipFiller#fillWithComponent(java.util.zip.ZipOutputStream, java.lang.String, awl.frontsolutions.internal.DownloadDocType, boolean, java.lang.String, java.lang.String, boolean)
	 */
	public void fillWithComponent(ZipOutputStream zos, String urlTag,
			DownloadDocType dlType, boolean dlExamples, String themeName,
			String themeDir, boolean tapestry, boolean angular) throws IOException {

		TreeNode componentInfo = fileSystemIndexer.getLinkToComponent().get(
				urlTag);

		ComponentContent content = componentInfo.getContent();

		String path = "Components/" + componentInfo.getRelatifPath() + "/";

		fillWithFile(zos, path + componentInfo.getCss()+".css",
                    content.getHtml5().getSnippetCSS3(themeName));

        fillWithFile(zos, path + componentInfo.getCss()+".scss",
                content.getHtml5().getSnippetSASS(themeName));

        fillWithFile(zos, path + ComponentConstants.SNIPPET_HTML,
                content.getHtml5().getSnippetHTML5(themeDir));
        fillWithFile(zos, path + ComponentConstants.SNIPPET_JS,
                content.getHtml5().getSnippetJS5(themeDir));



		fillWithFile(zos, path + "README.html", getNotes(componentInfo, dlType));

		// We also copy the dependencies folder
		for (JSDependency jsDep : componentInfo.getContent()
				.getJsDependencies()) {
			File file = new File(componentInfo.getPath() + "/dependencies/"
					+ jsDep.getPath());

			if (file.isFile()) {

				copyFile(zos, file, path + "dependencies/");

			}

		}

		// We also copy the resources folder
		File file = new File(componentInfo.getPath() + "/resources/");

		parcourirFolder(zos, file, path + "resources/");

		if (tapestry) {
			// We also copy the Tapestry folder
			parcourirFolder(zos, new File(componentInfo.getPath()
					+ "/tapestry/"), path + "tapestry/");
		}

        if(angular) {
            parcourirFolder(zos, new File(componentInfo.getPath()
                    + "/angular/"), path + "angular/");
        }
	}

    /**
     *
     * @param link
     * @param destination
     * @param zos
     */
    private void copyThemeCSSFilesIntoZip(StylesheetLink link, String destination, ZipOutputStream zos) throws IOException {
        String[] urlTree = link.getURL().split("/");
        // On supprime les 4 premiers dossiers
        StringBuilder sb = new StringBuilder();

        Boolean flag = false;
        for (int i = 0; i < urlTree.length; i++) {
            if (urlTree[i].equalsIgnoreCase("css"))
                flag = true;
            if (flag) {
                sb.append("/");
                sb.append(urlTree[i]);
            }

        }
        String ctxPath = "context:" + sb.toString().substring(1);

        String comment = null;
        if (link.getOptions() != null) {
            if (link.getOptions().getMedia() != null) {
                comment += "media='" + link.getOptions().getMedia()
                        + "', ";
            }
            if (link.getOptions().getCondition() != null) {
                comment += "condition='"
                        + link.getOptions().getCondition() + "'";
            }
        }

        copyThemeCSSFilesIntoZip(assetSource.getExpandedAsset(ctxPath), destination, zos, comment);
    }

    /**
     *
     * @param link
     * @param destination
     * @param zos
     */
    private void copyThemeCSSFilesIntoZip(Asset asset, String destination, ZipOutputStream zos, String comment) throws IOException {
        ZipEntry entry = new ZipEntry(destination);
        zos.putNextEntry(entry);

        Resource r = asset.getResource();
        InputStream is = r.openStream();
        IOUtils.copy(is, zos);
        is.close();

        if (StringUtils.isNotEmpty(comment)) {
            entry.setComment(comment);
        }

        zos.closeEntry();

    }
	public void fillWithThemeCSS(ZipOutputStream zos, String themeName)
            throws IOException {

        fillWithThemeCSS(zos, themeName, "Theme_" + getThemeLabel(themeName) + "/css/");
    }

    public void fillWithThemeCSS(ZipOutputStream zos, String themeName, String path)
            throws IOException {
        List<StylesheetLink> cssFiles = stackSource.getStack(themeName)
                .getStylesheets();

        for (StylesheetLink cssFile : cssFiles) {
            String url = cssFile.getURL();
            String[] urlPath = url.split("/");
            if(!urlPath[urlPath.length - 1].startsWith("i-theme"))
                copyThemeCSSFilesIntoZip(cssFile, path + urlPath[urlPath.length - 1], zos);
        }

        //Add k-structure
        String[] urlPath = assetSource.getExpandedAsset("${jquery.ui.default-theme.path}").toClientURL().split("/");
        copyThemeCSSFilesIntoZip(assetSource.getExpandedAsset("${jquery.ui.default-theme.path}"),
                path + urlPath[urlPath.length - 1], zos, null);
    }

	/**
	 * 
	 * We include into the ZIP file the ieGeneralFix.js, used for Internet
	 * Explorer issues.
	 * 
	 * @param zos
	 * @param themeName
	 * @throws IOException
	 */
	public void fillWithThemeJs(ZipOutputStream zos, String themeName)
			throws IOException {

		copyAssetToZip(zos, themeName,
				assetSource.getContextAsset("js/ieGeneralFix.js", null));
		copyAssetToZip(zos, themeName, assetSource.getClasspathAsset(
				"org/got5/tapestry5/jquery/jquery_core/jquery-" + jQueryVersion
						+ ".js", null));
	}

	

	/* (non-Javadoc)
	 * @see awl.frontsolutions.services.ComponentZipFiller#fillWithThemeTemplate(java.util.zip.ZipOutputStream, java.lang.String, awl.frontsolutions.internal.DownloadDocType)
	 */
	public void fillWithThemeTemplate(ZipOutputStream zos, String themeName,
			DownloadDocType doctype) throws IOException {

		String themeDir = messages.get(getThemeLabel(themeName)
				+ "-asset-subdir");

        File file = new File(new File(root).getAbsolutePath()
					+ "/templates_html5/" + themeDir + "/");

		parcourirFolder(zos, file, "Templates_" + getThemeLabel(themeName)
				+ "/");

        for (File f : new File(root).listFiles()) {
            if(f.isFile() && f.getName().endsWith(".scss")){
                copyFile(zos, file, "Templates_" + getThemeLabel(themeName)
                        + "/scss/");
            }
        }

        //Add CSS files
        fillWithThemeCSS(zos, themeName, "Templates_" + getThemeLabel(themeName) + "/css/");
	}

	/**
	 * @param zos
	 * @param folder
	 * @param path
	 */
	private void parcourirFolder(ZipOutputStream zos, File folder, String path) {

		if (folder.isDirectory() && !folder.getName().startsWith(".svn")) {

			for (File file : folder.listFiles()) {

				if (file.isDirectory()) {

					parcourirFolder(zos, file, path + file.getName() + "/");

				} else {

					copyFile(zos, file, path);

				}

			}

		}

	}

	/**
	 * @param themeName
	 * @return
	 */
	private String getThemeLabel(String themeName) {
		return (themeName.startsWith(ThemeStack.PREFIX)) ? themeName
				.split(ThemeStack.PREFIX)[1] : themeName;
	}

	

	/* (non-Javadoc)
	 * @see awl.frontsolutions.services.ComponentZipFiller#fillWithThemeImg(java.util.zip.ZipOutputStream, java.lang.String)
	 */
	public void fillWithThemeImg(ZipOutputStream zos, String themeName) {
		String themeDir = messages.get(getThemeLabel(themeName)
				+ "-asset-subdir");
		try {
			File imgDir = globals.getContext().getRealFile(
					"/img/" + themeDir + "/");
			File[] images = imgDir.listFiles();
			for (File img : images) {
				if (img.isFile()) {
					String zipEntryName = "Theme_" + getThemeLabel(themeName)
							+ "/img/" + img.getName();
					ZipEntry entry = new ZipEntry(zipEntryName);
					zos.putNextEntry(entry);
					InputStream fis = new FileInputStream(img);
					IOUtils.copy(fis, zos);
					fis.close();
					zos.closeEntry();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param componentInfo
	 * @param dlType
	 * @return
	 * @throws IOException
	 */
	private String getNotes(TreeNode componentInfo, DownloadDocType dlType)
			throws IOException {
		InputStream is = getClass().getResourceAsStream(
				"/misc/NotesTemplate.html");
		String notesContent = IOUtils.toString(is);
		is.close();

		String updateContent = "<p>Version " + componentInfo.getVersion()
				+ "</p>";

		updateContent = addContent(updateContent,
				messages.get("readme_forewords"), componentInfo.getContent()
						.getForewords());
		updateContent = addContent(updateContent,
				messages.get("readme_afterwords"), componentInfo.getContent()
						.getAfterwords());

		if (DownloadDocType.XHTML.equals(dlType)) {
			updateContent = addContent(updateContent,
					messages.get("readme_html"), componentInfo.getContent()
							.getHtml5().getReadMoreHTML5());
			updateContent = addContent(updateContent,
					messages.get("readme_css"), componentInfo.getContent()
							.getHtml5().getReadMoreCSS3());
			updateContent = addContent(updateContent,
					messages.get("readme_javascript"), componentInfo
							.getContent().getHtml5().getReadMoreJS5());
		} else {
			if (DownloadDocType.HTML5.equals(dlType)) {
				updateContent = addContent(updateContent,
						messages.get("readme_html"), componentInfo.getContent().getHtml5().getReadMoreHTML5());
				updateContent = addContent(updateContent,
						messages.get("readme_css"), componentInfo.getContent().getHtml5().getReadMoreCSS3());
				updateContent = addContent(updateContent,
						messages.get("readme_javascript"), componentInfo
								.getContent().getHtml5().getReadMoreJS5());
			}
		}

		notesContent = KawwaUtils.replaceTag(notesContent, "TITLE",	componentInfo.getNodeName());
		notesContent = KawwaUtils.replaceTag(notesContent, "CONTENT", updateContent);

		return notesContent;

	}

	/**
	 * @param updateContent
	 * @param title
	 * @param content
	 * @return
	 * @throws IOException
	 */
	private String addContent(String updateContent, String title, String content)
			throws IOException {

		if (InternalUtils.isBlank(content))
			return updateContent;

		InputStream is = getClass().getResourceAsStream("/misc/NotesPart.html");
		String notesContent = IOUtils.toString(is);
		is.close();

		notesContent = KawwaUtils.replaceTag(notesContent, "TITLE", title);
		notesContent = KawwaUtils.replaceTag(notesContent, "CONTENT", content);
		return updateContent.concat(notesContent);
	}

	/**
	 * @param zos
	 * @param file
	 * @param path
	 */
	private void copyFile(ZipOutputStream zos, File file, String path) {

		try {
			String zipEntryName = path + file.getName();
			ZipEntry entry = new ZipEntry(zipEntryName);
			zos.putNextEntry(entry);
			InputStream fis = new FileInputStream(file);
			IOUtils.copy(fis, zos);
			fis.close();
			zos.closeEntry();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param zos
	 * @param themeName
	 * @param as
	 * @throws IOException
	 */
	private void copyAssetToZip(ZipOutputStream zos, String themeName, Asset as)
			throws IOException {

		String[] libFileUrl = as.toClientURL().split("/");

		String url = libFileUrl[libFileUrl.length - 1];

		String zipEntryName = "Theme_" + getThemeLabel(themeName) + "/js/"
				+ url;

		CopyAssetToZip(zos, as, zipEntryName);
	}

	/**
	 * @param zos
	 * @param as
	 * @param zipEntryName
	 * @throws IOException
	 */
	private void CopyAssetToZip(ZipOutputStream zos, Asset as,
			String zipEntryName) throws IOException {
		ZipEntry entry = new ZipEntry(zipEntryName);

		zos.putNextEntry(entry);

		Resource r = as.getResource();

		InputStream is = r.openStream();

		IOUtils.copy(is, zos);

		is.close();

		zos.closeEntry();
	}

	/**
	 * @param zos
	 * @param filename
	 * @param content
	 * @throws IOException
	 */
	private void fillWithFile(ZipOutputStream zos, String filename,
			String content) throws IOException {
		System.out.println(filename);
		if (StringUtils.isNotEmpty(content)) {
			ZipEntry entry = new ZipEntry(filename);
			zos.putNextEntry(entry);
			zos.write(content.getBytes());
			zos.closeEntry();
		}
	}
}