package net.atos.kawwaportal.components.data.articleOrganizer;

import java.util.ArrayList;

public class TabArticleDetailImpl implements TabArticleDetail {
	
	private String ref;
	
	private String label;
	
	private boolean opened;
	
	private ArrayList<ArticleDetail> articles;

	
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

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public ArrayList<ArticleDetail> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<ArticleDetail> articles) {
		this.articles = articles;
	}
}