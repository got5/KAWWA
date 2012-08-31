package net.atos.kawwaportal.components.data.ratings;


public class OptionDefinitionImpl implements OptionDefinition {

	private String optionValue;
	
	private String optionLabel;

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.test.data.ratings.FrontRatingOption#getOptionValue()
	 */
	public String getOptionValue() {
		return optionValue;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.test.data.ratings.FrontRatingOption#setOptionValue(java.lang.String)
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.test.data.ratings.FrontRatingOption#getOptionLabel()
	 */
	public String getOptionLabel() {
		return optionLabel;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.test.data.ratings.FrontRatingOption#setOptionLabel(java.lang.String)
	 */
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}
	
}
