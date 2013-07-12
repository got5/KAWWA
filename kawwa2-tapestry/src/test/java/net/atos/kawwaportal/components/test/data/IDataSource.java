package net.atos.kawwaportal.components.test.data;

import java.util.List;

public interface IDataSource<T> {
	
	/**
	 * Retrieve all the objects that are needed by GridDataSourceImpl
	 * @return
	 */
	public List<T> getAllObjects();

	/**
	 * Retrieve a particular object by Id
	 * @param id
	 * @return
	 */
	public T getObjectById(long id);

	/**
	 * Add Objects to the list
	 * @param c
	 */
	public void addObject(T c);

	/**
	 * Get Filtered list based on the string query
	 * @param filterQuery
	 * @return
	 */
	public List<T> getFilteredRange(String filterQuery);

}
