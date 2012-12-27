package awl.frontsolutions.services;

import org.apache.tapestry5.services.PageRenderRequestParameters;

public interface DASSecurityManager {
	boolean isUserAuthenticated(PageRenderRequestParameters parameters);
}
