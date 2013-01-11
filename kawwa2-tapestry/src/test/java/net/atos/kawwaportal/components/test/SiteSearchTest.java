package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class SiteSearchTest extends SeleniumTestCase{

	@Test
	public void testSiteSearch(){
		
		open("SiteSearch");
		
		//Check if our component has the right CSS classes
		new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//input[contains(@class, 'k-autocomplete')]") && 
                	isElementPresent("//form[contains(@class, 'k-search')]");
            }
        }.wait("The Autocomplete classes are missing.", 5000l);
        
        //Check if the input has the placeholder value
        new Wait()
        {
            @Override
            public boolean until()
            {
                return getValue(getAutocompleteField()).equalsIgnoreCase("Search...");
            }
        }.wait("The input do not have the PlaceHolder value " + getValue("//input[contains(@class, 'k-autocomplete')]"), 5000l);
        
        type(getAutocompleteField(), "aaa");
        keyDown(getAutocompleteField(), "a");
        keyUp(getAutocompleteField(), "a");
        fireEvent(getAutocompleteField(), "keydown");
        
        //Check the autocomplete mechanism
        new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//ul[contains(@class, 'ui-autocomplete')]/li");
            }
        }.wait("The autocomplete list is missing.", 5000l);
	}
	
	public String getAutocompleteField(){
		return "//input[contains(@class, 'k-autocomplete')]";
	}
}
