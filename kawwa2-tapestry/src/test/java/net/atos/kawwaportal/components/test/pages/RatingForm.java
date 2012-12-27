package net.atos.kawwaportal.components.test.pages;

import java.util.List;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.Rating;
import net.atos.kawwaportal.components.test.services.ratings.RatingService;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

public class RatingForm {
	
	@Property
	@Persist
	private Rating rating;
	
	@Property
	@Persist
	@SuppressWarnings("unused")
	private List<CriteriaDefinition> criteriasDefinition;
	
	@Persist
	@Property
	@SuppressWarnings("unused")
	private boolean submited;
	
	@Inject
	private RatingService ratingService;
	
	@SetupRender
	public void setupRender(){
		
		rating = ratingService.getFrontRating("#42", "product");
		
		criteriasDefinition = ratingService.getFormCriteriasDefinition("#42", "product");

	}
	
	@AfterRender
	public void afterRender(){
		
		submited = false;
	}
	
	@OnEvent(value="serveRatingFormSubmitted")
	public Object ratingFormSubmitted(Rating varFrontRating){
		
		rating = varFrontRating;
				
		if( rating.getTitle() != null && rating.getComment() != null)
		{
			submited = ratingService.registerRating(rating, "2012", "product", "9000", "customer");
		}
		
		return null;
	}
}
