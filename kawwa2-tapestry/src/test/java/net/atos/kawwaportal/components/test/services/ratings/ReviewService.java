package net.atos.kawwaportal.components.test.services.ratings;

import java.util.List;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;

public interface ReviewService {

	public abstract ResponseReviews getReviews(String entityRef, String entityType);
	
	public abstract List<CriteriaDefinition> getFullCriteriasDefinition(String entityRef, String entityType);
	
}