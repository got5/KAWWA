package net.atos.kawwaportal.components.test.services.ratings;

import java.util.List;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.Rating;

public interface RatingService {

	public abstract boolean registerRating(Rating rating, String entityRef, String entityType, String authorRef, String authorLogin);
	
	public abstract Rating getFrontRating(String entityRef, String entityType);
	
	public abstract List<CriteriaDefinition> getFormCriteriasDefinition(String entityRef, String entityType);
	
}