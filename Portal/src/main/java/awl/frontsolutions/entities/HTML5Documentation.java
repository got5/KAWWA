package awl.frontsolutions.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import awl.frontsolutions.internal.ComponentConstants;

public class HTML5Documentation {
	private String snippetHTML5;
	private List<String> escapedSnippetHTML5;
	private Map<String, String> snippetsCSS3;
	private Map<String, List<String>> escapedSnippetsCSS3;
	private String snippetJS5;
	private List<String> EscapedSnippetJS5;
	private String readMoreHTML5;
	private String readMoreCSS3;
	private String readMoreJS5;

	public Map<String, String> getSnippetsCSS3() {
		return snippetsCSS3;
	}

	public String getSnippetJS5() {
		return snippetJS5;
	}

	public void setSnippetJS5(String snippetJS5) {
		this.snippetJS5 = snippetJS5;
	}

	public List<String> getEscapedSnippetJS5() {
		return EscapedSnippetJS5;
	}

	public void setEscapedSnippetJS5(List<String> escapedSnippetJS5) {
		EscapedSnippetJS5 = escapedSnippetJS5;
	}

	public String getReadMoreHTML5() {
		return readMoreHTML5;
	}

	public void setReadMoreHTML5(String readMoreHTML5) {
		this.readMoreHTML5 = readMoreHTML5;
	}

	public String getReadMoreCSS3() {
		return readMoreCSS3;
	}

	public void setReadMoreCSS3(String readMoreCSS3) {
		this.readMoreCSS3 = readMoreCSS3;
	}

	public String getReadMoreJS5() {
		return readMoreJS5;
	}

	public void setReadMoreJS5(String readMoreJS5) {
		this.readMoreJS5 = readMoreJS5;
	}

	public void setSnippetsCSS3(Map<String, String> snippetsCSS3) {
		this.snippetsCSS3 = snippetsCSS3;
	}

	public Map<String, List<String>> getEscapedSnippetsCSS3() {
		return escapedSnippetsCSS3;
	}

	public void setEscapedSnippetsCSS3(
			Map<String, List<String>> escapedSnippetsCSS3) {
		this.escapedSnippetsCSS3 = escapedSnippetsCSS3;
	}

	public String getSnippetHTML5() {
		return snippetHTML5;
	}

	public void setSnippetHTML5(String snippetHTML5) {
		this.snippetHTML5 = snippetHTML5;
	}

	public List<String> getEscapedSnippetHTML5() {
		return escapedSnippetHTML5;
	}

	public void setEscapedSnippetHTML5(List<String> escapedSnippetHTML5) {
		this.escapedSnippetHTML5 = escapedSnippetHTML5;
	}
	
	public List<String> getEscapedSnippetHTML5(String theme) {
		List<String> string = new ArrayList<String>();

		//TODO Rewrite with tapestry-func
		for (String s : escapedSnippetHTML5) {
			string.add(s.replace(ComponentConstants.THEME_IMG_DIR, theme));
		}

		return string;
	}
	
	public List<String> getEscapedSnippetJS5(String theme) {
		List<String> string = new ArrayList<String>();
		
		//TODO Rewrite with tapestry-func
		for (String s : EscapedSnippetJS5) {
			string.add(s.replace(ComponentConstants.THEME_IMG_DIR, theme));
		}
		
		return string;
	}
}
