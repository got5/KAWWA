package net.atos.kawwaportal.components.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class RatingsTest extends SeleniumTestCase{

	@Test
    public void initalRatingFormTest()
    {
        open("/RatingForm");
        
        assertTextPresent("Title:");
    }
	
	@Test
    public void fillRatingFormTest()
    {
        open("/RatingForm");
        
        type("//input[@id='ratingTitle']", "Title");
        
        type("//textarea[@id='ratingComment']", "Comment");
        
        mouseOver("//*[@id='option_3']");
        click("//*[@id='option_3']");
        
        mouseOver("//*[@id='option_9']");
        click("//*[@id='option_9']");
        
        click("//*[@id='ratingFormSubmit']");
    	
    	waitForPageToLoad("10000");
    	
    	assertEquals(getText("//body/strong"),"Rating sent!");
    }
	
	@Test
    public void showRatingReviewsTest()
    {
        open("/RatingReviews");
      
        assertEquals(getText("//*[@id='reviewForm']/p/label"),"Global Rating:");
    }
}