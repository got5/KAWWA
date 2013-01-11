package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class TipsyTest extends SeleniumTestCase{
	
	@Test
	public void testTipsy(){
		
		open("Tipsy");
		
		//By default the tooltip is not visible
		new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//div[contains(@class, 'tipsy')]");
            }
        }.wait("The tipsy tooltip is not visible !!", 5000l);
        
        focus("id=values");
        
        new Wait()
        {
            @Override
            public boolean until()
            {
                return !isElementPresent("//div[contains(@class, 'tipsy')]");
            }
        }.wait("The tipsy tooltip is visible !!", 5000l);
        
        focus("id=value");
        
        new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//div[contains(@class, 'tipsy')]");
            }
        }.wait("The tipsy tooltip is not visible !!", 5000l);
	}
	
}
