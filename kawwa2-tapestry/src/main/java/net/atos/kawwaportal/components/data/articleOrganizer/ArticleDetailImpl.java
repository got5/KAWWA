package net.atos.kawwaportal.components.data.articleOrganizer;

import org.apache.tapestry5.Asset;

public class ArticleDetailImpl implements ArticleDetail {
	
	private String ref;
	
	private String label;
	
	private String category;
	
	private Asset image;

	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Asset getImage() {
		return image;
	}

	public void setImage(Asset image) {
		this.image = image;
	}
}