package awl.frontsolutions.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import awl.frontsolutions.internal.ComponentConstants;

public class ComponentContent {

	private Map<String, TapestryDocumentation> Tapestry;

    private Map<String, AngularDocumentation> Angular;

	private HTML5Documentation html5;

	private List<JSDependency> jsDependencies;

	private List<Documentation> documentation;

	private String forewords;

	private String afterwords;

	private List<String> srcPaths;

	public Map<String, TapestryDocumentation> getTapestry() {
		return Tapestry;
	}

	public void setAngular(Map<String, AngularDocumentation> angular) {
		Angular = angular;
	}

    public Map<String, AngularDocumentation> getAngular() {
        return Angular;
    }

    public void setTapestry(Map<String, TapestryDocumentation> tapestry) {
        Tapestry = tapestry;
    }

	public HTML5Documentation getHtml5() {
		return this.html5;
	}

	public void setHtml5(HTML5Documentation html5) {
		this.html5 = html5;
	}

	public List<JSDependency> getJsDependencies() {
		return jsDependencies;
	}

	public void setJsDependencies(List<JSDependency> jsDependencies) {
		this.jsDependencies = jsDependencies;
	}

	public List<Documentation> getDocumentation() {
		return documentation;
	}

	public void setDocumentation(List<Documentation> documentation) {
		this.documentation = documentation;
	}

	public String getForewords() {
		return forewords;
	}

	public void setForewords(String forewords) {
		this.forewords = forewords;
	}

	public String getAfterwords() {
		return afterwords;
	}

	public void setAfterwords(String afterwords) {
		this.afterwords = afterwords;
	}

	public List<String> getSrcPaths() {
		return srcPaths;
	}

	public void setSrcPaths(List<String> srcPaths) {
		this.srcPaths = srcPaths;
	}

	//NOT TESTED

	private String snippetHTML;

	private Map<String, String> snippetsCSS;

	private String snippetJS;

	private List<String> escapedSnippetHTML;

	private Map<String, List<String>> escapedSnippetsCSS;

	private List<String> escapedSnippetJS;

	private String readMoreHTML;

	private String readMoreCSS;

	private String readMoreJS;



	private boolean rebuilt;

	public ComponentContent() {
		super();
		escapedSnippetHTML = new ArrayList<String>();
		snippetsCSS = new HashMap<String, String>();

		escapedSnippetsCSS = new HashMap<String, List<String>>();

		escapedSnippetJS = new ArrayList<String>();
		jsDependencies = new ArrayList<JSDependency>();
		// imageDependencies = new ArrayList<ImagesDependency>();
		documentation = new ArrayList<Documentation>();
		rebuilt = false;
	}

	public String getSnippetHTML() {
		return snippetHTML;
	}

	public String getSnippetHTML(String theme) {
		if (snippetHTML != null)
			return snippetHTML.replace(ComponentConstants.THEME_IMG_DIR, theme);
		else
			return getSnippetHTML();
	}

	public String getSnippetHTML5(String theme) {
		if (html5.getSnippetHTML5() != null)
			return html5.getSnippetHTML5().replace(
					ComponentConstants.THEME_IMG_DIR, theme);
		else
			return getSnippetHTML();
	}

	public void setSnippetHTML(String snippetHTML) {
		this.snippetHTML = snippetHTML;
	}

	public String getSnippetCSS(String version) {
		if (snippetsCSS.containsKey(version)) {
			return snippetsCSS.get(version);
		} else if (snippetsCSS.containsKey(ComponentConstants.SNIPPET_CSS)) {
			return snippetsCSS.get(ComponentConstants.SNIPPET_CSS);
		} else {
			return "";
		}
	}

	public String getSnippetCSS3(String version) {
		if (snippetsCSS.containsKey(version)) {
			return snippetsCSS.get(version);
		} else if (snippetsCSS.containsKey(ComponentConstants.SNIPPET_CSS3)) {
			return snippetsCSS.get(ComponentConstants.SNIPPET_CSS3);
		} else {
			return "";
		}
	}

	public void addSnippetCSS(String filename, String content) {
		snippetsCSS.put(filename, content);
	}

	public String getSnippetJS() {
		return snippetJS;
	}

	public String getSnippetJS(String theme) {
		if (snippetJS != null)
			return snippetJS.replace(ComponentConstants.THEME_IMG_DIR, theme);
		else
			return getSnippetJS();
	}


	public String getSnippetJS5(String theme) {
		if (html5.getSnippetJS5() != null)
			return html5.getSnippetJS5().replace(ComponentConstants.THEME_IMG_DIR, theme);
		else
			return getSnippetJS(theme);
	}

	public void setSnippetJS(String snippetJS) {
		this.snippetJS = snippetJS;
	}

	public List<String> getEscapedSnippetHTML() {
		return escapedSnippetHTML;
	}

	public List<String> getEscapedSnippetHTML(String theme) {
		List<String> string = new ArrayList<String>();

		//TODO Rewrite with tapestry-func
		for (String s : escapedSnippetHTML) {
			string.add(s.replace(ComponentConstants.THEME_IMG_DIR, theme));
		}

		return string;
	}


	public List<String> getEscapedSnippetJS(String theme) {
		List<String> string = new ArrayList<String>();

		//TODO Rewrite with tapestry-func
		for (String s : escapedSnippetJS) {
			string.add(s.replace(ComponentConstants.THEME_IMG_DIR, theme));
		}

		return string;
	}


	public void setEscapedSnippetHTML(List<String> escapedSnippetHTML) {
		this.escapedSnippetHTML = escapedSnippetHTML;
	}

	public List<String> getEscapedSnippetCSS(String version) {
		if (escapedSnippetsCSS.containsKey(version)) {
			return escapedSnippetsCSS.get(version);
		} else if (escapedSnippetsCSS
				.containsKey(ComponentConstants.SNIPPET_CSS)) {
			return escapedSnippetsCSS.get(ComponentConstants.SNIPPET_CSS);
		} else {
			return new ArrayList<String>();
		}
	}

	public void addEscapedSnippetCSS(String filename,
			List<String> escapedSnippetCSS) {
		escapedSnippetsCSS.put(filename, escapedSnippetCSS);
	}

	public List<String> getEscapedSnippetJS() {
		return escapedSnippetJS;
	}

	public void setEscapedSnippetJS(List<String> escapedSnippetJS) {
		this.escapedSnippetJS = escapedSnippetJS;
	}

	public String getReadMoreHTML() {
		return readMoreHTML;
	}

	public void setReadMoreHTML(String readMoreHTML) {
		this.readMoreHTML = readMoreHTML;
	}

	public String getReadMoreCSS() {
		return readMoreCSS;
	}

	public void setReadMoreCSS(String readMoreCSS) {
		this.readMoreCSS = readMoreCSS;
	}

	public String getReadMoreJS() {
		return readMoreJS;
	}

	public void setReadMoreJS(String readMoreJS) {
		this.readMoreJS = readMoreJS;
	}

	public boolean isRebuilt() {
		return rebuilt;
	}

	public void setRebuilt(boolean rebuilt) {
		this.rebuilt = rebuilt;
	}
}
