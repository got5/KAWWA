package net.atos.kawwaportal.components.test.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.grid.ColumnSort;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

public class CelebritySource<T> implements GridDataSource {

	private List<T> celebrities;

	private IDataSource<T> dataSource;

	private List<T> selection;

	@SuppressWarnings("unused")
	private int indexFrom;

	private String filterQuery;

	public CelebritySource(IDataSource<T> ds) {
		this.dataSource = ds;
		filterQuery = "";
		celebrities = ds.getAllObjects();
	}

	/**
	 * Returns the number of rows available in the data source.
	 */
	public int getAvailableRows() {
		return celebrities.size();

	}

	/**
	 * Returns the row value at the provided index. This method will be invoked
	 * in sequential order.
	 */
	public Object getRowValue(int i) {
		return celebrities.get(i);
	}

	/**
	 * Returns the type of value in the rows, or null if not known. This value
	 * is used to create a default {@link *
	 * org.apache.tapestry.beaneditor.BeanModel} when no such model is
	 * explicitly provided.
	 * 
	 * @return the row type, or null
	 */
	@SuppressWarnings("unchecked")
	public Class getRowType() {

		return null;
	}

	/**
	 * Invoked to allow the source to prepare to present values. This gives the
	 * source a chance to pre-fetch data (when appropriate) and informs the
	 * source of the desired sort order. Sorting comes first, then extraction by
	 * range.
	 * 
	 * @param startIndex
	 *            the starting index to be retrieved
	 * @param endIndex
	 *            the ending index to be retrieved
	 * @param sortConstraints
	 *            identify how data is to be sorted
	 */
	@SuppressWarnings("unchecked")
	public void prepare(int startIndex, int endIndex,
			List<SortConstraint> sortConstraints) {
		// startIndex and endIndex is used to sort current page's objects, but
		// here we want to sort all objects
		// getRange(startIndex, endIndex);
		this.indexFrom = startIndex;

		for (SortConstraint constraint : sortConstraints) {
			final ColumnSort sort = constraint.getColumnSort();

			if (sort == ColumnSort.UNSORTED)
				continue;

			final PropertyConduit conduit = constraint.getPropertyModel()
					.getConduit();

			final Comparator valueComparator = new Comparator<Comparable>() {
				public int compare(Comparable o1, Comparable o2) {
					// Simplify comparison, and handle case where both are
					// nulls.

					if (o1 == o2)
						return 0;

					if (o2 == null)
						return 1;

					if (o1 == null)
						return -1;

					return o1.compareTo(o2);
				}
			};

			final Comparator rowComparator = new Comparator() {
				public int compare(Object row1, Object row2) {
					Comparable value1 = (Comparable) conduit.get(row1);
					Comparable value2 = (Comparable) conduit.get(row2);

					return valueComparator.compare(value1, value2);
				}
			};

			final Comparator reverseComparator = new Comparator() {
				public int compare(Object o1, Object o2) {
					int modifier = sort == ColumnSort.ASCENDING ? 1 : -1;

					return modifier * rowComparator.compare(o1, o2);
				}
			};

			// We can freely sort this list because its just a copy.

			Collections.sort(celebrities, reverseComparator);

		}
	}

	public void resetFilter() {
		filterQuery = "";
		celebrities = dataSource.getAllObjects();
	}

	public void setFilter(String query) {
		filterQuery = query;
		celebrities = dataSource.getFilteredRange(filterQuery);

	}

	@SuppressWarnings("unused")
	private void getRange(int indexFrom, int indexTo) {
		selection = new ArrayList<T>();

		for (int i = indexFrom; i <= indexTo; i++) {
			selection.add(celebrities.get(i));
		}

	}

	/**
	 * Finder based on id
	 * 
	 * @param id
	 * @return
	 */
	public Celebrity getOnId(long id) {
		for (T t : celebrities) {
			if (t instanceof Celebrity) {
				Celebrity celebrity = (Celebrity) t;
				if (celebrity.getId() == id)
					return celebrity;
			}
		}
		return null;
	}
    
    
	
	
}
