package awl.frontsolutions.services.impl;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.RequestGlobals;

import awl.frontsolutions.services.DASSecurityManager;

public class DASSecurityManagerImpl implements DASSecurityManager{
	@Inject 
	private RequestGlobals requestGlobals;

	public boolean isUserAuthenticated(PageRenderRequestParameters parameters) {
		if(parameters.getLogicalPageName().equals("Login"))
			return true;
		else {
			Object user = requestGlobals.getHTTPServletRequest().getSession().getAttribute("sso:awl.frontsolutions.entities.DASUser");
			if(user!=null)
				return true;
		}
		return false;
	}
}