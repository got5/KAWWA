package net.atos.kawwaportal.components.data.ratings;

import java.util.ArrayList;

public interface CriteriaDefinition {

	/**
	 * @return the ref
	 */
	public abstract String getRef();

	/**
	 * @return the label
	 */
	public abstract String getLabel();
	
	/**
	 * @return the initialValue
	 */
	public abstract String getInitialValue();

	/**
	 * @return the options
	 */
	public abstract ArrayList<OptionDefinition> getOptions();

}