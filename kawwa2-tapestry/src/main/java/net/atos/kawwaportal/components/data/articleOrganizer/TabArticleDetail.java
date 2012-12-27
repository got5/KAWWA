package net.atos.kawwaportal.components.data.articleOrganizer;

import java.util.ArrayList;

public interface TabArticleDetail {

	public abstract String getRef();

	public abstract String getLabel();
	
	public abstract boolean isOpened();
	
	public abstract ArrayList<ArticleDetail> getArticles();

}