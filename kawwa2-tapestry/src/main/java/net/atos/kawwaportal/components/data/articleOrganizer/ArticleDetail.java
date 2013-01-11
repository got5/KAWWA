package net.atos.kawwaportal.components.data.articleOrganizer;

public interface ArticleDetail {

	public abstract String getRef();

	public abstract String getLabel();

	public abstract String getCategory();

	public abstract String getImage();

	public abstract ImageResourceTypeEnum getImageResourceType();
}