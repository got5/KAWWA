package awl.frontsolutions.entities;

import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.stack.ThemeStack;

import java.io.Serializable;

public class ChoosenTheme implements Serializable{
	
 
	
	//Put the annotation to force this constructor by default
	@Inject
	public ChoosenTheme() {
		super();
		themeName = ThemeStack.DEFAULT_THEME;
		dir = "k-theme0";
	}

	public ChoosenTheme(String themeName, String dir) {
		super();
		this.themeName = themeName;
		this.dir = dir;
	}

	private String themeName;
	private String dir;

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	
	
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return themeName;
	}
}