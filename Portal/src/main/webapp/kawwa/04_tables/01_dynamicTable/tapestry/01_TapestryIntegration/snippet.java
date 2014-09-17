package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.atos.kawwaportal.components.test.data.Celebrity;
import net.atos.kawwaportal.components.test.data.CelebritySource;
import net.atos.kawwaportal.components.test.data.IDataSource;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.got5.tapestry5.jquery.data.Formats;
import org.got5.tapestry5.jquery.data.Occupation;
import org.got5.tapestry5.jquery.internal.TableInformation;
import org.got5.tapestry5.jquery.pages.DataTables;

public class DataTables
{
	
	@Property
	private Celebrity current;
	
	public List<Celebrity> getAllCelebrities() {
		List<Celebrity> celebrities = new ArrayList<Celebrity>();
		celebrities.add(new Celebrity("Britney", "Spearce", Formats
				.parseDate("12/02/1981"), Occupation.SINGER));
		celebrities.add(new Celebrity("Bill", "Clinton", Formats
				.parseDate("08/19/1946"), Occupation.POLITICIAN));
		celebrities.add(new Celebrity("Placido", "Domingo", Formats
				.parseDate("01/21/1941"), Occupation.SINGER));
		celebrities.add(new Celebrity("Albert", "Einstein", Formats
				.parseDate("03/14/1879"), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Ernest", "Hemingway", Formats
				.parseDate("07/21/1899"), Occupation.WRITER));
		celebrities.add(new Celebrity("Luciano", "Pavarotti", Formats
				.parseDate("10/12/1935"), Occupation.SINGER));
		celebrities.add(new Celebrity("Ronald", "Reagan", Formats
				.parseDate("02/06/1911"), Occupation.POLITICIAN));
		celebrities.add(new Celebrity("Pablo", "Picasso", Formats
				.parseDate("10/25/1881"), Occupation.ARTIST));
		celebrities.add(new Celebrity("Blaise", "Pascal", Formats
				.parseDate("06/19/1623"), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Isaac", "Newton", Formats
				.parseDate("01/04/1643"), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Antonio", "Vivaldi", Formats
				.parseDate("03/04/1678"), Occupation.COMPOSER));
		celebrities.add(new Celebrity("Niccolo", "Paganini", Formats
				.parseDate("10/27/1782"), Occupation.MUSICIAN));
		celebrities.add(new Celebrity("Johannes", "Kepler", Formats
				.parseDate("12/27/1571"), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Franz", "Kafka", Formats
				.parseDate("07/03/1883"), Occupation.WRITER));
		celebrities.add(new Celebrity("George", "Gershwin", Formats
				.parseDate("09/26/1898"), Occupation.COMPOSER));
		return celebrities;
	}

	@Inject
	private ComponentResources resources;

	
	@Inject
	private BeanModelSource beanModelSource;
	
	@SuppressWarnings("unchecked")
	private BeanModel model;
	
	@SuppressWarnings("unchecked")
	public BeanModel getModel() {
		this.model = beanModelSource.createDisplayModel(Celebrity.class,resources.getMessages());
		this.model.get("firstName").sortable(false);
		return model;
	}
	
	public TableInformation getInformation(){
		return new TableInformation() {
			
			public String getTableSummary() {
				// TODO Auto-generated method stub
				return "A summary description of table data";
			}
			
			public String getTableCaption() {
				// TODO Auto-generated method stub
				return "The table title";
			}
			
			public String getTableCSS() {
				// TODO Auto-generated method stub
				return "k-data-table";
			}
		};
	}
	
	public JSONObject getOptions(){
		
		JSONObject json = new JSONObject("bStateSave", "true", "sDom", "TC<\"clear\">Rlfrtip");
		json.put("bJQueryUI", new JSONLiteral("false"));
		return json;
	}
	
	@Property
	private int index;
	
	public String getCss(){
		if(index==0)
			return "first";
		return "other";
	}
	
	
	public class Celebrity {
		private long id;
	    private String firstName;
	    private String lastName;
	    private Date dateOfBirth;
	    private Occupation occupation;
	    private String biography;
	    private boolean birthDateVerified;

		public Celebrity() { 
	    }
	    
	    public Celebrity(String firstName, String lastName, 
						Date dateOfBirth, Occupation occupation) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.dateOfBirth = dateOfBirth;
	        this.occupation = occupation;
	    }
	    
	   
	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public Date getDateOfBirth() {
	        return dateOfBirth;
	    }

	    public void setDateOfBirth(Date dateOfBirth) {
	        this.dateOfBirth = dateOfBirth;
	    }

	    public Occupation getOccupation() {
	        return occupation;
	    }

	    public void setOccupation(Occupation occupation) {
	        this.occupation = occupation;
	    }

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }
	    
	    public String getBiography() {
			return biography;
		}

		public void setBiography(String biography) {
			this.biography = biography;
		}

		public boolean isBirthDateVerified() {
			return birthDateVerified;
		}

		public void setBirthDateVerified(boolean birthDateVerified) {
			this.birthDateVerified = birthDateVerified;
		}
	}
}
