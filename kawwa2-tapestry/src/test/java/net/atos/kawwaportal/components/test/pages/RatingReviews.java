package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.RatingCriteria;
import net.atos.kawwaportal.components.data.ratings.Review;
import net.atos.kawwaportal.components.test.services.ratings.ResponseReviews;
import net.atos.kawwaportal.components.test.services.ratings.ReviewService;

public class RatingReviews {
	
	@Property
	@SuppressWarnings("unused")
	private RatingCriteria globalRating;
	
	@Property
	@SuppressWarnings("unused")
	private ArrayList<Review> ratingReviews;
	
	@Property
	@SuppressWarnings("unused")
	private List<CriteriaDefinition> criteriasDefinition;
	
	@Inject
	private ReviewService reviewService;
	
	@OnEvent(value=EventConstants.ACTIVATE)
	public void activate(){
		
		ResponseReviews ratings = reviewService.getReviews("42", "product");
		
		globalRating = ratings.getGlobalRating();
		ratingReviews = ratings.getRatings();
		
		criteriasDefinition = reviewService.getFullCriteriasDefinition("42", "product");
	}
}
