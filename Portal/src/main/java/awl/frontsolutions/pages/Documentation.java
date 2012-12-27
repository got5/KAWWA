package awl.frontsolutions.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;

import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.internal.LogsUtils;
import awl.frontsolutions.services.MailService;

@Import(stack="themestack")
public class Documentation {
	
	@Inject
	@Symbol(ComponentConstants.FILE_INDEXER_ROOT)
	private String rootPath;

	@Inject
	private ComponentResources cr; 
	
	@Inject
	private MailService mail;
	
	@Inject 
	private RequestGlobals request;
	
	public String getUrl(String file){
		return cr.createEventLink(ComponentConstants.DOC_EVENT, file).toRedirectURI();
	}

	@OnEvent(ComponentConstants.DOC_EVENT)
	public StreamResponse getDoc(String path) throws Exception{
		
		String pathDoc = rootPath + "/documentation/"+path;
		
		final File file = new File(pathDoc);
	
		if(InternalUtils.isBlank(request.getRequest().getHeader("DNT"))){
			LogsUtils.log(LogsUtils.getFile("docs", rootPath), pathDoc);
			mail.sendMailToDev("Downloading files", pathDoc);
		}
		
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
