package net.atos.kawwaportal.components.components;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.OptionDefinition;
import net.atos.kawwaportal.components.data.ratings.RatingCriteria;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.internal.services.StringValueEncoder;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>
 * 		This Component is used to call RadioGgroup displayed as a Star Rating
 * 
 * 		<br/><br/>
 * 
 *  	To use it, call the component with '&lt; t:kawwa2.rating t:ratingCriteria="currentCriteria"
				t:criteriaDefinition="getCriteriaDefinitionByRatingCriteria(currentCriteria)" t:renderMode="renderMode" /&gt;'.
 * </p>
 * 
 * <p>
 * 		There is parameters to call for the displayingform:
 * 		<ul>
 * 			<li>t:criteriaDefinition="&lt;&lt;!&gt;&gt;" ==> This parameter is Mandatory. Replace &lt;&lt;!&gt;&gt; by the CriteriaDefinition that will define the criteria displayed as the RadioGroup.</li>
 * 			<li>t:ratingCriteria="&lt;&lt;!&gt;&gt;" ==> This parameter is Mandatory. Replace &lt;&lt;!&gt;&gt; by the rating you want to be displayed. In case of a form, the value will be setted by the predefined field contained in the criteriaDefinition.
 * 					In case of rendering, this value will be setted according to the OptionValue field of the ratingCriteria</li>
 * 			<li>t:renderMode="&lt;&lt;!&gt;&gt;" ==> Replace &lt;&lt;!&gt;&gt; by the boolean that will determine if the RadioGroup is fixed or not.</li>
 * 		</ul>
 * </p>
 * 
 * @tapestrydoc
 * @component_version 1.0
 */

@Import(library = {"classpath:/net/atos/kawwaportal/components/assets/ratings/jquery.rating.pack.js",
		"classpath:/net/atos/kawwaportal/components/assets/ratings/rating.js",
		"classpath:/net/atos/kawwaportal/components/assets/ratings/ratings.css"})
public class Rating {
	
	@Parameter(required=true)
	@Property
	private CriteriaDefinition criteriaDefinition;
	
	@Parameter(required=true)
	@Property
	private RatingCriteria ratingCriteria;
	
	@Parameter("false")
	@Property
	private Boolean renderMode;
	
	@Property
	@SuppressWarnings("unused")
	private OptionDefinition currentOptionDefinition;
	
	@Property
	@SuppressWarnings("unused")
	private final StringValueEncoder stringEncoder = new StringValueEncoder();
	
	@InjectComponent("ratingRadioGroup") 
	private RadioGroup ratingRadioField;
	
	@Inject
	private Messages messages;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Property
	@SuppressWarnings("unused")
    private final ValueEncoder<OptionDefinition> optionEncoder = new ValueEncoder<OptionDefinition>() {
        public String toClient(OptionDefinition value) {
        	
        	return value.getOptionValue();
    	}

        public OptionDefinition toValue(String clientValue) {
        	
            return getOptionDefinitionByValue(clientValue);
        }
    };
	
    private OptionDefinition getOptionDefinitionByValue(String value) {
    	
    	OptionDefinition selectedOption = null;
    	
    	for(OptionDefinition currentOption : criteriaDefinition.getOptions()) {
    		
    		if(currentOption.getOptionValue().equals(value))
    			selectedOption = currentOption;
    	}
    	
    	return selectedOption;
    }
    
    public String getRatingCriteriaLabel() {
		
		String currentLabel;
		
		if(messages.contains(ratingCriteria.getRef()))
			currentLabel = messages.get(ratingCriteria.getRef());
		else if(ratingCriteria.getLabel() != null)
			currentLabel = ratingCriteria.getLabel();
		else
			currentLabel = ratingCriteria.getRef();
		
		return currentLabel;
	}
    
    @SetupRender
    @SuppressWarnings("unused")
    private void checkInitialIntegrity() {
    	
    	if(!renderMode) {
    		if(criteriaDefinition.getInitialValue() != null) {
	    		ratingCriteria.setOptionValue(criteriaDefinition.getInitialValue());
	    		ratingCriteria.setOptionLabel(getOptionDefinitionByValue(criteriaDefinition.getInitialValue()).getOptionLabel());
    		}
    	}
    	else {
    		ratingCriteria.setOptionLabel(getOptionDefinitionByValue(ratingCriteria.getOptionValue()).getOptionLabel());
    	}
    }
    
    @AfterRender
    @SuppressWarnings("unused")
	private void afterRender() {
    	
    	JSONObject option = new JSONObject();
	    option.put("target", ratingRadioField.getControlName());
	    
    	javaScriptSupport.addInitializerCall("rating", option);
    }
}
