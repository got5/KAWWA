package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class LayoutTest extends SeleniumTestCase{

	@Test
    public void testMetaTags()
    {
        open("/Layout");
        
        assertTrue(isElementPresent("//head/meta[@http-equiv='X-UA-Compatible']"));
        
    }
	
}
