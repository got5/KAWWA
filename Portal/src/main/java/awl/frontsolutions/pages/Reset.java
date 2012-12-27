package awl.frontsolutions.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.services.FileSystemIndexer;

/**
 * Start page of application frontsolutions.
 */
@Import(stack="themestack", stylesheet="context:css/reset.css")
public class Reset {

	@Inject
	private FileSystemIndexer indexer;
	
	@OnEvent("resetComponents")
	public Object resetComponents(){
		indexer.reParseFileStructure();
		return Components.class;
	}


}
