package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

/**
 * Check if Tapestry ExceptionReport page does not contain kawwaStack
 * Any other page must contain KawwaStack
 * 
 * */
public class KawwaStackExceptionReportTest extends SeleniumTestCase {
	
	private final String kawwaUrlPortion = "/kawwa2_asset/theme/css/";

	@Test
	public void testIsKawwaStackPresentInExceptionPage(){

		//Check if exception report page does not contain kawwaStack
		open("/ExceptionReport");

		new Wait() {

			@Override
			public boolean until() {
				return isElementPresent("//html");
			}
		}.wait("The ExceptionReport page has not been loaded", 5000l);

		assertFalse(isElementPresent("//link[contains(@href, '"+kawwaUrlPortion+"')]"), "The ExceptionReport page should not import kawwaStack");

		//Check if any page contains kawwaStack
		open("/");
    }
}
