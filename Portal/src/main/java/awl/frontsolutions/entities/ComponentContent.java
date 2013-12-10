package awl.frontsolutions.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import awl.frontsolutions.internal.ComponentConstants;

public class ComponentContent implements Serializable {

	private Map<String, TapestryDocumentation> Tapestry;

    private Map<String, AngularDocumentation> Angular;

	private HTML5Documentation html5;

	private XHTMLDocumentation xhtml;

	private List<JSDependency> jsDependencies;

	private List<Documentation> documentation;

	private String forewords;

	private String afterwords;

	private List<String> srcPaths;
	
	public XHTMLDocumentation getXhtml() {
		return xhtml;
	}

	public void setXhtml(XHTMLDocumentation xhtml) {
		this.xhtml = xhtml;
	}

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
	
	private boolean rebuilt;

	public ComponentContent() {
		super();
		jsDependencies = new ArrayList<JSDependency>();
		documentation = new ArrayList<Documentation>();
		rebuilt = false;
	}


    public boolean isRebuilt() {
		return rebuilt;
	}

	public void setRebuilt(boolean rebuilt) {
		this.rebuilt = rebuilt;
	}
}
