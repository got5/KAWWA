package net.atos.kawwaportal.components.components;

import java.util.ArrayList;
import java.util.List;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.OptionDefinition;
import net.atos.kawwaportal.components.data.ratings.Rating;
import net.atos.kawwaportal.components.data.ratings.RatingCriteria;

import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.internal.util.Holder;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * <p>
 * 		This Component is used to call a rating form that will provide 3 types of fields
 * 		<ul>
 * 			<li>One field "title" to record the title of the rating</li>
 * 			<li>One field "comment" to record the full comment of the rating</li>
 * 			<li>Multiple fields of "rate" to record the rate of each criteria specified</li>
 * 		</ul>
 * 
 *  	To use it, call the component with '&lt;div t:type="kawwa2/RatingForm" t:id="ratingForm" t:rating="rating" t:criteriasDefinition="criteriasDefinition"/&gt;'.
 * </p>
 * 
 * <p>
 * 		There is parameters to call for the form (These two parameter must be persisted by the component caller):
 * 		<ul>
 * 			<li>t:rating="&lt;&lt;!&gt;&gt;" ==> This parameter is Mandatory. Replace &lt;&lt;!&gt;&gt; by the instance of formRating. All the details of the field Criterias will be setted in function of the criteriasDefinition.</li>
 * 			<li>t:criteriasDefinition="&lt;&lt;!&gt;&gt;" ==> This parameter is Mandatory. Replace &lt;&lt;!&gt;&gt; by the list of CriteriaDefinition that will define the criterias passed with the instance of rating.</li>
 * 		</ul>
 * </p>
 * 
 * @tapestrydoc
 * @component_version 1.0
 */

@Import(stylesheet = "classpath:/net/atos/kawwaportal/components/assets/ratings/ratings.css")
public class RatingForm {

	@Parameter(required=true)
	@Property
	private Rating rating;

	@Parameter(required=true)
	@Property
	private List<CriteriaDefinition> criteriasDefinition;
	
	@Property
	@SuppressWarnings("unused")
	private RatingCriteria currentCriteria;

	@Inject
	private ComponentResources resources;
	
	@Property
	@SuppressWarnings("unused")
    private final ValueEncoder<RatingCriteria> criteriaEncoder = new ValueEncoder<RatingCriteria>() {
        public String toClient(RatingCriteria value) {
        	
        	return value.getRef();
    	}

        public RatingCriteria toValue(String clientValue) {
        	
            return getRatingCriteriaByRef(clientValue);
        }
    };
	
    public RatingCriteria getRatingCriteriaByRef(String ref) {
    	
    	RatingCriteria selectedCriteria = null;
    	
    	for(RatingCriteria currentCriteria : rating.getCriterias()) {
    		
    		if(currentCriteria.getRef().equals(ref))
    			selectedCriteria = currentCriteria;
    	}
    	
    	return selectedCriteria;
    }
    
    public CriteriaDefinition getCriteriaDefinitionByRatingCriteria(RatingCriteria ratingCriteria) {
    	
    	CriteriaDefinition criteriaDefinition = null;
    	
    	for(CriteriaDefinition currentCriteria : criteriasDefinition) {
    		
    		if(ratingCriteria.getRef().equals(currentCriteria.getRef()))
    			criteriaDefinition = currentCriteria;
    	}
    	
    	return criteriaDefinition;
    }
    
    private OptionDefinition getOptionDefinitionByValue(RatingCriteria ratingCriteria, String optionValue) {
		
		OptionDefinition optionDefinition = null;
		
		for(OptionDefinition currentOptionDefinition : getCriteriaDefinitionByRatingCriteria(ratingCriteria).getOptions()) {
			
			if(optionValue.equals(currentOptionDefinition.getOptionValue()))
				optionDefinition = currentOptionDefinition;
		}
		
		return optionDefinition;
	}
    
    private void completeCriteriasLabel() {
		
		for(RatingCriteria ratingCriteria : rating.getCriterias()) {
			
			ratingCriteria.setOptionLabel(getOptionDefinitionByValue(ratingCriteria, ratingCriteria.getOptionValue()).getOptionLabel());
		}
	}
    
    @SetupRender
    @SuppressWarnings("unused")
    private void setRatingCriterias() {
    	
    	if(rating.getCriterias() == null || "0".equals(rating.getCriterias().size())) {
    		
    		rating.setCriterias(new ArrayList<RatingCriteria>());
    		
    		for(CriteriaDefinition criteriaDefinition : criteriasDefinition) {
    			
    			RatingCriteria ratingCriteria = new RatingCriteria();
    			ratingCriteria.setRef(criteriaDefinition.getRef());
    			ratingCriteria.setLabel(criteriaDefinition.getLabel());
    			
    			rating.getCriterias().add(ratingCriteria);
    		}
    	}
    }
	
	@OnEvent(value = EventConstants.SUCCESS, component = "ratingForm")
	@SuppressWarnings("unused")
	private Object submit() {
		
		completeCriteriasLabel();
		
		Object[] contextValues = new Object[] {rating};

		final Holder<Object> holder = new Holder<Object>();

		resources.triggerEvent("serveRatingFormSubmitted", contextValues,
				new ComponentEventCallback<Object>() {
					public boolean handleResult(Object result) {
						holder.put(result);
						return false;
					}
				});

		return holder.get();
	}
}
