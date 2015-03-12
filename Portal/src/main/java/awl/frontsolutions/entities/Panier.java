package awl.frontsolutions.entities;

import java.util.ArrayList;
import java.util.List;

import awl.frontsolutions.internal.DownloadDocType;

public class Panier {

	private List<String> listeComponent = new ArrayList<String>();

	private boolean includeTapestry;



    private boolean includeAngular;

	private boolean includeTemplate;

	private DownloadDocType doctype;

	private String theme;

	public List<String> getListeComponent() {
		return listeComponent;
	}

	public void setListeComponent(List<String> listeComponent) {
		this.listeComponent = listeComponent;
	}

	public void add(String param){
		if(!listeComponent.contains(param)) listeComponent.add(0, param);

	}

	public boolean isIncludeTapestry() {
		return includeTapestry;
	}

	public void setIncludeTapestry(boolean includeTapestry) {
		this.includeTapestry = includeTapestry;
	}

	public boolean isIncludeTemplate() {
		return includeTemplate;
	}

	public void setIncludeTemplate(boolean includeTemplate) {
		this.includeTemplate = includeTemplate;
	}

	public DownloadDocType getDoctype() {
		if(doctype == null) return DownloadDocType.HTML5;
		return doctype;
	}

	public void setDoctype(DownloadDocType doctype) {
		this.doctype = doctype;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

    public boolean isIncludeAngular() {
        return includeAngular;
    }

    public void setIncludeAngular(boolean includeAngular) {
        this.includeAngular = includeAngular;
    }
}
