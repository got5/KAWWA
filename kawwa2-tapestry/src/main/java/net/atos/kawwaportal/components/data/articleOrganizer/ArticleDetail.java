package net.atos.kawwaportal.components.data.articleOrganizer;

import org.apache.tapestry5.Asset;

public interface ArticleDetail {

	public abstract String getRef();

	public abstract String getLabel();

	public abstract String getCategory();

	public abstract Asset getImage();

}