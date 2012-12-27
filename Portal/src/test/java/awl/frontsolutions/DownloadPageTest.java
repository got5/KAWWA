package awl.frontsolutions;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class DownloadPageTest {
	private WebTester tester;

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/kawwa2");
		tester.beginAt("/Download");
	}
	
	@Test
	public void testLabelWithForAttribute(){
		tester.assertElementNotPresent("//label[not(@for)]");
	}
	
	@Test
	public void testHtml5andXhtmlDoctypes() {
		tester.assertElementPresentByXPath("//input[@type='radio'][@id='xhtml']");
		tester.assertElementPresentByXPath("//input[@type='radio'][@id='html5']");
		
	}

	@Test
	public void testHtml5DoctypeByDefault() {
		tester.assertElementPresentByXPath("//input[@type='radio'][@id='html5'][@checked='checked']");
		tester.assertElementNotPresentByXPath("//input[@type='radio'][@id='xhtml'][@checked='checked']");
	}
}
