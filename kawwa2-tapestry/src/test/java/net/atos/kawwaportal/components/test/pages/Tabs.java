package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.got5.tapestry5.jquery.utils.JQueryTabData;

public class Tabs {

	
	@Persist
	@Property
	private String activePanel;

	@Persist
	@Property
	private Date sysDate;

	@Property
	private List<JQueryTabData> listTabDataDemo;

	@OnEvent(EventConstants.ACTIVATE)
	void onSetupRender()
	{
		listTabDataDemo = new ArrayList<JQueryTabData>();
	    listTabDataDemo.add(new JQueryTabData("Panel1","block1"));
	    listTabDataDemo.add(new JQueryTabData("Panel2","block2"));
	    listTabDataDemo.add(new JQueryTabData("Panel3","block3"));
	    listTabDataDemo.add(new JQueryTabData("Panel4","block4"));
	}
		
	
}
