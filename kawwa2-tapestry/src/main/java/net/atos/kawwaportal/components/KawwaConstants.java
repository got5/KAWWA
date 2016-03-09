package net.atos.kawwaportal.components;

public class KawwaConstants {
	
	/**
	 * The path of the image used in the Form Worker
	 */
	public static final String MANDATORY_FIELD_IMAGE = "kawwa2.mandatory_field_image";

    /**
     * Flag to enable/disable in your AppModule cookies (use in the Kawwa2Grid)
     */
	public static final String KAWWA_COOKIE_ENABLE = "kawwa2.cookie-enable";

    /**
     * The root directory containing all images
     */
	public static final String KAWWA_IMG_PATH = "kawwa2.img-path";

    /**
     * Flag set in the AppModule, used to define if we should or not import the Kawwa2 stack.
     */
	public static final String KAWWA_INCLUDE_STACK = "kawwa2.IncludeStack";

	/**
	 * The ID of the current Stack
	 */
	public static final String STACK_ID = "kawwa2.KawwaStack";

    /**
     * This CSS class is added automatically if an input is on error.
     */
	public static final String CSS_CLASS_ERROR = "k-field-error";

    /**
     * This CSS class is added automatically to required inputs.
     */
	public static final String CSS_CLASS_REQUIRED = "k-required";

    /**
     * CSS class by the KawwaError component. When an input is on error, the error message
     * will be displayed in a span HTML element using this CSS class.
     */
	public static final String CSS_CLASS_CONTEXTUAL_ERROR = "k-contextual-error";
	
	/**
	 * Indicates that we want to drop out bootstrap css embedded in tapestry 5.4
	 */
	public static final String SUPPRESS_BOOTSTRAP = "suppress.bootstrap";
	
}
