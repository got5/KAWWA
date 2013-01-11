package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class BreadCrumbTest extends SeleniumTestCase {
	
	@Test
	public void testDefaultBreadcrumb(){
		
		open("BreadCrumb");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return isElementPresent("//div[@id='demo1']/p/a[contains(@href, '/breadcrumb')]");
			}
		}.wait("The a element is missing.", 5000l);
		
		new Wait() {
			
			@Override
			public boolean until() {
				return getText("//div[@id='demo1']/p").contains("THE breadcrum ! > THE breadcrum !");
			}
		}.wait("We should have 2 THE breadcrumb", 5000l);
	}
	
	@Test
	public void testBreadcrumbWithDifferentSpliChar(){
		
		open("BreadCrumb");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return getText("//div[@id='demo2']/p").contains("&");
			}
		}.wait("The Splitting char should be '&'", 5000l);
	}
	
	@Test
	public void testBreadCrumbWithDifferentProvider(){
		
		open("BreadCrumb");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return getText("//div[@id='demo3']/p/a[contains(@href, '/breadcrumb')]").contains("BreadCrumb");
			}
		}.wait("The Splitting char should be '&'", 5000l);
	}
}
