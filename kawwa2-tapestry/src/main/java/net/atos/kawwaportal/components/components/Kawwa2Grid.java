package net.atos.kawwaportal.components.components;

import java.io.IOException;
import java.util.List;

import net.atos.kawwaportal.components.KawwaConstants;
import net.atos.kawwaportal.components.KawwaEventsConstants;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.MarkupWriterListener;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.corelib.data.GridPagerPosition;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.Cookies;
import org.apache.tapestry5.services.FormSupport;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.internal.TableInformation;

/**
 * 
 * @tapestrydoc
 *
 */
@SupportsInformalParameters
@Events(Kawwa2Grid.GRID_EVENT_ROW_PER_PAGE_SELECT)
public class Kawwa2Grid implements ClientElement {

	public static final String GRID_EVENT_ROW_PER_PAGE_SELECT = "rowPerPageSelectionEvent";

	/**
	 * Defines where the pager (used to navigate within the "pages" of results)
	 * should be displayed: "top", "bottom", "both" or "none".
	 */
	@Parameter(value = "both", defaultPrefix = BindingConstants.LITERAL)
	private GridPagerPosition pagerPosition;
	
	/**
	 * This object is used to create a filter form that can be used to filter
	 * available rows. If null then no filter form is created. When the form will be submitted,
	 * Tapestry will propagate 2 events : KawwaEventsConstants.UPDATE_GRID_DATA_SOURCE and 
	 * KawwaEventsConstants.RESET_GRID_DATA_SOURCE_FILTER. You can create method catching these
	 * events, and filter your data source.
	 */
	@Parameter
	private Object criterium;
	
	/**
	 * Contains the information for which the grid table is to be created.
	 */
	@Parameter(required = true, autoconnect = true)
	private GridDataSource source;

	/**
	 * The number of rows of data displayed on each page. If there are more rows
	 * than will fit, the Grid will divide up the rows into "pages" and
	 * (normally) provide a pager to allow the user to navigate within the
	 * overall result set.
	 */
	@Parameter(value = "10")
	private int defaultRowPerPage;

	/**
	 * Helps to configure look and feel of the table rows.
	 */
	@SuppressWarnings("unused")
	@Parameter
	@Property
	private String tableClass;

	/**
	 * Number of pages in set to be shown in kawwaGridPager
	 */
	@SuppressWarnings("unused")
	@Parameter
	private int pagesPerSet;

	/**
	 * Max age in seconds for the number of row per page cookie
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, value = "literal:"
			+ (24 * 60 * 60 * 7))
	private int maxAge;

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String navigatorOptions;

	/**
	 * This parameter is used to set the value for the Caption element, and the
	 * summary attribute of the HTML Table.
	 */
	@Parameter
	private TableInformation information;

	/**
	 * if true, then the Kawwa2Grid is plainly ajax (pagination and sort)
	 * 
	 * We had to declare a parameter instead of using the kgrid component's
	 * published parameter "inplace" because we had to pass this param to an
	 * inner component (Kawwa2Nav)
	 * 
	 */
	@Parameter
	@Property
	private boolean inPlace;

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String clientId;
	
	 
	/**
	 * Header of the block containing the Filter form
	 */
	@Property	@Parameter(value = "message:filterHeader")
	private String filterHeader;
	
	/**
	 * Value for the submit input of the Filter form
	 */
	@Property	@Parameter(value = "message:submitLabel") 
	private String submitLabel;
	
	/**
	 * Value for the reset input of the Filter form
	 */
	@Property	@Parameter(value = "message:resetLabel")
	private String resetLabel;
	
	@InjectComponent("rowPerPageZone")
	private Zone zone;

	/**
	 * Set up via the traditional or Ajax component event request handler
	 */
	@Environmental
	private ComponentEventResultProcessor componentEventResultProcessor;

	@Inject
	@Symbol(KawwaConstants.KAWWA_COOKIE_ENABLE)
	private boolean cookieEnabled;

	@Inject
	private JavaScriptSupport support;

	@Inject
	private Cookies cookies;

	@Inject
	private ComponentResources resources;

	@Inject
	private Block pager;

	@Inject
	private Request request;

	@Inject
	private Block gridBlock;

	@Inject
	private ComponentResources cr;

	@Component(publishParameters = "rowsPerPage")
	private Kawwa2Nav nav;
	
	@Component(id = "idKawwaGrid", inheritInformalParameters = true, parameters = "inplace=inplace", publishParameters = "model,include,exclude,reorder,row,add,overrides,encoder,empty")
	private Grid kGrid;

	@Persist
	@Property
	private String rowPerPageZone;

	@Property
	private GridDataSource cachedSource;

	@Persist
	private Integer rowsPerPage;

	private int index = 0;

	private int availableRows;

	private String uniqueId;

	static class CachingDataSource implements GridDataSource {

		private final GridDataSource delegate;

		private boolean availableRowsCached;

		private int availableRows;

		CachingDataSource(GridDataSource delegate) {
			this.delegate = delegate;
		}

		public int getAvailableRows() {
			if (!availableRowsCached) {
				availableRows = delegate.getAvailableRows();
				availableRowsCached = true;
			}

			return availableRows;
		}

		public void prepare(int startIndex, int endIndex,
				List<SortConstraint> sortConstraints) {
			delegate.prepare(startIndex, endIndex, sortConstraints);
		}

		public Object getRowValue(int index) {
			return delegate.getRowValue(index);
		}

		@SuppressWarnings("unchecked")
		public Class getRowType() {
			return delegate.getRowType();
		}
	}

	void setupDataSource() {
		this.cachedSource = new CachingDataSource(this.source);
		this.availableRows = cachedSource.getAvailableRows();
	}

	@SetupRender
	public void initSource(MarkupWriter writer) {
		setupDataSource();

		// We add a Listener for adding the caption element and the summary
		// attribute to the table
		writer.addListener(new MarkupWriterListener() {

			public void elementDidStart(Element element) {
				if (element.getName().equalsIgnoreCase("table")) {

					if (cr.isBound("information")) {
						element.forceAttributes("summary",
								information.getTableSummary());
						Element caption = element.element("caption");
						caption.text(information.getTableCaption());
					}
				}

			}

			public void elementDidEnd(Element element) {
			}
		});

		if (clientId != null && !"".equals(clientId)) {
			uniqueId = support.allocateClientId(clientId);

		} else {
			uniqueId = support.allocateClientId(resources);

		}

		this.rowPerPageZone = "rowPerPageZone_" + getClientId();

		try {
			if (cookieEnabled) {

				this.rowsPerPage = Integer.parseInt(cookies
						.readCookieValue(resources.getCompleteId()));
			}
		} catch (NumberFormatException nbfEx) {

			// Set the number of default row per page to display.
			// Value would be taken during first time rendering of Kawwagrid.
			if (rowsPerPage == null) {

				rowsPerPage = defaultRowPerPage;
			}
		}
	}

	public Integer getRowsPerPage() {

		return this.rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {

		this.rowsPerPage = rowsPerPage;

		// Writing the value to a cookie
		if (cookieEnabled) {
			cookies.writeCookieValue(resources.getCompleteId(),
					this.rowsPerPage + "", maxAge);
		}

		// trigger event when select drop down value changed

		resources
				.triggerEvent(
						KawwaEventsConstants.GRID_EVENT_ROW_PER_PAGE_SELECT,
						null, null);

	}

	public int getCurrentPage() {
		return kGrid.getCurrentPage();
	}

	/**
	 * Sets current page to the Tapestry 5 Grid.
	 * 
	 * @param int currentPage
	 */
	public void setCurrentPage(int currentPage) {
		kGrid.setCurrentPage(currentPage);
	}

	/**
	 * To control the sorting action of the grid. Because of this, it would now
	 * bring the grid to first page when sorted.
	 * 
	 */
	void onSort() {
		// trigger event for focus mixin
		resources.triggerEvent(KawwaEventsConstants.FOCUS_MIXIN_EVENT_ONCLICK,
				null, null);
	}

	/**
	 * Event handler for Rows Per page
	 * 
	 * @return Object
	 */
	@OnEvent(KawwaEventsConstants.SELECT_ROWPERPAGE)
	public Object getRppChange(Integer nbRows) {
		try {
			this.setRowsPerPage(nbRows);
		} catch (NumberFormatException nfe) {

			/**
			 * This condition will not be reached in normal circumstances.
			 * However, there is still keeping it as a good practise, since
			 * input is from the gui screen.
			 */
			rowsPerPage = defaultRowPerPage;
		}

		if (request.isXHR()) {
			this.setupDataSource();
			return zone.getBody();
		} else
			return resources.getPage().getClass();

	}

	/**
	 * Event handler for inplaceupdate event triggered from nested components
	 * when an Ajax update occurs. The event context will carry the zone, which
	 * is recorded here, to allow the Grid and its sub-components to properly
	 * re-render themselves. Invokes
	 * {@link org.apache.tapestry5.services.ComponentEventResultProcessor#processResultValue(Object)}
	 * passing this (the Grid component) as the content provider for the update.
	 */
	void onInPlaceUpdate(String zone) throws IOException {
		// Ajax render only for events triggered by the kawwaGridPager.
		// Events from Grid and sub-component (GridPager, ...) are handled by
		// the Grid itself.
		if (zone.equalsIgnoreCase(this.zone.getClientId())) {
			componentEventResultProcessor.processResultValue(this);
		}
	}

	public String getClientId() {
		return uniqueId;
	}

	/**
	 * This method returns true if pagerPosition is top or both otherwise false.
	 * 
	 * @return boolean
	 */
	public boolean isTop() {

		return pagerPosition.isMatchTop() ? true : false;
	}

	/**
	 * This method returns true if pagerPosition is bottom or both otherwise
	 * false.
	 * 
	 * @return boolean
	 */
	public boolean isBottom() {

		return pagerPosition.isMatchBottom() ? true : false;
	}

	public Block getPagerBottom() {
		return isBottom() ? pager : null;
	}

	public Block getPagerTop() {
		return isTop() ? pager : null;
	}

	public Block getBlockToDisplay() {
		return gridBlock;
	}

	public String getOptions() {
		if (cr.isBound("navigatorOptions"))
			return navigatorOptions;
		return "5,10,15,20";
	}

	public boolean isEmpty() {
		return this.source == null || this.availableRows == 0;
	}

	// Filter
	
	@Environmental(false)
	private FormSupport formSupport;
	
	/**
	 * Verify if the filter form must be displayed.
	 * 
	 * @return boolean
	 */
	public boolean isFilter() {
		boolean result;
		boolean bound = resources.isBound("criterium")
				&& this.criterium != null;

		if (bound && formSupport == null) {
			result = true;

		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * Citerium is null by default but must be bound.
	 */
	public Object getCriterium() {
		
		return criterium;
	}
	
	public void onSelected(String raisedEvent) {
		/**
		 * Raising events have unique id as well so that there is a different
		 * catcher that catches event for more than 1 usage of this components
		 */

		if ("filter".equalsIgnoreCase(raisedEvent)) {
			resources.triggerEvent(KawwaEventsConstants.UPDATE_GRID_DATA_SOURCE,null, null);
		} else if ("cancel".equalsIgnoreCase(raisedEvent)) {
			resources.triggerEvent(KawwaEventsConstants.RESET_GRID_DATA_SOURCE_FILTER, null,null);
		}
	}

}
