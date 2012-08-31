package net.atos.kawwaportal.components.data.ratings;

import java.util.ArrayList;

public class CriteriaDefinitionImpl implements CriteriaDefinition {
	
	private String ref;
	
	private String label;
	
	private String initialValue;
	
	private ArrayList<OptionDefinition> options;

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the initialValue
	 */
	public String getInitialValue() {
		return initialValue;
	}

	/**
	 * @param initialValue the initialValue to set
	 */
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	/**
	 * @return the options
	 */
	public ArrayList<OptionDefinition> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(ArrayList<OptionDefinition> options) {
		this.options = options;
	}
}