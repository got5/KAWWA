package net.atos.kawwaportal.components.test.pages;

import net.atos.kawwaportal.components.test.data.Celebrity;
import net.atos.kawwaportal.components.test.data.CelebritySource;
import net.atos.kawwaportal.components.test.data.IDataSource;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.got5.tapestry5.jquery.internal.TableInformation;

public class DataTables {
	@SessionState
	private IDataSource dataSource;
	private Celebrity celebrity;
	private CelebritySource celebritySource;
	
	@Property
	private Celebrity current;
	
	public GridDataSource getCelebritySource() {
		if(celebritySource==null)
			celebritySource = new CelebritySource(dataSource);
		return celebritySource;
	}

	
	public Celebrity getCelebrity() {
		return celebrity;
	}
	
	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
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
		
		JSONObject json = new JSONObject("bJQueryUI", "true", "bStateSave", "true", "sDom", "TC<\"clear\">Rlfrtip");
		return json;
	}
	
	
}
