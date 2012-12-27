package awl.frontsolutions;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class IndexPageTest{
	
	private WebTester tester;
	
	@Before
    public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/kawwa2");
		tester.beginAt("/"); 
    }

    @Test
    public void testTop5Block() {
       
    	tester.assertTextPresent("TOP 5 Components");
       
       tester.assertElementPresentByXPath("//div[@id='on-the-spot']/div[@class='content']/ul/li[5]");
       tester.assertElementNotPresentByXPath("//div[@id='on-the-spot']/div[@class='content']/ul/li[6]");
    }
}
