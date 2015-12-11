package net.atos.kawwaportal.components.test.services.ratings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.atos.kawwaportal.components.data.ratings.CriteriaDefinition;
import net.atos.kawwaportal.components.data.ratings.CriteriaDefinitionImpl;
import net.atos.kawwaportal.components.data.ratings.Rating;
import net.atos.kawwaportal.components.data.ratings.RatingCriteria;
import net.atos.kawwaportal.components.data.ratings.RatingImpl;
import net.atos.kawwaportal.components.data.ratings.OptionDefinition;
import net.atos.kawwaportal.components.data.ratings.OptionDefinitionImpl;
import net.atos.kawwaportal.components.data.ratings.Review;
import net.atos.kawwaportal.components.data.ratings.ReviewImpl;

public class RatingServiceImpl implements RatingService, ReviewService {
	
	public List<CriteriaDefinition> getFullCriteriasDefinition(String entityRef, String entityType) {
		
		//Mock\\
		ArrayList<CriteriaDefinition> criteriasDefinition = new ArrayList<CriteriaDefinition>();
		
		//Criteria0
		CriteriaDefinitionImpl criteria0 = new CriteriaDefinitionImpl();
		criteria0.setRef("000");
		criteria0.setLabel("Global Rating");
		
		///Options
		ArrayList<OptionDefinition> optionsList0 = new ArrayList<OptionDefinition>();
		
		OptionDefinitionImpl frontRatingOption01 = new OptionDefinitionImpl();
		frontRatingOption01.setOptionValue("1");frontRatingOption01.setOptionLabel("Really Bad");
		optionsList0.add(frontRatingOption01);
		
		OptionDefinitionImpl frontRatingOption02 = new OptionDefinitionImpl();
		frontRatingOption02.setOptionValue("2");frontRatingOption02.setOptionLabel("Bad");
		optionsList0.add(frontRatingOption02);
		
		OptionDefinitionImpl frontRatingOption03 = new OptionDefinitionImpl();
		frontRatingOption03.setOptionValue("3");frontRatingOption03.setOptionLabel("Normal");
		optionsList0.add(frontRatingOption03);
		
		OptionDefinitionImpl frontRatingOption04 = new OptionDefinitionImpl();
		frontRatingOption04.setOptionValue("4");frontRatingOption04.setOptionLabel("Good");
		optionsList0.add(frontRatingOption04);
		
		OptionDefinitionImpl frontRatingOption05 = new OptionDefinitionImpl();
		frontRatingOption05.setOptionValue("5");frontRatingOption05.setOptionLabel("Really Good");
		optionsList0.add(frontRatingOption05);
		
		criteria0.setOptions(optionsList0);
		
		criteriasDefinition.add(criteria0);
		
		//Criteria1
		CriteriaDefinitionImpl criteria1 = new CriteriaDefinitionImpl();
		criteria1.setRef("001");
		criteria1.setLabel("Product quality");
		criteria1.setInitialValue("1");
		
		///Options
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
		
		criteria1.setOptions(optionsList1);
		
		criteriasDefinition.add(criteria1);
		
		//Criteria2
		CriteriaDefinitionImpl criteria2 = new CriteriaDefinitionImpl();
		criteria2.setRef("002");
		criteria2.setLabel("Service quality");
		
		///Options
		ArrayList<OptionDefinition> optionsList2 = new ArrayList<OptionDefinition>();
		
		OptionDefinitionImpl frontRatingOption21 = new OptionDefinitionImpl();
		frontRatingOption21.setOptionValue("1");frontRatingOption21.setOptionLabel("Really Bad");
		optionsList2.add(frontRatingOption21);
		
		OptionDefinitionImpl frontRatingOption22 = new OptionDefinitionImpl();
		frontRatingOption22.setOptionValue("2");frontRatingOption22.setOptionLabel("Bad");
		optionsList2.add(frontRatingOption22);
		
		OptionDefinitionImpl frontRatingOption23 = new OptionDefinitionImpl();
		frontRatingOption23.setOptionValue("3");frontRatingOption23.setOptionLabel("Normal");
		optionsList2.add(frontRatingOption23);
		
		OptionDefinitionImpl frontRatingOption24 = new OptionDefinitionImpl();
		frontRatingOption24.setOptionValue("4");frontRatingOption24.setOptionLabel("Good");
		optionsList2.add(frontRatingOption24);
		
		OptionDefinitionImpl frontRatingOption25 = new OptionDefinitionImpl();
		frontRatingOption25.setOptionValue("5");frontRatingOption25.setOptionLabel("Really Good");
		optionsList2.add(frontRatingOption25);
		
		OptionDefinitionImpl frontRatingOption26 = new OptionDefinitionImpl();
		frontRatingOption26.setOptionValue("6");frontRatingOption26.setOptionLabel("Awesome");
		optionsList2.add(frontRatingOption26);
		
		criteria2.setOptions(optionsList2);
		
		criteriasDefinition.add(criteria2);
		
		return criteriasDefinition;
		//---\\
	}
	
	public List<CriteriaDefinition> getFormCriteriasDefinition(String entityRef, String entityType) {
		
		//Mok\\
		ArrayList<CriteriaDefinition> criteriasDefinition = new ArrayList<CriteriaDefinition>();
		
		//Criteria1
		CriteriaDefinitionImpl criteria1 = new CriteriaDefinitionImpl();
		criteria1.setRef("001");
		criteria1.setLabel("Product quality");
		criteria1.setInitialValue("2");
		
		///Options
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
		
		criteria1.setOptions(optionsList1);
		
		criteriasDefinition.add(criteria1);
		
		//Criteria2
		CriteriaDefinitionImpl criteria2 = new CriteriaDefinitionImpl();
		criteria2.setRef("002");
		criteria2.setLabel("Service quality");
		criteria2.setInitialValue("5");
		
		///Options
		ArrayList<OptionDefinition> optionsList2 = new ArrayList<OptionDefinition>();
		
		OptionDefinitionImpl frontRatingOption21 = new OptionDefinitionImpl();
		frontRatingOption21.setOptionValue("1");frontRatingOption21.setOptionLabel("Really Bad");
		optionsList2.add(frontRatingOption21);
		
		OptionDefinitionImpl frontRatingOption22 = new OptionDefinitionImpl();
		frontRatingOption22.setOptionValue("2");frontRatingOption22.setOptionLabel("Bad");
		optionsList2.add(frontRatingOption22);
		
		OptionDefinitionImpl frontRatingOption23 = new OptionDefinitionImpl();
		frontRatingOption23.setOptionValue("3");frontRatingOption23.setOptionLabel("Normal");
		optionsList2.add(frontRatingOption23);
		
		OptionDefinitionImpl frontRatingOption24 = new OptionDefinitionImpl();
		frontRatingOption24.setOptionValue("4");frontRatingOption24.setOptionLabel("Good");
		optionsList2.add(frontRatingOption24);
		
		OptionDefinitionImpl frontRatingOption25 = new OptionDefinitionImpl();
		frontRatingOption25.setOptionValue("5");frontRatingOption25.setOptionLabel("Really Good");
		optionsList2.add(frontRatingOption25);
		
		OptionDefinitionImpl frontRatingOption26 = new OptionDefinitionImpl();
		frontRatingOption26.setOptionValue("6");frontRatingOption26.setOptionLabel("Awesome");
		optionsList2.add(frontRatingOption26);
		
		criteria2.setOptions(optionsList2);
		
		criteriasDefinition.add(criteria2);
		
		return criteriasDefinition;
		//---\\
	}

	public Rating getFrontRating(String entityRef, String entityType) {
		
		//Mok\\
		ArrayList<RatingCriteria> ratingCriterias= new ArrayList<RatingCriteria>();
		
		RatingCriteria rating= new RatingCriteria();
		rating.setRef("001");
		rating.setLabel("Product quality");
		rating.setOptionLabel("pq");
		rating.setOptionValue("2");
		
		ratingCriterias.add(rating);
		
		rating =  new RatingCriteria();
		rating.setRef("002");
		rating.setLabel("Service quality");
		rating.setOptionLabel("sq");
		rating.setOptionValue("5");
				
		ratingCriterias.add(rating);
		
		RatingImpl frontRating = new RatingImpl();
		frontRating.setCriterias(ratingCriterias);
		frontRating.setComment("Here is my comment");
		frontRating.setDate(new Date());
		frontRating.setLogin("login");
		frontRating.setTitle("Global Rating Title");
		
		return frontRating;
		//---\\
	}
	
	public boolean registerRating(Rating rating, String entityRef, String entityType, String authorRef, String authorLogin) {
		
		//Mok\\
		return true;
		//---\\
	}
	
	public ResponseReviews getReviews(String entityRef, String entityType) {
		
		//Mok\\
		Date date = new Date();
		
		//Reviews
		RatingCriteria criteria0 = new RatingCriteria();
		criteria0.setRef("000");
		criteria0.setLabel("Global Rating:");
		criteria0.setOptionValue("4");
		criteria0.setOptionLabel("Good");
		
		
		ArrayList<Review> ratingList = new ArrayList<Review>();		
		
		//Rating
		ReviewImpl rating1 = new ReviewImpl();
		rating1.setLogin("Ludovic");
		rating1.setDate(date);
		rating1.setTitle("Good but no more.");
		rating1.setComment("Product not properly finished and the service stands to basics.");
		
		ArrayList<RatingCriteria> criterias1 = new ArrayList<RatingCriteria>();
		RatingCriteria criteria11 = new RatingCriteria();
		criteria11.setRef("001");
		criteria11.setLabel("Product quality:");
		criteria11.setOptionValue("3");
		criteria11.setOptionLabel("Normal");
		criterias1.add(criteria11);
		
		RatingCriteria criteria12 = new RatingCriteria();
		criteria12.setRef("002");
		criteria12.setLabel("Service quality:");
		criteria12.setOptionValue("2");
		criteria12.setOptionLabel("Bad");
		criterias1.add(criteria12);
		
		rating1.setCriterias(criterias1);
		
		ratingList.add(rating1);
		
		
		//rating
		ReviewImpl rating2 = new ReviewImpl();
		rating2.setLogin("Ludo");
		rating2.setDate(date);
		rating2.setTitle("Just perfect!");
		rating2.setComment("Product really correct for its price and service very nice.");

		ArrayList<RatingCriteria> criterias2 = new ArrayList<RatingCriteria>();
		RatingCriteria criteria21 = new RatingCriteria();
		criteria21.setRef("001");
		criteria21.setLabel("Product quality:");
		criteria21.setOptionValue("5");
		criteria21.setOptionLabel("Really Good");
		criterias2.add(criteria21);
		
		RatingCriteria criteria22 = new RatingCriteria();
		criteria22.setRef("002");
		criteria22.setLabel("Service quality:");
		criteria22.setOptionValue("6");
		criteria22.setOptionLabel("Awesome");
		criterias2.add(criteria22);
		
		rating2.setCriterias(criterias2);

		ratingList.add(rating2);
		

		ResponseReviews reviews = new ResponseReviews();
		
		reviews.setGlobalRating(criteria0);
		reviews.setRatings(ratingList);
		
		return reviews;
		//---\\
	}
}
