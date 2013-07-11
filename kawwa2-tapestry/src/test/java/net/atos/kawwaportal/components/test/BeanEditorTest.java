package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class BeanEditorTest extends SeleniumTestCase{
	@Test
	public void testDefaultBreadcrumb(){
		
		open("BeanEditor");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return !isElementPresent("//div[contains(@class, 't-beaneditor-row')]");
			}
		}.wait("You page should not contain a div with a CSS class t-beaneditor-row", 5000l);
	}
}
