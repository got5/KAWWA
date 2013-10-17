package net.atos.kawwaportal.components.test.data;

import java.util.ArrayList;
import java.util.List;

public class MockDataSource implements IDataSource {
	private List<Celebrity> celebrities = new ArrayList<Celebrity>();

	public static String noValuebyClient = "noValue";

	/**
	 * Add Objects to List
	 *
	 */
	public MockDataSource() {
		for (int i = 0; i < 1000; i++) {
			addObject(new Celebrity("Bill"+i, "Clinton"+i, Formats
					.parseDate("08/19/1946"), Occupation.POLITICIAN));
			addObject(new Celebrity("Placido"+i, "Domingo"+i, Formats
					.parseDate("01/21/1941"), Occupation.SINGER));
			addObject(new Celebrity("Albert"+i, "Einstein"+i, Formats
					.parseDate("03/14/1879"), Occupation.SCIENTIST));
			addObject(new Celebrity("Ernest"+i, "Hemingway"+i, Formats
					.parseDate("07/21/1899"), Occupation.WRITER));
			addObject(new Celebrity("Nusrat"+i, "Khan"+i, Formats
					.parseDate("08/21/1899"), Occupation.MUSICIAN));
		}

	}

	public List<Celebrity> getAllObjects() {
		return celebrities;
	}

	public Celebrity getObjectById(long id) {
		for (Celebrity c : celebrities) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}

	public void addObject(Object c) {
		long newId = celebrities.size();
		((Celebrity) c).setId(newId + 1);
		celebrities.add(((Celebrity) c));
	}

	public List<Celebrity> getFilteredRange(String filterQuery) {

		List<Celebrity> filteredcelebrities = new ArrayList<Celebrity>();

		String[] filterQuerySplit = filterQuery.split(",");

		String id = filterQuerySplit[0];
		String firstName = filterQuerySplit[1];
		String lastName = filterQuerySplit[2];
		List<Celebrity> filteredIdcelebrities = new ArrayList<Celebrity>();
		List<Celebrity> filteredFirstNameCelebrities = new ArrayList<Celebrity>();
		List<Celebrity> filteredLastNameCelebrities = new ArrayList<Celebrity>();

		//When no value is entered by the client
		if((noValuebyClient.equals(id))&&(noValuebyClient.equals(firstName))
				&&(noValuebyClient.equals(lastName))){
			
			IDataSource mockDataSource = new MockDataSource();			
			return mockDataSource.getAllObjects();
			 
			
		}
		filteredIdcelebrities = getListFromIdSearch(id);
		filteredFirstNameCelebrities = getListFromFirstNameSearch(firstName,
				filteredIdcelebrities);
		filteredLastNameCelebrities = getListFromLastNameSearch(lastName,
				filteredFirstNameCelebrities);

		filteredcelebrities = (List) ((ArrayList<Celebrity>) filteredLastNameCelebrities)
				.clone();

		// Collections.copy(filteredcelebrities,filteredLastNameCelebrities);	
		return filteredcelebrities;

	}// getFilteredRange ends

	private List<Celebrity> getListFromLastNameSearch(String lastName,
			List<Celebrity> filteredFirstNameCelebrities) {


		List<Celebrity> filteredLastNameCelebrities = 
			(List) ((ArrayList<Celebrity>) filteredFirstNameCelebrities).clone();
		/*
		 *  if Id and/or FirstName search gaveout some results, then use the same
		 list to iterate through and remove the last name objects that are not
		 found in the list that came as a result of Id and/or 
		 FirstName search above i.e.  filteredcelebrities list
		 * 
		 */
		if (filteredFirstNameCelebrities.size() != 0) {
			if (!lastName.equals(noValuebyClient)) {
				for (Celebrity filteredcelebrity : filteredFirstNameCelebrities) {
					if (!filteredcelebrity.getLastName().contains(lastName)) {
						filteredLastNameCelebrities.remove(filteredcelebrity);
					}
				}

			}
		} else {
			for (Celebrity celebrity : celebrities) {
				/*
				 *  if Id and/or FirstName search yielded nothing then adding
				 then conducting a fresh search on lastname and addding 
				 the results to filteredcelebrities list
				 * 
				 */
				if (celebrity.getLastName().contains(lastName)) {
					filteredLastNameCelebrities.add(celebrity);
				}
			}

		}		
		
		return filteredLastNameCelebrities;
	}

	
	private List<Celebrity> getListFromFirstNameSearch(String firstName,
			List<Celebrity> filteredIdCelebrities) {
		
		/*
		 * if Id search gaveout some results, then use the contents of list to
		 iterate through and remove the first name objects that are not found 
		 in the list that came as a result of Id search above i.e. filteredcelebrities list
		 */

		List<Celebrity> filteredFirstNameCelebrities = (List) ((ArrayList<Celebrity>) filteredIdCelebrities)
				.clone();
		if (filteredIdCelebrities.size() != 0) {
			if (!firstName.equals(noValuebyClient)) {
				for (Celebrity filteredcelebrity : filteredIdCelebrities) {
					if (!filteredcelebrity.getFirstName().contains(firstName)) {
						filteredFirstNameCelebrities.remove(filteredcelebrity);
					}
				}
			}
		} else {
			for (Celebrity celebrity : celebrities) {
				/*
				 *  if Id search yielded nothing then adding then conducting
				 a fresh search on firstname and addding the results to
				 filteredcelebrities list
				 * 
				 */
				if (celebrity.getFirstName().contains(firstName)) {
					filteredFirstNameCelebrities.add(celebrity);
				}
			}

		}	

		return filteredFirstNameCelebrities;

	}

	private List<Celebrity> getListFromIdSearch(String id) {
		List<Celebrity> filteredIdcelebrities = new ArrayList<Celebrity>();
		for (Celebrity celebrity : celebrities) {
			// if Id contains some value only then add the contents to fiteredlist
			if (!id.equals(noValuebyClient)
					&& (celebrity.getId() + "").contains(id)) {
				filteredIdcelebrities.add(celebrity);
			}
		}		
		return filteredIdcelebrities;

	}

}// Class Ends

