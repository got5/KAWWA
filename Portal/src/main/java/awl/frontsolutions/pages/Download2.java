package awl.frontsolutions.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Response;

import awl.frontsolutions.entities.DASUser;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.internal.LogsUtils;

@Import(stack="themestack")
public class Download2 {
	
	@Inject
	private ComponentResources cr; 
	
	public String getUrl(String file){
		return cr.createEventLink(ComponentConstants.DOC_EVENT, file).toRedirectURI();
	}
	
	@Inject
	@Symbol(ComponentConstants.FILE_INDEXER_ROOT)
	private String rootPath;
	
	@SessionState
	private DASUser loggedUser;
	
	@OnEvent(ComponentConstants.DOC_EVENT)
	public StreamResponse getDoc(String path){
		
		String pathDoc = rootPath + "/documentation/"+path;
		
		final File file = new File(pathDoc);
		
		LogsUtils.log(LogsUtils.getFile("docs", rootPath), loggedUser.getLogin() + "," + pathDoc);
		
		return new StreamResponse() {
			@Override
			public void prepareResponse(Response response) {
				
				response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
			}
			
			@Override
			public InputStream getStream() throws IOException {
				return new FileInputStream(file.getAbsolutePath());}
			
			@Override
			public String getContentType() {return "application/octet-stream";}
		};
	}

}
