package net.atos.kawwaportal.components.data.ratings;

import java.util.ArrayList;
import java.util.Date;

/**
 *	<p>
 * 		This Interface provide methodes of an object that contain a review to be rendered.
 *	</p>
 */

public interface Review {

	public abstract String getLogin();

	public abstract String getTitle();

	public abstract String getComment();

	public abstract ArrayList<RatingCriteria> getCriterias();

	public abstract Date getDate();

}