package net.atos.kawwaportal.components.test.services.ratings;

import java.util.ArrayList;

import net.atos.kawwaportal.components.data.ratings.RatingCriteria;
import net.atos.kawwaportal.components.data.ratings.Review;


public class ResponseReviews {
	
	private RatingCriteria globalRating;
	
	private ArrayList<Review> ratings;

	public RatingCriteria getGlobalRating() {
		return globalRating;
	}

	public void setGlobalRating(RatingCriteria globalRating) {
		this.globalRating = globalRating;
	}

	public ArrayList<Review> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Review> ratings) {
		this.ratings = ratings;
	}
}