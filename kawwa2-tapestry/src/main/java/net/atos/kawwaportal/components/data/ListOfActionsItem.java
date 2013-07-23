package net.atos.kawwaportal.components.data;

import net.atos.kawwaportal.components.KawwaUtils;

public class ListOfActionsItem {
	
	private String id;
	
	private String label;
	
	private String url;
	
	private String classe;

	private Boolean disabled;
	
	public ListOfActionsItem(String label, String url, String classe, Boolean disabled) {
		this(KawwaUtils.camelCase(label.split("\\s+")), label, url, classe, disabled);
	}
	
	
	public ListOfActionsItem(String id, String label, String url, String classe, Boolean disabled) {
		super();
		this.id = id;
		this.label = label;
		this.url = url;
		this.classe = classe;
		this.disabled = disabled;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}


	public Boolean getDisabled() {
		return disabled;
	}


	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	
	
}
