package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class ProductQuantityTest extends SeleniumTestCase
{

    @Test
    public void initialTest()
    {
        open("/ProductQuantity");
        
        assertTextPresent("Formulaire classique");
        
    }
    
    @Test
    public void uppydownerTest()
    {
        open("/ProductQuantity");

        click("id=uppydownerbutton0_down");
        
        for (int i = 0; i < 2; i++) {
        	click("id=uppydownerbutton1_up");
        }
        
        for (int i = 0; i < 3; i++) {
        	click("id=uppydownerbutton2_down");
        }
        
        assertEquals(getValue("//input[@id='textfield']"), "-1");
        
        assertEquals(getValue("//input[@id='textfield_0']"), "2");
        
        assertEquals(getValue("//input[@id='textfield_1']"), "-3");
    }
    
    @Test
    public void submitTest()
    {
    	open("/ProductQuantity");
    	
    	click("id=uppydownerbutton0_up");
    	
    	click("id=uppydownerbutton1_up");
    	
    	for (int i = 0; i < 2; i++) {
    		click("id=uppydownerbutton2_up");
    	}
    	
    	click("//form[@id='quantityForm']/fieldset[@id='fsSubmit']/input[@type='submit']");
    	
    	waitForPageToLoad("10000");
    	
    	assertEquals(getText("//span/strong"), "1;1;2");
    }
    
    @Test
    public void ajaxTest()
    {
    	open("/ProductQuantity");
    	
    	for (int i = 0; i < 3; i++) {
    		click("id=uppydownerbutton3_up");
		}
    	
    	for (int i = 0; i < 5; i++) {
    		click("id=uppydownerbutton4_up");
    	}
    	
    	for (int i = 0; i < 8; i++) {
    		click("id=uppydownerbutton5_up");
    	}
    	
    	click("//form[@id='quantityAjaxForm']/fieldset[@id='fsSubmit']/input[@type='submit']");
    	
    	waitForCondition("selenium.browserbot.getCurrentWindow().$.active == 0", "10000");
    	
    	assertEquals(getText("//div[@id='resultZone']/strong"), "3;5;8");
    }
}