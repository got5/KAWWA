package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;

public class SiteSearch {
	@Property
	private String search;
	
	@OnEvent(EventConstants.PROVIDE_COMPLETIONS)
	public List<String> getResults(String input){
		System.out.println("###### getResults method");
		List<String> results = new ArrayList<String>();
		if(input.startsWith("a")){
			results.add("abcd");
			results.add("abcd1");
			results.add("abcd2");
		}
		return results;
	}
	
	@OnEvent(value=EventConstants.SUBMIT, component="search")
	public void submit(){
		System.out.println("##### " + search);
	}
}
