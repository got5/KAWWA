package awl.frontsolutions.services.atos;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.RequestGlobals;

import awl.frontsolutions.entities.DASUser;
import awl.frontsolutions.services.AtosService;

public class AtosServiceImpl implements AtosService{
	
	private ApplicationStateManager manager;
	private RequestGlobals request;
	
	List<String> ips;
	
	public AtosServiceImpl(ApplicationStateManager manager, RequestGlobals request) {
		super();
		this.manager = manager;
		this.request = request;
		ips = new ArrayList<String>();
	}

	@Override
	public boolean isAtosMember() {
		return manager.exists(DASUser.class) || ips.contains(request.getHTTPServletRequest().getRemoteAddr());
	}

	@Override
	public void addIpAdress(String ip) {
		if(!ips.contains(ip)) {
			ips.add(ip);
		}
		
	}
	
	

}
