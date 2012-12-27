package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class MainNavTest extends SeleniumTestCase{
	
	@Test
	public void testSiteSearch(){
	
		open("MainNav");
		
		new Wait()
	    {
	        @Override
	        public boolean until()
	        {
	            return isElementPresent("//ul[contains(@class, 'k-navbar')]");
	        }
	    }.wait("The UL element is missing.", 5000l);
	    
	    mouseOver("//ul[contains(@class, 'k-navbar')]/li[2]");
	    
	    new Wait()
	    {
	        @Override
	        public boolean until()
	        {
	            return getAttribute("//ul[contains(@class, 'k-navbar')]/li[2]/ul[contains(@class, 'dropdown')]@aria-expanded").equals("true");
	        }
	    }.wait("The dropdown list is hidden.", 5000l);
	    
	    mouseOut("//ul[contains(@class, 'k-navbar')]/li[2]");
	    
	    new Wait()
	    {
	        @Override
	        public boolean until()
	        {
	            return getAttribute("//ul[contains(@class, 'k-navbar')]/li[2]/ul[contains(@class, 'dropdown')]@aria-expanded").equals("false");
	        }
	    }.wait("The dropdown list is visible.", 5000l);
	}
	
}
