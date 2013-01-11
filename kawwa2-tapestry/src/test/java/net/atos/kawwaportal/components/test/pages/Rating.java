package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinitionImpl;
import net.atos.kawwaportal.components.data.ratings.OptionDefinition;
import net.atos.kawwaportal.components.data.ratings.OptionDefinitionImpl;
import net.atos.kawwaportal.components.data.ratings.RatingCriteria;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Rating {

	@Property
	@Persist
	private CriteriaDefinitionImpl criteriaDefinition;
	
	@Property
	@Persist
	private RatingCriteria ratingCriteria;
	
	public void setupRender(){
		
		criteriaDefinition = new CriteriaDefinitionImpl();
		criteriaDefinition.setOptions(getOptions());
		criteriaDefinition.setLabel("Avis");
		criteriaDefinition.setInitialValue("1");
		criteriaDefinition.setRef("ref1");
		
		if(ratingCriteria==null){
			ratingCriteria = new RatingCriteria();
			ratingCriteria.setLabel("Label");
			ratingCriteria.setOptionLabel("Really Bad");
			ratingCriteria.setOptionValue("1");
			ratingCriteria.setRef("ref1");
		}
	}
	
	public ArrayList<OptionDefinition> getOptions(){
		ArrayList<OptionDefinition> optionsList1 = new ArrayList<OptionDefinition>();
		
		OptionDefinitionImpl frontRatingOption11 = new OptionDefinitionImpl();
		frontRatingOption11.setOptionValue("1");frontRatingOption11.setOptionLabel("Really Bad");
		optionsList1.add(frontRatingOption11);
		
		OptionDefinitionImpl frontRatingOption12 = new OptionDefinitionImpl();
		frontRatingOption12.setOptionValue("2");frontRatingOption12.setOptionLabel("Bad");
		optionsList1.add(frontRatingOption12);
		
		OptionDefinitionImpl frontRatingOption13 = new OptionDefinitionImpl();
		frontRatingOption13.setOptionValue("3");frontRatingOption13.setOptionLabel("Normal");
		optionsList1.add(frontRatingOption13);
		
		OptionDefinitionImpl frontRatingOption14 = new OptionDefinitionImpl();
		frontRatingOption14.setOptionValue("4");frontRatingOption14.setOptionLabel("Good");
		optionsList1.add(frontRatingOption14);
		
		OptionDefinitionImpl frontRatingOption15 = new OptionDefinitionImpl();
		frontRatingOption15.setOptionValue("5");frontRatingOption15.setOptionLabel("Really Good");
		optionsList1.add(frontRatingOption15);
		
		return optionsList1;
	}
}
