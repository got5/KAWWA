package awl.frontsolutions.entities;

import java.io.Serializable;

public class JSDependency implements Serializable{
	private String path;
	private String label;
	private String content;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}