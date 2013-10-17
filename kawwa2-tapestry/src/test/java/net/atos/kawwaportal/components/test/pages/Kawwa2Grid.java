package net.atos.kawwaportal.components.test.pages;

import net.atos.kawwaportal.components.KawwaEventsConstants;
import net.atos.kawwaportal.components.test.data.Celebrity;
import net.atos.kawwaportal.components.test.data.CelebrityCriterium;
import net.atos.kawwaportal.components.test.data.CelebritySource;
import net.atos.kawwaportal.components.test.data.MockDataSource;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.got5.tapestry5.jquery.internal.TableInformation;

public class Kawwa2Grid {
	@Property private Boolean check;
	@Property
	private Celebrity celebrity;

	@Property
	private Celebrity current;
	
	@Property
	private boolean inplace;

    @Property
    private Integer rows;
	@Persist
	@Property
	private CelebrityCriterium critere;
	/**
	 * is the source populated or empty
	 * */
	@ActivationRequestParameter 
	private boolean empty;
	
	@SessionState
	private MockDataSource dataSource;
	
	@Persist
	@Property
	private CelebritySource gridDataSourceToDisplay;
	
	/**
	 * we add a param in url (true/false) to activate or not thekawwa2Grid's inplace parameter
	 * */
	@OnEvent(EventConstants.ACTIVATE)
	public void init(EventContext context){
		if(context.getCount()>0 && context.get(boolean.class, 0)){
			inplace = true;
		}
	}
	
	@SetupRender
	public void setupRender(){   rows = 20;
		if(critere  == null)
			critere = new CelebrityCriterium();
		
		if (gridDataSourceToDisplay == null) {
			gridDataSourceToDisplay = new CelebritySource<MockDataSource>(
					dataSource);
			
		}
		
	}
	
	public GridDataSource getAllCelebrities() {
		return gridDataSourceToDisplay;
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
	
	public TableInformation getInformations(){
		return new TableInformation() {
			
			public String getTableSummary() {
				return "Summary";
				
			}
			
			public String getTableCaption() {
				return "Caption";
			}
			
			public String getTableCSS() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	@OnEvent(value = KawwaEventsConstants.UPDATE_GRID_DATA_SOURCE)
	public void filter(){
		gridDataSourceToDisplay.setFilter(critere.toString());
	}
	
	@OnEvent(value = KawwaEventsConstants.RESET_GRID_DATA_SOURCE_FILTER)
	public void resetFilter(){
		critere = new CelebrityCriterium();
		gridDataSourceToDisplay.setFilter(critere.toString());
	}
}
