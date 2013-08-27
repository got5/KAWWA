package awl.frontsolutions.entities;

import java.util.List;

public class AngularDocumentation {

	public AngularDocumentation(String name) {
		super();
		this.name = name;
	}

	public String name;
	
	public String html;
	
	public String js;



    public List<String> EscapedHtml;
	
	public List<String> EscapedJs;
	
	public String Forewords;
	
	public String ReadmeHtml;
	
	public String ReadmeJs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getJs() {
		return this.js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public String getForewords() {
		return Forewords;
	}

	public void setForewords(String forewords) {
		Forewords = forewords;
	}

	public String getReadmeHtml() {
		return this.ReadmeHtml;
	}

	public void setReadmeTemplate(String readmeHtml) {
		this.ReadmeHtml = readmeHtml;
	}

	public String getReadmeJs() {
		return ReadmeJs;
	}

	public void setReadmeJs(String readmeJs) {
		ReadmeJs = readmeJs;
	}

	public List<String> getEscapedJs() {
		return EscapedJs;
	}

	public void setEscapedJs(List<String> escapedJs) {
		EscapedJs = escapedJs;
	}

    public List<String> getEscapedHtml() {
        return EscapedHtml;
    }

    public void setEscapedHtml(List<String> escapedHtml) {
        EscapedHtml = escapedHtml;
    }
}
