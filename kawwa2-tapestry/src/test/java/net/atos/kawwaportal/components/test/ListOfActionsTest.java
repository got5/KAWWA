package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class ListOfActionsTest extends SeleniumTestCase {

	@Test
	public void testActionButtonsBar() {
		open("ListOfActions");

		new Wait() {

			@Override
			public boolean until() {
				return isElementPresent("//div[@id='sample2']/div[@class='k-actions-block']");
			}
		}.wait("The Second example should have a DIV with the CSS class k-actions-block",
				5000l);
	}

	@Test
	public void testActionButtons() {
		open("ListOfActions");

		new Wait() {

			@Override
			public boolean until() {
				return isElementPresent("//div[@id='sample1']/ul[@class='k-actions']");
			}
		}.wait("The First example should have a UL with the CSS class k-actions",
				5000l);
	}

	@Test
	public void testIconButtons() {
		open("ListOfActions");

		new Wait() {

			@Override
			public boolean until() {
				return isElementPresent("//div[@id='sample3']/ul[@class='actions']");
			}
		}.wait("The Third example should have a UL with the CSS class actions",
				5000l);
	}

	@Test
	public void testOverrideBlock() {
		open("ListOfActions");

		new Wait() {

			@Override
			public boolean until() {
				return getText("//div[@id='sample4']/ul/li/a").contains("Override Action");
			}
		}.wait("The Fourth example should be overriden",
				5000l);
	}
}
