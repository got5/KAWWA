package awl.frontsolutions.entities;

import java.util.List;

public class TapestryDocumentation {

	public TapestryDocumentation(String name) {
		super();
		this.name = name;
	}

	public String name;

	public String Template;

	public String Java;

	public String Js;

	public List<String> EscapedTemplate;

	public List<String> EscapedJava;

	public List<String> EscapedJs;

	public String Forewords;

	public String ReadmeTemplate;

	public String ReadmeJava;

	public String ReadmeJs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return Template;
	}

	public void setTemplate(String template) {
		Template = template;
	}

	public String getJava() {
		return Java;
	}

	public void setJava(String java) {
		Java = java;
	}

	public String getJs() {
		return Js;
	}

	public void setJs(String js) {
		Js = js;
	}

	public String getForewords() {
		return Forewords;
	}

	public void setForewords(String forewords) {
		Forewords = forewords;
	}

	public String getReadmeTemplate() {
		return ReadmeTemplate;
	}

	public void setReadmeTemplate(String readmeTemplate) {
		ReadmeTemplate = readmeTemplate;
	}

	public String getReadmeJava() {
		return ReadmeJava;
	}

	public void setReadmeJava(String readmeJava) {
		ReadmeJava = readmeJava;
	}

	public String getReadmeJs() {
		return ReadmeJs;
	}

	public void setReadmeJs(String readmeJs) {
		ReadmeJs = readmeJs;
	}

	public List<String> getEscapedTemplate() {
		return EscapedTemplate;
	}

	public void setEscapedTemplate(List<String> escapedTemplate) {
		EscapedTemplate = escapedTemplate;
	}

	public List<String> getEscapedJava() {
		return EscapedJava;
	}

	public void setEscapedJava(List<String> escapedJava) {
		EscapedJava = escapedJava;
	}

	public List<String> getEscapedJs() {
		return EscapedJs;
	}

	public void setEscapedJs(List<String> escapedJs) {
		EscapedJs = escapedJs;
	}
}
