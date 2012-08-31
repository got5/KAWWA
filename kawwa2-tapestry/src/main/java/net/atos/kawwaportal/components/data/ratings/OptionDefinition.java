package net.atos.kawwaportal.components.data.ratings;

/**
 *	<p>
 * 		This Interface provide methodes of an object witch define one of the option the user will be able to choose for one rating.
 *	</p>
 */

public interface OptionDefinition {

	/**
	 * @return the optionValue
	 */
	public abstract String getOptionValue();

	/**
	 * @return the optionLabel
	 */
	public abstract String getOptionLabel();

}