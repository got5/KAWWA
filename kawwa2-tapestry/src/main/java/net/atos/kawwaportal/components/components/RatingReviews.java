package net.atos.kawwaportal.components.components;

import java.util.ArrayList;
import java.util.List;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.RatingCriteria;
import net.atos.kawwaportal.components.data.ratings.Review;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * <p>
 * 		This Component is used to provide a list of reviews.
 * 
 * 		<br/><br/>
 * 
 *  	To use it, call the component with '&lt;div t:type="kawwa2/RatingReviews" t:id="ratingReviews"
			t:globalRating="globalRating" t:ratingReviews="ratingReviews"
			t:criteriasDefinition="criteriasDefinition"/&gt;'.
 * </p>
 * 
 * <p>
 * 		There is parameters to call for the displayingform:
 * 		<ul>
 * 			<li>t:globalRating="&lt;&lt;!&gt;&gt;" ==> Replace &lt;&lt;!&gt;&gt; by the object of the average value for the main rating criteria of the reviews.</li>
 * 			<li>t:ratingReviews="&lt;&lt;!&gt;&gt;" ==> This parameter is Mandatory. Replace &lt;&lt;!&gt;&gt; by the list of reviews you want to be displayed.</li>
 * 			<li>t:criteriasDefinition="&lt;&lt;!&gt;&gt;" ==> This parameter is Mandatory. Replace &lt;&lt;!&gt;&gt; by the list of CriteriaDefinition that will define the criterias passed with the instance of ratingReviews.</li>
 * 		</ul>
 * </p>
 * 
 * @tapestrydoc
 */

@Import(stylesheet = "classpath:/net/atos/kawwaportal/components/assets/ratings/ratings.css")
public class RatingReviews {
	
	@Parameter
	@Property
	private RatingCriteria globalRating;

	@Parameter(required=true)
	@Property
	@SuppressWarnings("unused")
	private ArrayList<Review> ratingReviews;
	
	@Parameter(required=true)
	@Property
	private List<CriteriaDefinition> criteriasDefinition;
	
	@Property
	@SuppressWarnings("unused")
	private Review currentRating;
	
	@Property
	@SuppressWarnings("unused")
	private RatingCriteria currentCriteria;
	
	public boolean getGlobalRatingIsSetted() {
		
		return globalRating != null;
	}
	
	public CriteriaDefinition getCriteriaDefinitionByRatingCriteria(RatingCriteria ratingCriteria) {
    	
    	CriteriaDefinition criteriaDefinition = null;
    	
    	for(CriteriaDefinition currentCriteria : criteriasDefinition) {
    		
    		if(ratingCriteria.getRef().equals(currentCriteria.getRef()))
    			criteriaDefinition = currentCriteria;
    	}
    	
    	return criteriaDefinition;
    }
}
