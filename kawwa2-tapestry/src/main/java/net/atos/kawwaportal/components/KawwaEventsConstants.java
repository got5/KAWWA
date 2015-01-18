package net.atos.kawwaportal.components;

/**
 * Class with all the constants for Events triggered by Kawwa Components
 */
public class KawwaEventsConstants {
	
	/**
	 * Event triggered when the user changed the locale
	 */
	public static final String LANGUAGE_SELECTED = "languageSelected";

    /**
     * Event triggered by the Kawwa2Grid when we change the number of rows that should be displayed
     */
	public static final String GRID_EVENT_ROW_PER_PAGE_SELECT = "rowPerPageSelectionEvent";

    /**
     * Event handled by the Kawwa2Grid when we change the number of rows that should be displayed.
     * When this event is triggered, the component will trigger the GRID_EVENT_ROW_PER_PAGE_SELECT event
     * if everything is ok.
     */
	public static final String SELECT_ROWPERPAGE = "rowPerPageSelect";
	
	/**
	 * Trigger by the kawwa grid component when sort,pagination link is clicked
	 * or change in drop down value
	 */
	public static final String FOCUS_MIXIN_EVENT_ONCLICK = "focusMixinEventOnclick";

	/** Trigger by the kawwa grid when the filter form is submitted */
	public static final String UPDATE_GRID_DATA_SOURCE = "updateGridDataSource";

	/** Trigger by the kawwa grid when the filter form is reset */
	public static final String RESET_GRID_DATA_SOURCE_FILTER = "resetGridDataSourceFilter";

	/** Trigger by the kawwa ActionsDropDown when an item is selected */
	public static final String ACTIONSDROPDOWN_SELECT  = "actionsDropDownSelect";
}
