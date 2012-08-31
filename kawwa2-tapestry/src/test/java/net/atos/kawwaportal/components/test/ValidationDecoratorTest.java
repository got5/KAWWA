package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;


public class ValidationDecoratorTest extends SeleniumTestCase{

	/**
	 * This method will check if the inputs have the k-required CSS class
	 */
	@Test
	public void testRequiredClass(){
		
		open("Form");
		
		
		new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//input[@id='value'][contains(@class, 'k-required')]");
                	
            }
        }.wait("The TextField input do not have the k-required class.", 5000l);
        
        new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//input[@id='second'][contains(@class, 'k-required')]");
                	
            }
        }.wait("The PasswordField input do not have the k-required class.", 5000l);
	}
	
	/**
	 * This method will check if the inputs have the k-field-error CSS class, the span with error message
	 * and the Errors component 
	 */
	@Test
	public void testErrorClass() throws InterruptedException{
		
		open("Form");
		
		click("//input[@type='submit']");
		
		 new Wait()
	     {
            @Override
            public boolean until()
            {
                return isElementPresent("//input[@id='value'][contains(@class, 'k-field-error')]");
                	
            }
	     }.wait("The TextField input do not have the k-field-error class.", 5000l);
		
	     new Wait()
	     {
            @Override
            public boolean until()
            {
                return isElementPresent("//input[@id='second'][contains(@class, 'k-field-error')]");
                	
            }
	     }.wait("The PasswordField input do not have the k-field-error class.", 5000l);
	     
	     new Wait()
	     {
            @Override
            public boolean until()
            {
                return isElementPresent("//label[contains(@class, 'k-field-error')]");
                	
            }
	     }.wait("The label do not have the k-field-error class.", 5000l);
		
	     new Wait()
	     {
            @Override
            public boolean until()
            {
                return isElementPresent("//span[contains(@class, 'k-contextual-error')]");
                	
            }
	     }.wait("The span tag with the error message is not present", 5000l);
		
	     new Wait()
	     {
            @Override
            public boolean until()
            {
                return isElementPresent("//div[contains(@class, 'k-error-messages')]");
                	
            }
	     }.wait("The Errors with the k-error-messages is not present", 5000l);
	}
	
	
	/**
	 * This method will check the p tag with the k-mandatory class is present
	 */
	@Test
	public void testMandatoryClass(){
		
		open("Form");
		
		new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent("//p[contains(@class, 'k-mandatory')]");
                	
            }
        }.wait("The p tag with the k-mandatory class is not present.", 5000l);
    }
}
