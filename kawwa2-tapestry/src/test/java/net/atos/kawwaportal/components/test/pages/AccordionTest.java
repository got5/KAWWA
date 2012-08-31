package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.utils.JQueryAccordionData;

public class AccordionTest {
	
	@Persist
	@Property
	private int activeElement;
		
	@Property
	private List<JQueryAccordionData> faqList;
	
	@Property
	private List<TestMessage> faqMessages;
	
	@Property
	@Persist
	private TestMessage faqmessage ;
	
	@Property
	private int messageIndex ;

	
	@SetupRender
	void init()
	{
	    faqMessages = getList() ;
	    //System.out.println("faqMessages size: " + faqMessages.size());
	  	faqList = new ArrayList<JQueryAccordionData>();
			int i = 0 ;
	    for(TestMessage m: faqMessages) {
	    	faqList.add(new JQueryAccordionData(m.getTitle(), "block_"+i)) ;
	    	i++ ;
	    }	    
	}
	
	private List<TestMessage> getList() {
		ArrayList<TestMessage> ret = new ArrayList<TestMessage>() ;
		ret.add(new TestMessage("title message 1", "text message 1")) ;
		ret.add(new TestMessage("title message 2", "text message 2")) ;
		ret.add(new TestMessage("title message 3", "text message 3")) ;
		ret.add(new TestMessage("title message 4", "text message 4")) ;
		ret.add(new TestMessage("title message 5", "text message 5")) ;
		return ret;
	}
			
	
	public JSONObject getAccordionParams() {
	  JSONObject param = new JSONObject();
    param.put("autoHeight", false) ;
    param.put("collapsible", true) ;
    param.put("active", false) ;
	  return param;
	}
	
	
	public class TestMessage {
		
		private String title, message ;
		
		public TestMessage() {
			
		}
		
		public TestMessage(String title, String message) {
			setTitle(title) ;
			setMessage(message) ;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		 
	}
	
}
