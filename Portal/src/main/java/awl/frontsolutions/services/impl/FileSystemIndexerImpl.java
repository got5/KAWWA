package awl.frontsolutions.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import awl.frontsolutions.entities.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.func.*;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.slf4j.Logger;

import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.services.ComponentUtils;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.stack.ThemeStack;
import awl.frontsolutions.treeDescription.NodeType;
import awl.frontsolutions.treeDescription.TreeNode;

public class FileSystemIndexerImpl implements FileSystemIndexer {

	private String root;

	private TreeNode fileStructure;

	private Map<String, TreeNode> linkToComponent;

	private Logger logger;

	private ThreadLocale threadLocale;

	private JavaScriptStackSource stackSource;

	private Map<String, String> componentStyles;

	private ComponentUtils componentUtils;

	private String rootPath;

	public List<TreeNode> searchComponent(final String term) {
		Flow<TreeNode> values = F.flow(linkToComponent.values()).filter(
				new Predicate<TreeNode>() {

					public boolean accept(TreeNode value) {
						boolean test1 = value.getNodeName().toLowerCase()
								.indexOf(term.toLowerCase()) != -1;
						boolean test2 = StringUtils.isNotEmpty(value.getCss())
								&& value.getCss().toLowerCase()
										.indexOf(term.toLowerCase()) != -1;
						return test1 || test2;
					}

				});
		return values.toList();

	}

	public FileSystemIndexerImpl(
			@Symbol(ComponentConstants.FILE_INDEXER_ROOT) String root,
			Logger logger, ThreadLocale threadLocale,
			JavaScriptStackSource stackSource, ComponentUtils componentUtils,
            Context context) {
		super();


        if(root.equalsIgnoreCase("cloudbees")){
            this.root = context.getRealFile("/kawwa").getAbsolutePath();
        }
		else this.root = root;
		this.logger = logger;
		this.threadLocale = threadLocale;
		linkToComponent = new TreeMap<String, TreeNode>();
		this.stackSource = stackSource;
		this.componentUtils = componentUtils;
		reParseFileStructure();
	}

	public void reParseFileStructure() {
		componentStyles = getCssByTheme();
		File rootFile = new File(root);
		fileStructure = parseStructure(rootFile);
	}

	public TreeNode getFileStructure() {
		// reParseFileStructure();
		return fileStructure;
	}

	public Map<String, TreeNode> getLinkToComponent() {
		return linkToComponent;
	}

	private TreeNode parseStructure(File rootFile) {
		return parseStructure(rootFile, 0, null);
	}

	private TreeNode parseStructure(File rootFile, int level, TreeNode parent) {
		logger.info("Parsing directory [{}]", rootFile.toString());
		TreeNode tn = new TreeNode();
		tn.setLevel(level);
		if (rootFile.isDirectory()) {
			try {
				// Put properties into node
				if (level == 0) {
					rootPath = rootFile.getPath();
					tn.setNodeName("root");
					tn.setNodeType(NodeType.GROUP);
				} else {
					Properties properties = findProperties(rootFile);
					tn.setNodeName(properties
							.getProperty(ComponentConstants.METADATA_NAME_KEY));
					tn.setNodeType(NodeType.valueOf(properties
							.getProperty(ComponentConstants.METADATA_TYPE_KEY)
							.toUpperCase().trim()));
					tn.setPath(rootFile.getPath());
					tn.setRelatifPath(rootFile.getPath().substring(
							(rootPath.length() + 1)));
					if (tn.getNodeType().equals(NodeType.COMPONENT)) {
						tn.setParent(parent);
						tn.setUrlParam(properties
								.getProperty(ComponentConstants.METADATA_URL_PARAM));
						tn.setTags(properties
								.getProperty(ComponentConstants.METADATA_TAG));
						tn.setCss(properties
								.getProperty(ComponentConstants.METADATA_CSS));
						tn.setContent(getComponentContent(rootFile,
								tn.getUrlParam()));

						String version = "1.0";

						if (InternalUtils
								.isNonBlank(properties
										.getProperty(ComponentConstants.METADATA_VERSION)))
							version = properties
									.getProperty(ComponentConstants.METADATA_VERSION);

						tn.setVersion(version);
						linkToComponent.put(tn.getUrlParam(), tn);
					}

				}
				// list other files
				File[] fileArray = rootFile.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return !pathname.getName().equals(
								ComponentConstants.METADATA_FILE_NAME)
								&& !pathname.getName().equals(
										ComponentConstants.DEPENDENCIES_DIR)
								&& !pathname.getName().equals(
										ComponentConstants.DOC_DIR)
								&& pathname.isDirectory();
					}

				});
				for (File file : fileArray) {
					TreeNode n = parseStructure(file, level + 1, tn);
					if (n != null) {
						tn.addChild(n);
					}
				}

				return tn;

			} catch (Exception e) {
				logger.warn(
						"problem reading metadata file found in the directory {} ({})",
						rootFile.getAbsolutePath(), e.getClass());
				return null;
			}
		} else {
			tn.setNodeName(rootFile.getName());
			tn.setNodeType(NodeType.COMPONENT);
			tn.setLevel(level + 1);
			return tn;
		}
	}

	private Properties findProperties(File rootFile) throws IOException {
		File properties = new File(rootFile.getPath() + File.separator
				+ ComponentConstants.METADATA_FILE_NAME);
		if (properties.exists()) {
			Properties p = new Properties();
			FileInputStream fis = new FileInputStream(properties);
			p.load(fis);
			fis.close();
			return p;
		} else {
			throw new FileNotFoundException();
		}
	}

	private Map<String, String> getCssByTheme() {

		Map<String, String> retour = new HashMap<String, String>();

		// Looking for themes
		List<String> stackNames = stackSource.getStackNames();

		for (String stackName : stackNames) {

			if (stackName.startsWith(ThemeStack.PREFIX)) {
				// Looking for K-themeX.css file

				JavaScriptStack stack = stackSource.getStack(stackName);

				List<StylesheetLink> stylesheets = stack.getStylesheets();

				for (StylesheetLink stylesheet : stylesheets) {

					String[] path = stylesheet.getURL().split("/");

					String fileName = path[path.length - 1];

					if (fileName.startsWith("k-theme")
							&& fileName.endsWith(".css")) {

						try {
							retour.put(stackName, IOUtils
									.toString(componentUtils
											.getResourceFormStylesheetLink(
													stylesheet).openStream()));
						} catch (IOException e) {

						}
					}
				}
			}
		}

		return retour;
	}

private ComponentContent getComponentContent(File rootFile, String urlParam) throws IOException {

		String basedir = rootFile.getPath() + File.separator;

        System.out.println(basedir);
        System.out.println(urlParam);
		ComponentContent retour = new ComponentContent();

		readAfterBeforeWords(retour, basedir);

		readHtml5Directory(retour, basedir, rootFile, urlParam);

		readJsDependencies(retour, basedir);
		
		readTapestryDirectory(retour, basedir);

        readAngularDirectory(retour, basedir);

		readDocumentationDirectory(basedir, retour);

		return retour;
	}

	private void readDocumentationDirectory(String basedir,
			ComponentContent retour) {
		// documentation reading
		try {
			logger.info("reading documentation...");
			File docDir = new File(basedir + ComponentConstants.DOC_DIR);
			if (docDir.exists()) {
				Properties docInfo = new Properties();
				docInfo.load(new FileInputStream(basedir
						+ ComponentConstants.DOC_DIR + File.separator
						+ ComponentConstants.DOC_INFO_FILE));
				Set<String> files = docInfo.stringPropertyNames();

				String lang = threadLocale.getLocale().getLanguage()
						.toLowerCase();
				for (String file : files) {
					File doc = new File(basedir + ComponentConstants.DOC_DIR
							+ File.separator + file);
					if (!doc.exists()) {
						// On cherche ds le dossier de la locale
						doc = new File(basedir + ComponentConstants.DOC_DIR
								+ File.separator + lang + File.separator + file);
						if (!doc.exists()) {
							// On cherche ds la documentation générale
							doc = new File(root + File.separator
									+ ComponentConstants.DOC_DIR
									+ File.separator + file);
							if (!doc.exists()) {
								// On cherche ds la documentation générale
								// avec la locale
								doc = new File(root + File.separator
										+ ComponentConstants.DOC_DIR
										+ File.separator + lang
										+ File.separator + file);
								if (!doc.exists()) {
									doc = null;
								}
							}
						}
					}
					if (doc != null) {
						Documentation d = new Documentation();
						d.setLabel(doc.getName() + " ["
								+ componentUtils.formatFileSize(doc.length())
								+ "]");
						String description = (String) docInfo.get(file);
						String[] descriptions = description
								.split("\\s*\\|\\s*");
						if (descriptions.length <= 1) {
							d.setDescriptionFr(description);
							d.setDescriptionEn(description);
						} else {
							d.setDescriptionFr(descriptions[0]);
							d.setDescriptionEn(descriptions[1]);
						}

						d.setPath(doc.getAbsolutePath());
						retour.getDocumentation().add(d);

					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("Error while reading documentation", e);
		} catch (IOException e) {
			logger.error("Error while reading documentation", e);
		}
	}

	private void readJsDependencies(ComponentContent retour, String basedir) {
		// js dependencies reading
		try {
			// logger.info("reading JS dependencies...");
			File dependenciesDir = new File(basedir
					+ ComponentConstants.DEPENDENCIES_DIR);
			File[] fileArray = dependenciesDir.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					return pathname.isFile()
							&& pathname.getName().endsWith(".js");
				}

			});
			if (fileArray != null) {
				for (int i = 0; i < fileArray.length; i++) {
					File file = fileArray[i];
					JSDependency jsdep = new JSDependency();
					jsdep.setLabel(file.getName() + " ["
							+ componentUtils.formatFileSize(file.length())
							+ "]");
					jsdep.setPath(file.getName());
					InputStream fis = new FileInputStream(
							file.getAbsoluteFile());
					jsdep.setContent(IOUtils.toString(fis));
					fis.close();
					retour.getJsDependencies().add(jsdep);
				}
			}
		} catch (Exception e) {
			// logger.info("> no JS dependencies");
		}
	}

    private void readAngularDirectory(ComponentContent retour, String basedir) {
        File AngularDirectory = new File(basedir
                + ComponentConstants.ANGULAR_FOLDER + File.separator);

        Map<String, AngularDocumentation> docs = new HashMap<String, AngularDocumentation>();

        if (AngularDirectory.exists()) {
            for (File subDir : AngularDirectory.listFiles()) {
                if (subDir.isDirectory() && !subDir.getName().startsWith(".")) {

                    AngularDocumentation doc = new AngularDocumentation(
                            subDir.getName());
                    Object[] js = readFile(subDir + File.separator
                            + ComponentConstants.SNIPPET_HTML);

                    if (js != null) {
                        doc.setHtml((String) js[0]);
                        doc.setEscapedHtml((List<String>) js[1]);
                    }

                    js = readFile(subDir + File.separator
                            + ComponentConstants.SNIPPET_JS);
                    if (js != null) {
                        doc.setJs((String) js[0]);
                        doc.setEscapedJs((List<String>) js[1]);
                    }

                    js = readFile(subDir + File.separator
                            + ComponentConstants.FOREWORDS);
                    if (js != null)
                        doc.setForewords((String) js[0]);

                    js = readFile(subDir + File.separator
                            + ComponentConstants.READMORE_TML);
                    if (js != null)
                        doc.setReadmeTemplate((String) js[0]);

                    js = readFile(subDir + File.separator
                            + ComponentConstants.READMORE_JS);
                    if (js != null)
                        doc.setReadmeJs((String) js[0]);

                    docs.put(subDir.getName(), doc);
                }
            }
        }
        retour.setAngular(docs);
    }
	/**
	 * Method used to read all the Tapestry documentation for a Component
	 * 
	 * @param retour
	 * @param basedir
	 *            : the components directory
	 */
	private void readTapestryDirectory(ComponentContent retour, String basedir) {
		File TapestryDirectory = new File(basedir
				+ ComponentConstants.TAPESTRY_FOLDER + File.separator);

		Map<String, TapestryDocumentation> docs = new HashMap<String, TapestryDocumentation>();

		if (TapestryDirectory.exists()) {
			for (File subDir : TapestryDirectory.listFiles()) {
				if (subDir.isDirectory() && !subDir.getName().startsWith(".")) {

					TapestryDocumentation doc = new TapestryDocumentation(
							subDir.getName());
					Object[] js = readFile(subDir + File.separator
							+ ComponentConstants.SNIPPET_TML);

					if (js != null) {
						doc.setTemplate((String) js[0]);
						doc.setEscapedTemplate((List<String>) js[1]);
					}

					js = readFile(subDir + File.separator
							+ ComponentConstants.SNIPPET_JAVA);
					if (js != null) {
						doc.setJava((String) js[0]);
						doc.setEscapedJava((List<String>) js[1]);
					}

					js = readFile(subDir + File.separator
							+ ComponentConstants.SNIPPET_JS);
					if (js != null) {
						doc.setJs((String) js[0]);
						doc.setEscapedJs((List<String>) js[1]);
					}

					js = readFile(subDir + File.separator
							+ ComponentConstants.FOREWORDS);
					if (js != null)
						doc.setForewords((String) js[0]);

					js = readFile(subDir + File.separator
							+ ComponentConstants.READMORE_TML);
					if (js != null)
						doc.setReadmeTemplate((String) js[0]);

					js = readFile(subDir + File.separator
							+ ComponentConstants.READMORE_JAVA);
					if (js != null)
						doc.setReadmeJava((String) js[0]);

					js = readFile(subDir + File.separator
							+ ComponentConstants.READMORE_JS);
					if (js != null)
						doc.setReadmeJs((String) js[0]);

					docs.put(subDir.getName(), doc);
				}
			}
		}
		retour.setTapestry(docs);
	}

	private void readHtml5Directory(ComponentContent retour, String basedir,
			File rootFile, String urlParam) throws IOException {

		HTML5Documentation html5 = new HTML5Documentation();

		// HTML5 reading
		Object[] code = readFile(basedir + ComponentConstants.SNIPPET_HTML);

		html5.setSnippetHTML5((String) code[0]);
		html5.setEscapedSnippetHTML5((List<String>) code[1]);


        Map<String, String> styles = new HashMap<String, String>();
        for (String stackName : stackSource.getStackNames()) {
            if(stackName.startsWith(ThemeStack.PREFIX)){
                JavaScriptStack stack = stackSource.getStack(stackName);
                String theme_name = F.flow(stack.getStylesheets()).map(new Mapper<StylesheetLink, String>() {
                    @Override
                    public String  map(StylesheetLink first) {
                        String[] path = first.getURL().split("/");
                        return path[path.length - 1];
                    }
                }).filter(new Predicate<String>() {
                    @Override
                    public boolean accept(String element) {
                        return element.startsWith("k-theme");
                    }
                }).first();


                try {
                    Object[] cssContent = componentUtils.readAndEscapeFile(basedir + theme_name);
                    html5.addSnippetCSS3(stackName, (String) cssContent[0]);
                    html5.addEscapedSnippetCSS3(stackName, (List<String>) cssContent[1]);
                } catch (Exception e) {
                    logger.info("Impossible to read CSS");
                }

                try {
                    System.out.println(basedir + theme_name.subSequence(0, theme_name.indexOf("."))+".scss");

                    Object[] cssContent = componentUtils.readAndEscapeFile(basedir + theme_name.subSequence(0, theme_name.indexOf("."))+".scss");
                    System.out.println(cssContent[0]);
                    System.out.println(cssContent[1]);
                    html5.addSnippetSASS(stackName, (String) cssContent[0]);
                    html5.addEscapedSnippetSASS(stackName, (List<String>) cssContent[1]);
                } catch (Exception e) {
                    logger.info("Impossible to read SASS");
                }
            }
        }


		code = readFile(basedir + ComponentConstants.SNIPPET_JS);
        html5.setSnippetJS5((String) code[0]);
		html5.setEscapedSnippetJS5((List<String>) code[1]);
		html5.setReadMoreHTML5((String) readFile(basedir
				+ ComponentConstants.READMORE_HTML)[0]);
		html5.setReadMoreCSS3((String) readFile(basedir
				+ ComponentConstants.READMORE_CSS)[0]);
		html5.setReadMoreJS5((String) readFile(basedir
				+ ComponentConstants.READMORE_JS)[0]);

		retour.setHtml5(html5);

	}

	private void readAfterBeforeWords(ComponentContent retour, String basedir) {
		
		retour.setForewords((String) readFile(basedir + ComponentConstants.FOREWORDS)[0]);
		
		retour.setAfterwords((String) readFile(basedir + ComponentConstants.AFTERWORDS)[0]);

        retour.setUpdate((String) readFile(basedir + ComponentConstants.UPDATES)[0]);
	}

	/**
	 * TODO
	 */
	private Object[] readFile(String path) {
		try {
			logger.info("reading {}...", path);
			Object[] obj = componentUtils.readAndEscapeFile(path);
			return obj;
		} catch (Exception e) {
			logger.info("> no {}", path);
		}
		return new Object[2];
	}

	public void setToRebuilt() {
		for (TreeNode child : fileStructure.getChildren()) {
			setToRebuilt(child);
		}
	}

	private void setToRebuilt(TreeNode node) {
		if (node.getContent() != null)
			node.getContent().setRebuilt(false);
		for (TreeNode child : node.getChildren()) {
			setToRebuilt(child);
		}
	}
}