package net.atos.kawwaportal.components.data.ratings;

import java.util.ArrayList;

/**
 *	<p>
 * 		This Interface provide methodes of an object that contain a review of a product and its rantings to be setted.
 *	</p>
 */

public interface Rating {

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract String getComment();

	public abstract void setComment(String comment);

	public abstract ArrayList<RatingCriteria> getCriterias();
	
	public abstract void setCriterias(ArrayList<RatingCriteria> criterias);
	
}