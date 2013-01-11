package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class DeliveryFormTest extends SeleniumTestCase{
	
	@Test
    public void initalTest()
    {
        open("/DeliveryForm");
        
        assertTextPresent("Choose delivery mode:");
    }
	
	@Test
    public void modeSelectedTest()
    {
        open("/DeliveryForm");
        
        click("//input[@name='deliveryModeRadio' and @value='1']");
        
        waitForCondition("selenium.browserbot.getCurrentWindow().$.active == 0", "10000");
        
        click("//form[@id='deliveryForm']/fieldset[@id='fsSubmitdeliveryModeForm']/input[@type='submit']");
    	
    	waitForCondition("selenium.browserbot.getCurrentWindow().$.active == 0", "10000");
    	
    	assertEquals(getText("//div[@id='resultZone']/strong[1]"), "1");
    }
	
	@Test
    public void modeAndLocationSelectedTest()
    {
        open("/DeliveryForm");
        
        click("//input[@name='deliveryModeRadio' and @value='1']");
        
        waitForCondition("selenium.browserbot.getCurrentWindow().$.active == 0", "10000");
        
        click("//input[@type='radio' and @value='13']");
        
        click("//form[@id='deliveryForm']/fieldset[@id='fsSubmitdeliveryModeForm']/input[@type='submit']");
    	
    	waitForCondition("selenium.browserbot.getCurrentWindow().$.active == 0", "10000");
    	
    	assertEquals(getText("//div[@id='resultZone']/strong[1]"), "1");
    	
    	assertEquals(getText("//div[@id='resultZone']/strong[2]"), "13");
    }

}
