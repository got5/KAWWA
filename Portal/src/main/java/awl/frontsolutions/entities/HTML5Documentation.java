package awl.frontsolutions.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import awl.frontsolutions.internal.ComponentConstants;

public class HTML5Documentation {
	private String snippetHTML5;
	private List<String> escapedSnippetHTML5;
	private Map<String, String> snippetsCSS3;
	private Map<String, List<String>> escapedSnippetsCSS3;
    private Map<String, String> snippetsSASS;
    private Map<String, List<String>> escapedSnippetsSASS;



    private String snippetJS5;
	private List<String> escapedSnippetJS5;
	private String readMoreHTML5;
	private String readMoreCSS3;
	private String readMoreJS5;

    public HTML5Documentation() {
        escapedSnippetHTML5 = new ArrayList<String>();

        snippetsCSS3 = new HashMap<String, String>();
        snippetsSASS = new HashMap<String, String>();
        escapedSnippetsCSS3 = new HashMap<String, List<String>>();
        escapedSnippetsCSS3 = new HashMap<String, List<String>>();
        escapedSnippetJS5 = new ArrayList<String>();

    }

    public Map<String, String> getSnippetsSASS() {
        return snippetsSASS;
    }

    public void setSnippetsSASS(Map<String, String> snippetsSASS) {
        this.snippetsSASS = snippetsSASS;
    }

    public Map<String, List<String>> getEscapedSnippetsSASS() {
        return escapedSnippetsSASS;
    }

    public void setEscapedSnippetsSASS(Map<String, List<String>> escapedSnippetsSASS) {
        this.escapedSnippetsSASS = escapedSnippetsSASS;
    }

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
		return escapedSnippetJS5;
	}

	public void setEscapedSnippetJS5(List<String> escapedSnippetJS5) {
		this.escapedSnippetJS5 = escapedSnippetJS5;
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
		for (String s : escapedSnippetJS5) {
			string.add(s.replace(ComponentConstants.THEME_IMG_DIR, theme));
		}
		
		return string;
	}

    public List<String> getEscapedSnippetCSS3(String version) {
        if (escapedSnippetsCSS3.containsKey(version)) {
            return escapedSnippetsCSS3.get(version);
        } else if (escapedSnippetsCSS3
                .containsKey(ComponentConstants.SNIPPET_CSS)) {
            return escapedSnippetsCSS3.get(ComponentConstants.SNIPPET_CSS);
        } else {
            return new ArrayList<String>();
        }
    }

    public String getSnippetHTML5(String theme) {
        return snippetHTML5.replace(
                ComponentConstants.THEME_IMG_DIR, theme);
    }

    public String getSnippetCSS3(String version) {
        if (snippetsCSS3.containsKey(version)) {
            return snippetsCSS3.get(version);
        } else if (snippetsCSS3.containsKey(ComponentConstants.SNIPPET_CSS3)) {
            return snippetsCSS3.get(ComponentConstants.SNIPPET_CSS3);
        } else {
            return "";
        }
    }

    public String getSnippetSASS(String version) {
        return snippetsSASS.get(version);
    }

    public String getSnippetJS5(String theme) {
        if (snippetJS5 != null)
            return snippetJS5.replace(ComponentConstants.THEME_IMG_DIR, theme);
        else
            return snippetJS5;

    }

    public void addSnippetCSS3(String filename, String content) {
        snippetsCSS3.put(filename, content);
    }
    public void addSnippetSASS(String filename, String content) {
        System.out.println("add sass");
        snippetsSASS.put(filename, content);
    }
    public void addEscapedSnippetCSS3(String filename,
                                     List<String> escapedSnippetCSS) {
        escapedSnippetsCSS3.put(filename, escapedSnippetCSS);
    }
    public void addEscapedSnippetSASS(String filename,
                                      List<String> escapedSnippetCSS) {
        escapedSnippetsSASS.put(filename, escapedSnippetCSS);
    }
}
