package net.atos.kawwaportal.components.components;

import net.atos.kawwaportal.components.Kawwa2ComponentParameterConstants;
import net.atos.kawwaportal.components.KawwaConstants;
import net.atos.kawwaportal.components.KawwaEventsConstants;
import net.atos.kawwaportal.components.KawwaUtils;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ClientBehaviorSupport;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * 
 * @tapestrydoc
 *
 */
public class Kawwa2Pager {
	/**
	 * The source of the data displayed by the grid (this is used to determine
	 * {@link GridDataSource#getAvailableRows() how many rows are available},
	 * which in turn determines the page count).
	 */
	@Parameter(required = true)
	@Property
	private GridDataSource source;

	/**
	 * The number of rows displayed per page.
	 */
	@Parameter(required = true)
	@Property
	private int rowsPerPage;

	/**
	 * Parameter for mentioning start page, By Default it is 1.
	 */
	@Parameter("1")
	@Property
	private int firstPage;

	/**
	 * The current page number (indexed from 1).
	 */
	@Parameter(required = true)
	@Property
	private int currentPage;
	
	@Parameter(BindingConstants.SYMBOL + ":" + Kawwa2ComponentParameterConstants.KAWWA2GRIDPAGER_PAGE_RANGE)
	private int range;
	
	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_first_off.gif")
	private Asset pctFirstOff;

	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_first.gif")
	private Asset pctFirst;

	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_last_off.gif")
	private Asset pctLastOff;

	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_last.gif")
	private Asset pctLast;

	@Inject
	@Path("${" +KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_prev_off.gif")
	private Asset pctPrevOff;

	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_prev.gif")
	private Asset pctPrev;

	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_next_off.gif")
	private Asset pctNextOff;

	@Inject
	@Path("${" + KawwaConstants.KAWWA_IMG_PATH + "}" + "/pic_next.gif")
	private Asset pctNext;
	
	@Inject
	private ComponentResources resources;

	@Environmental
	private ClientBehaviorSupport clientBehaviorSupport;

	@Environmental
	private JavaScriptSupport jsSupport;
	    
	@Inject
	private Messages kawwaMessages;

	@Property
	private int lastIndex;

	@Property
	private int maxPages;

	/**
     * If not null, and inplace="true", then each link is output as a link to update the specified zone.
     */
	@Parameter
    private String zone;

	@Parameter
    private boolean inPlace;

    /**
	 * Method to write out Pagination code
	 * 
	 * @param writer
	 */
	void beginRender(MarkupWriter writer) {
		// high index in set
		int high;

		// low index in set
		int low;

		int availableRows = source.getAvailableRows();

		// getting max pages in grid
		// like for 100 records, by 10 row per page, maxpages will come to 10.
		maxPages = ((availableRows - 1) / rowsPerPage) + 1;

		// Avoid current page to exceed the maximum number of pages

		if (currentPage > maxPages) {
			currentPage = maxPages;
		}

		// Writing first page link
		writeFirstLink(writer, firstPage, currentPage);

		//Writing previous page link
		writePreviousLink(writer, currentPage - 1, "page");

		lastIndex = 0;

		// displaying first two page link when
		for (int i = 1; i <= 2; i++) {
			writePageLink(writer, i);
		}

		// setting lower index based on range
		low = currentPage - range;

		// setting higher index based on range
		high = currentPage + range;

		// if low -ve means start point is 1st page
		if (low < 1) {
			low = 1;
			high = 2 * range + 1;
		} else {
			// if high is maxPages then need to stop at high while writing the
			// links
			if (high > maxPages) {
				high = maxPages;
				low = high - 2 * range;
			}
		}

		// based on above low - high logic show links
		for (int i = low; i <= high; i++) {
			writePageLink(writer, i);
		}

		// showing last two pages of grid
		for (int i = maxPages - 1; i <= maxPages; i++) {
			writePageLink(writer, i);
		}
		// Writing next page link
		writeNextLink(writer, currentPage + 1, "page");

		// Writing last page link
		writeLastLink(writer, currentPage);
	}

	/**
	 * 
	 * Method to write the first Link
	 * 
	 * @param writer
	 * @param firstIndex
	 * @param pageIndex
	 */
	protected void writeFirstLink(MarkupWriter writer, int firstIndex,
			int pageIndex) {
		if (pageIndex == firstIndex) {
			imageOff(writer, KawwaUtils.getMessages("goto-first-page", kawwaMessages, null),
					pctFirstOff.toClientURL(), KawwaUtils.getMessages("first-page", kawwaMessages, null));
		} else {
			lastIndex = firstIndex;
			imageOn(writer, lastIndex, KawwaUtils.getMessages("goto-first-page", kawwaMessages, null),
					pctFirst.toClientURL(), KawwaUtils.getMessages("first-page", kawwaMessages, null),
					"firstpage");
			writer.end();
		}
		writer.end();
	}

	/**
	 * 
	 * Method to write the last Link
	 * 
	 * @param writer
	 * @param pageIndex
	 */
	protected void writeLastLink(MarkupWriter writer, int pageIndex) {

		// Condition pageIndex>_maxPages added
		if (pageIndex == maxPages || pageIndex > maxPages) {
			imageOff(writer, KawwaUtils.getMessages("goto-last-page", kawwaMessages, null), pctLastOff
					.toClientURL(), KawwaUtils.getMessages("last-page", kawwaMessages, null));
		} else {
			lastIndex = maxPages;
			imageOn(writer, lastIndex, KawwaUtils.getMessages("goto-last-page", kawwaMessages, null),
					pctLast.toClientURL(), KawwaUtils.getMessages("last-page", kawwaMessages, null),
					"lastpage");
			writer.end();
		}
		writer.end();
	}

	/**
	 * Utility method to write Page Link
	 * 
	 * @param writer
	 * @param pageIndex
	 */
	protected void writePageLink(MarkupWriter writer, int pageIndex) {

		// if the current page is less then 1 or getter then max then return
		if (pageIndex < 1 || pageIndex > maxPages) {
			return;
		}

		// current page can't be less then last written page
		if (pageIndex <= lastIndex) {
			return;
		}
		
		if (this.displaySupensionsPoints(pageIndex)) {
			writer.element("strong");
			writer.write(" ... ");
			writer.end();
		}
		
		String separator = (pageIndex<maxPages) ? " | " : " ";
		
		lastIndex = pageIndex;

		// showing current page in strong
		if (pageIndex == currentPage) {
			writer.element("strong", "title", KawwaUtils.getMessages("current-page", kawwaMessages, null));
			writer.write(Integer.toString(pageIndex));
			writer.end();
			writer.write(separator);
			return;
		}
		
		Object[] context = (!isAjax())
							? new Object[] { pageIndex }
							: new Object[] { pageIndex, zone };

		Link link = resources.createEventLink(EventConstants.ACTION, context);


		Element element = writer.element("a", "href", !isAjax() ? link : "#", "title", String.format(KawwaUtils.getMessages("goto-page", kawwaMessages, null), pageIndex), "class", "a" + pageIndex);

		writer.write(Integer.toString(pageIndex));

		writer.end();
		
		writer.write(separator);
		
		if (isAjax())
        {
            String clientId = jsSupport.allocateClientId(resources);

            element.attribute("id", clientId);

            clientBehaviorSupport.linkZone(clientId, zone, link);
        }
	}

	/**
	 * Method to write Previous Link with images with tool tips as
	 * "goto-previous-page" or "goto-previous-block".
	 * 
	 * @param writer
	 * @param pageIndex
	 * @param part
	 */
	protected void writePreviousLink(MarkupWriter writer, int pageIndex,
			String part) {
		if ((pageIndex < 1 || pageIndex > maxPages)
				|| (pageIndex == currentPage)) {
			imageOff(writer, KawwaUtils.getMessages("goto-previous-" + part, kawwaMessages, null),
					pctPrevOff.toClientURL(), KawwaUtils.getMessages("previous-" + part, kawwaMessages, null));
		} else {
			lastIndex = pageIndex;
			imageOn(writer, pageIndex, KawwaUtils.getMessages("goto-previous-" + part, kawwaMessages, null), pctPrev.toClientURL(), 
					KawwaUtils.getMessages("previous-" + part, kawwaMessages, null), "previous" + part);
			writer.end();
		}
		writer.end();
	}

	/**
	 * Method to write "goto-Next-page" or "goto-Next-block" link
	 * 
	 * @param writer
	 * @param pageIndex
	 */
	protected void writeNextLink(MarkupWriter writer, int pageIndex, String part) {
		if ((pageIndex < 1 || pageIndex > maxPages)
				|| (pageIndex == currentPage)) {
			imageOff(writer, KawwaUtils.getMessages("goto-next-" + part, kawwaMessages, null),
					pctNextOff.toClientURL(), KawwaUtils.getMessages("next-" + part, kawwaMessages, null));
		} else {
			lastIndex = pageIndex;
			imageOn(writer, pageIndex, KawwaUtils.getMessages("goto-next-" + part, kawwaMessages, null),
					pctNext.toClientURL(), KawwaUtils.getMessages("next-" + part, kawwaMessages, null),
					"next" + part);
			writer.end();
		}
		writer.end();

	}

	/**
	 * This method writes action link with enable image.
	 * 
	 * @param writer
	 * @param index
	 * @param title
	 * @param img
	 * @param alt
	 * @param id
	 */
	protected void imageOn(MarkupWriter writer, int index, String title,
			String img, String alt, String id) {
		
		Object[] context = (!isAjax())
							? new Object[] { index }
							: new Object[] { index, zone };

		Link link = resources.createEventLink(EventConstants.ACTION, context);

		Element element = writer.element("a", "href", !isAjax() ? link : "#", "class", id, "title", title);
		writer.element("img", "src", img, "alt", alt);
		
		if (isAjax())
        {
            String clientId = jsSupport.allocateClientId(resources);

            element.attribute("id", clientId);

            clientBehaviorSupport.linkZone(clientId, zone, link);
        }
	}

	/**
	 * This method writes link with no action and disabled image.
	 * 
	 * @param writer
	 * @param title
	 * @param img
	 * @param alt
	 */
	protected void imageOff(MarkupWriter writer, String title, String img,
			String alt) {
		writer.element("img",  "src", img, "alt", alt);
	}

	protected boolean displaySupensionsPoints(int pageIndex) {
		return pageIndex != (lastIndex + 1);
	}
	
	/**
	 * 
	 * Event Handler when any of the page links are clicked
	 * 
	 * @param newPage
	 */
	void onAction(int newPage) {
		currentPage = newPage;
		
		// trigger event for focus mixin
		resources.triggerEvent(KawwaEventsConstants.FOCUS_MIXIN_EVENT_ONCLICK,
				null, null);
	}
	
	/**
     * Ajax event handler, passing the zone along.
     */
    boolean onAction(int newPage, String zone)
    {
        onAction(newPage);

        resources.triggerEvent(InternalConstants.GRID_INPLACE_UPDATE, new Object[] { zone }, null);

        return true; // abort event
    }
    
    private boolean isAjax(){
		return inPlace && zone != null;
	}
	
    
}
