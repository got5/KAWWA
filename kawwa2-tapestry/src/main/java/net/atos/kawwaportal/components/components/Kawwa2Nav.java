package net.atos.kawwaportal.components.components;

import net.atos.kawwaportal.components.KawwaEventsConstants;
import net.atos.kawwaportal.components.KawwaUtils;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.internal.util.CaptureResultCallback;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.FormSupport;


public class Kawwa2Nav {

	/**
	 * Rows per page to display in the grid.
	 */
	@Parameter
	private int rowsPerPage;

	/**
	 * Source for which the grid table is created. It is needed in this
	 * component primarily to make calculations of text of No. of Rows Selected
	 * Out of the Total Rows.
	 */
	@Parameter
	private GridDataSource source;

	/**
	 * Parameter that marks the current page in the grid.
	 */
	@Parameter
	private Integer currentPage;

	/**
	 * Helps to provide optiond to select rows per page.
	 */
	@SuppressWarnings("unused")
	@Parameter
	private String navigatorOptions;

	@Parameter
	@Property
	private String zone;

	@SuppressWarnings("unused")
	@Property
	@Persist("flash")
	private boolean disableRowPerPage;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private Messages messages;
	
	@Environmental(value=false)
	private FormSupport formSupport;
	
	@Inject
	private Block navBlock;
	
	@Component(publishParameters="range")
	private Kawwa2Pager pager;
	
	@SetupRender
	public void initialWork() {
	this.disableRowPerPage = this.source.getAvailableRows() == 0;
	}

	/**
	 * Method to calculate the last index of the grid to help the calculation of
	 * the No. of Records text at the top of the grid
	 * 
	 * @return int
	 */
	public int getLastIndex() {
		int lastIndex = rowsPerPage * currentPage;

		if (lastIndex > source.getAvailableRows()) {
			lastIndex = source.getAvailableRows();

		}
		return lastIndex;
	}

	/**
	 * Method to calculate the first index of the grid to help the calculation
	 * of the No. of Records text at the top of the grid
	 * 
	 * @return int
	 */
	public int getFirstIndex() {

		int totalRecords = source.getAvailableRows();
		int firstIndex = 0;
		if (totalRecords == 0) {

			// Returning the case when There are no records retrieved for Grid.
			return firstIndex;
		}

		firstIndex = rowsPerPage * (currentPage - 1) + 1;
		

		// If Condition for work Bug for Wrong calculation of indexes

		if (firstIndex > getLastIndex()) {

			int rowsPerPage = this.rowsPerPage;
			double totalPages = Math.floor(totalRecords /(double)rowsPerPage);

			int totalRecordsOnLastPage = 0;

			// Finding total records on last page
			for (int i = 0; i < totalPages; i++) {
				totalRecords = totalRecords - rowsPerPage;

			}

			totalRecordsOnLastPage = totalRecords;

			if (totalPages == 1.0) {
				firstIndex = 1;
				return firstIndex;
			} else {
				firstIndex = getLastIndex() - totalRecordsOnLastPage + 1;
			}

		}

		return firstIndex;
	}

	/**
	 * Text to calculate total no. of rows
	 * 
	 * @return int
	 */
	public int getTotalRows() {
		return source.getAvailableRows();
	}

	/**
	 * on value changed from Select component, trigger a event handled by the kawwaGrid container, then return the result (a zone body)
	 * 
	 * @return
	 */
	@OnEvent(value = EventConstants.VALUE_CHANGED)
	public Object triggerNbRowsPerPageChange(String nbRows) 
	{
		CaptureResultCallback<Object> callback = new CaptureResultCallback<Object>();
		resources.triggerEvent(KawwaEventsConstants.SELECT_ROWPERPAGE, new Object[]{Integer.valueOf(nbRows)}, callback);
		return callback.getResult();
	}
	
	public String getResults(){
		return String.format(KawwaUtils.getMessages("pagination-results", messages, null), getFirstIndex(), getLastIndex());
	}
	public String getDisplay(){
		return String.format(KawwaUtils.getMessages("pagination-display", messages, null), getFirstIndex(), getLastIndex());
	}
	public String getPerPage(){
		return String.format(KawwaUtils.getMessages("pagination-perPage", messages, null), getFirstIndex(), getLastIndex());
	}
	
	/**
	 * 
	 * @return true if the component is in a Tapestry Form
	 */
	public Boolean getFormSupport(){
		return formSupport != null;
	}
	
	/**
	 * @return the block containing the Kawwa2Nav component
	 */
	public Object getNavBlock(){
		return navBlock;
	}
}
