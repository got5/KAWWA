package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;
import com.thoughtworks.selenium.Wait;

public class LanguageSelectionTest extends SeleniumTestCase{
	
	/**
	 * Test the LanguageSelection component with the Radio mode
	 */
	@Test
	public void testLanguageSelectionRadio(){
	
		open("LanguageSelection");

        radio(getSelector());
		
		radio(getSelector());
		
	}
	
	/**
	 * Test the LanguageSelection component with the Select mode
	 */
	@Test
	public void testLanguageSelectionSelect(){
	
		open("LanguageSelectionSelect");

        select(getSelectorValue());
		
		select(getSelectorValue());
		
	}
	
	private void radio(final String selector){
		
		clickAndWait("//input[@id='" + selector + "']");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return getAttribute("//input[@id='" + selector + "']@checked").equalsIgnoreCase("checked") || 
				getAttribute("//input[@id='" + selector + "']@checked").equalsIgnoreCase("true");
			}
		}.wait("The radio " + selector + " input is not checked", 2000l);
		
	}
	
	private void select(final String selector){
		
		select("//select[@id='language']", "label="+selector);
		
		focus("//body");
		
		new Wait() {
			
			@Override
			public boolean until() {
				if(selector.contains("en"))
					return  getHtmlSource().contains("england");
				else return getHtmlSource().contains("france");
			}
		}.wait("The "+selector+" language should be selected", 5000l);
	}
	
	private String getSelector(){
        return isElementPresent("//label[@for='lang_radio'][contains(@class, 'ui-state-active')]") ? "lang_radio_0" : "lang_radio";
	}

	private String getSelectorValue(){
		return (getSelectedValue("//select[@id='language']").equals("fr")) ? "en" : "fr";
	}
}
