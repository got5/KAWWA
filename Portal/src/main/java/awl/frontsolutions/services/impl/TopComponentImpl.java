package awl.frontsolutions.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.tapestry5.internal.TapestryInternalUtils;
import org.apache.tapestry5.ioc.annotations.Symbol;

import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.services.TopComponent;

public class TopComponentImpl implements TopComponent {
	
	private Map<String, String> components;
	
	private String rootFile;
	
	
	public TopComponentImpl(@Symbol(ComponentConstants.FILE_INDEXER_ROOT) String rootFile) {
		super();
		this.rootFile = rootFile;
	}
	
	public Map<String, String> getTopComponent() {
		if(components == null || components.size()==0) 
			setTopComponent();
		return components;
	}

	public void setTopComponent() {
		components = new LinkedHashMap<String, String>();
		Properties topComp = new Properties();
		try {
			topComp.load(new FileInputStream(rootFile + File.separatorChar + "configuration.properties"));
			String[] comps = TapestryInternalUtils.splitAtCommas((String) topComp.get("top"));
			String[] compsLibelle = TapestryInternalUtils.splitAtCommas((String) topComp.get("top_libelle"));
			
			for(int i = 0; i<comps.length; i++){
				components.put(comps[i], compsLibelle[i]);
			}
			
		} catch (Exception e) {
		}
	}
}
