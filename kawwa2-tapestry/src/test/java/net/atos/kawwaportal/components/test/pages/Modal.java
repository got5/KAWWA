package net.atos.kawwaportal.components.test.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;

public class Modal {


	@Property
	private JSONObject params;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void onActivate(){
		params=new JSONObject();
		params.accumulate("modal", true);
	}
	@Component
	private Zone myZone;
	
	@Persist 
    private Integer count;

    @Inject
    private Request request;

    @Property
    private String goalName;
    
    @OnEvent(EventConstants.ACTIVATE)
    void init()
    {
        if (count == null)
            count = 0;
    }

    public Integer getCount()
    {
        return count++;
    }

    @OnEvent(EventConstants.ACTION)
    Object updateCount()
    {
        if (!request.isXHR()) { return this; }
        return myZone;
    }

}
