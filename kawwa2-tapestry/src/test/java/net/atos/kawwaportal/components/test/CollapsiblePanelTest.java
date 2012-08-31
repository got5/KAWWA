package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class CollapsiblePanelTest extends SeleniumTestCase{
	
	@Test
	public void testCollapsiblePanel(){
		
		open("Collapsible");
		
		//By default the panel is visible
		new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//div[@id='demo1']//h4[contains(@id, 'ui-panel')][contains(@class, 'ui-state-active')]");
            }
        }.wait("The h4 tag do not have the ui-state-active class", 5000l);
        
        click("id=ui-panel-0");
        
        new Wait()
        {
            @Override
            public boolean until()
            {
                return !isElementPresent("//div[@id='demo1']//h4[contains(@id, 'ui-panel')][contains(@class, 'ui-state-active')]");
            }
        }.wait("The h4 tag do not have the ui-state-active class", 5000l);
	}
	
	
	@Test
	public void testOverrideHeader(){
		
		open("Collapsible");
		
		new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//div[@id='demo2']//h3");
            }
        }.wait("The h3 element is missing ", 5000l);
	}
}
