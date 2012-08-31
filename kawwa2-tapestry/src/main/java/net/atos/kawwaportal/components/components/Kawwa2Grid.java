package net.atos.kawwaportal.components.components;

import java.util.List;

import net.atos.kawwaportal.components.KawwaConstants;
import net.atos.kawwaportal.components.KawwaEventsConstants;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Component;
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
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Cookies;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@SupportsInformalParameters

//TODO Externalize this event as a constant
@Events("rowPerPageSelectionEvent")
public class Kawwa2Grid implements ClientElement{

	/**
	 * Defines where the pager (used to navigate within the "pages" of results)
	 * should be displayed: "top", "bottom", "both" or "none".
	 */
	@Parameter(value = "both", defaultPrefix = BindingConstants.LITERAL)
	private GridPagerPosition pagerPosition;
	
	/**
	 * Contains the information for which the grid table is to be created.
	 */
	@Parameter(required = true, autoconnect = true)
	private GridDataSource source;
	
	/**
     * The number of rows of data displayed on each page. If there are more rows than will fit, the Grid will divide up
     * the rows into "pages" and (normally) provide a pager to allow the user to navigate within the overall result
     * set.
     */
    @Parameter("10")	
    private int defaultRowPerPage;
    
    @Persist
	private Integer rowsPerPage;
    
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
	
	@SuppressWarnings("unused")
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String navigatorOptions;
	
	public static final String GRID_EVENT_ROW_PER_PAGE_SELECT = "rowPerPageSelectionEvent";
	@Component(id = "idKawwaGrid", inheritInformalParameters = true, publishParameters = "model,include,exclude,reorder,row,add,overrides,encoder,inplace,empty")
	private Grid kGrid;
	
	@Property
	private GridDataSource cachedSource;
	
	@Inject
	private Cookies cookies;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	@Symbol(KawwaConstants.KAWWA_COOKIE_ENABLE)
	private boolean cookieEnabled;
	
	@Inject
	private JavaScriptSupport support;
	
	@Inject
	private Block gridBlock;
	
	@Inject
	private ComponentResources cr;
	
	private String uniqueId;
	
	@Inject
	private Request request;
	
	@InjectComponent("rowPerPageZone")
	private Zone zone;
	
	@Persist
	@Property
	private String rowPerPageZone;
	
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String clientId;
	
	private int index = 0;
	
	private int availableRows;

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
	
	void setupDataSource(){
		this.cachedSource = new CachingDataSource(this.source);
		this.availableRows = cachedSource.getAvailableRows();
	}
	
	@SetupRender
	public void initSource(){
		setupDataSource();
		
		if (clientId != null && !"".equals(clientId)) {
			uniqueId = support.allocateClientId(clientId);
			
		} else {
			uniqueId = support.allocateClientId(resources);
			
		}
		
		this.rowPerPageZone = "rowPerPageZone_"+getClientId();
		
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
			cookies.writeCookieValue(resources.getCompleteId(), this.rowsPerPage + "", maxAge);
		}

		// trigger event when select drop down value changed

		resources.triggerEvent(
				KawwaEventsConstants.GRID_EVENT_ROW_PER_PAGE_SELECT, null, null);

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

		
		if(request.isXHR()){
			this.setupDataSource();
			return zone.getBody();
		} 
		else return resources.getPage().getClass();
		
		
	}

	public String getClientId() {
		return uniqueId;	
	}
	
	@Inject
	private Block pager;
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
	
	public String getOptions(){
		if(cr.isBound("navigatorOptions"))
			return navigatorOptions;
		return "5,10,15,20";
	}
}
