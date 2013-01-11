package net.atos.kawwaportal.components.data.articleOrganizer;

public class ArticleDetailImpl implements ArticleDetail {
	
	private String ref;
	
	private String label;
	
	private String category;
	
	private String image;
	
	private ImageResourceTypeEnum imageType;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ImageResourceTypeEnum getImageResourceType() {
		return imageType;
	}

	public void setImageResourceType(ImageResourceTypeEnum imageResourceType) {
		this.imageType = imageResourceType;
	}
}