package awl.frontsolutions.services;

import java.io.IOException;

import org.apache.tapestry5.internal.services.ResourceStreamer;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

import awl.frontsolutions.internal.ComponentConstants;

public class KawwaAsset implements Dispatcher {

	private String componentPath;
	
	private final ResourceStreamer resourceStreamer;
	
	
	public KawwaAsset(@Symbol(ComponentConstants.FILE_INDEXER_ROOT) String componentPath, 
			ResourceStreamer resourceStreamer) {
		super();
		this.componentPath = componentPath;
		this.resourceStreamer = resourceStreamer;
	}


	@Override
	public boolean dispatch(Request request, Response response) throws IOException {
		
		
		String path = request.getPath();
		
		
		
		return false;
	}
}
