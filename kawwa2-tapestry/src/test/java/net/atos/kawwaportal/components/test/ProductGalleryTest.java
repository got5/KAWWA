package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class ProductGalleryTest extends SeleniumTestCase {

	@Test
	public void testClickImg() {

		open("ProductGallery");

		click("//a[contains(@rel, 'p1_hd_1.jpg')]");

		new Wait() {

			@Override
			public boolean until() {
				return isElementPresent("//div[@class='zoomPad']/img[contains(@src, 'p1_1.jpg')]");
			}
		}.wait("When we click to the first image, the big one has to be the good one",
				5000l);

		click("//a[contains(@rel, 'p1_hd_2.jpg')]");

		new Wait() {

			@Override
			public boolean until() {
				return isElementPresent("//div[@class='zoomPad']/img[contains(@src, 'p1_2.jpg')]");
			}
		}.wait("When we click to the second image, the big one has to be the good one",
				5000l);
	}
}
