package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class TipsyTest extends SeleniumTestCase{
	
	@Test
	public void the_tooltip_should_be_displayed(){
		
		open("Tipsy");
		
		//By default the tooltip is not visible
		new Wait()
        {
            @Override
            public boolean until()
            {
                return !isElementPresent("//div[contains(@class, 'tipsy')]");
            }
        }.wait("The tipsy tooltip is visible !!", 5000l);
        
        focus("//input[@id='values']");
        
        new Wait()
        {
            @Override
            public boolean until()
            {
                return !isElementPresent("//div[contains(@class, 'tipsy')]");
            }
        }.wait("The tipsy tooltip is visible !!", 5000l);
        
        mouseOver("//input[@id='value']");
        
        new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//div[contains(@class, 'tipsy')]");
            }
        }.wait("The tipsy tooltip is not visible !!", 5000l);
	}

    @Test
    public void the_input_should_have_original_title_attribute(){

        open("Tipsy");

        new Wait()
        {
            @Override
            public boolean until() {
                return isElementPresent("//input[@original-title]");
            }
        }.wait("The input with a tipsy should have a original-title attribute", 5000l);

    }

    @Test
    public void the_original_title_attribute_should_have_the_right_value(){

        open("Tipsy");

        new Wait()
        {
            @Override
            public boolean until() {
                return isElementPresent("//input[@original-title='title which will be displayed !']");
            }
        }.wait("The original-title attribute should have this value: title which will be displayed !", 5000l);

    }
}
