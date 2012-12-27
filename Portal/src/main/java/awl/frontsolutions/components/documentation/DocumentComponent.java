package awl.frontsolutions.components.documentation;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.annotations.Parameter;

public class DocumentComponent {
	
	@Parameter
	private String urlParam;
	
	public boolean showFile(String fileContent) {

		if (urlParam.equalsIgnoreCase("pageStructure"))
			return false;

		return showFile(fileContent, true);
	}

	public boolean showFile(String fileContent, boolean flag) {

		return StringUtils.isNotEmpty(fileContent);
	}
}
