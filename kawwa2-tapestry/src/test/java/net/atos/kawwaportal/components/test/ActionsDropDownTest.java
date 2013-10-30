package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class ActionsDropDownTest extends SeleniumTestCase{
	@Test
	public void testDefaultBreadcrumb(){
		
		open("ActionsDropDown");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return isElementPresent("//div[@class='content']");
			}
		}.wait("Your page should contain a div with the 'content CSS class'", 5000l);
		
		
		new Wait() {
			
			@Override
			public boolean until() {
				return isElementPresent("//div[@class='content'][contains(@style, 'none')]");
			}
		}.wait("The div element with the 'content' CSS class should be hidden'", 5000l);
		
		click("//button");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return !isElementPresent("//div[@class='content'][contains(@style, 'none')]");
			}
		}.wait("The div element with the 'content' CSS class should be visible'", 5000l);
	}

    @Test
    public void testPropertyFile(){

        open("ActionsDropDown");

        new Wait() {
            @Override
            public boolean until() {
                return isTextPresent("Yo Yo Plastic");
            }
        }.wait("The 'Plastic' should be overriden by the Property file");

    }
}
