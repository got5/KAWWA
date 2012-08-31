package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class Kawwa2GridTests  extends SeleniumTestCase{
	
	@Test
	public void testDefaultNbRows(){
		
		open("Kawwa2Grid");
		
		checkNumbRows(getValue("//select").toString());
    }
	
	@Test(dependsOnMethods="testDefaultNbRows")
	public void testNbRowsFive(){
		
		open("Kawwa2Grid");
		
		select("//select", "label=5");
		
		//The selenium select method does not fire the onChange event. We need to change the focus.
		focus("//body");
		
		checkNumbRows("5");
		
		checkArrow("pic_first_off.gif");
		checkArrow("pic_prev_off.gif");
		checkArrow("pic_last.gif");
		checkArrow("pic_next.gif");
     
		checkCurrentPage("1");
    }
	
	@Test(dependsOnMethods="testNbRowsFive")
	public void testNbRowsTen(){
		
		open("Kawwa2Grid");
		
		checkNumbRows("5");
		
		select("//select","label=10");
		
		focus("//body");
		
		checkNumbRows("10");
		
		checkArrow("pic_first_off.gif");
		checkArrow("pic_prev_off.gif");
		checkArrow("pic_last.gif");
		checkArrow("pic_next.gif");
        
		checkCurrentPage("1");
    }
	
	@Test(dependsOnMethods="testNbRowsTen")
	public void testNbRowsFifteen(){
		
		open("Kawwa2Grid");
		
		checkNumbRows("10");
		
		select("//select", "label=15");
		
		focus("//body");
		
		checkNumbRows("15");
        
		checkArrow("pic_first_off.gif");
		checkArrow("pic_prev_off.gif");
		checkArrow("pic_last_off.gif");
		checkArrow("pic_next_off.gif");
		
		checkCurrentPage("1");
    }
	
	private void checkNumbRows(final String expected){
		
		new Wait()
        {
            @Override
            public boolean until()
            {
                return getXpathCount("//div[@class='t-data-grid']/table/tbody/tr").toString().equalsIgnoreCase(expected);
            }
        }.wait("We should have " + expected + " rows, but we have " + getXpathCount("//div[@class='t-data-grid']/table/tbody/tr"), 5000l);
        
	}
	
	private void checkArrow(final String img){
		
		new Wait()
        {
            @Override
            public boolean until()
            {
                return (isElementPresent("//img[contains(@src, '"+img+"')]"));
            }
        }.wait("The pictures are not the good ones. ", 5000l);
	}

	private void checkCurrentPage(final String expected){
		
		new Wait()
        {
            @Override
            public boolean until()
            {
                return getText("//p[@class='page-numbers']/strong").equalsIgnoreCase(expected);
            }
        }.wait("The current page should be " + expected, 5000l);
		
	}
}
