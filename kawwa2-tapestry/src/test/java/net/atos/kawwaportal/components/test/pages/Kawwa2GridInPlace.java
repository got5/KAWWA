package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.atos.kawwaportal.components.test.data.Celebrity;
import net.atos.kawwaportal.components.test.data.Occupation;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.util.EnumSelectModel;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

public class Kawwa2GridInPlace {
	@Property
	private Celebrity celebrity;

	@Property
	private Celebrity current;

	public List<Celebrity> getAllCelebrities() {
		
		List<Celebrity> celebrities = new ArrayList<Celebrity>();
		
		celebrities.add(new Celebrity("Britney", "Spearce", new Date(), Occupation.SINGER));
		celebrities.add(new Celebrity("Bill", "Clinton", new Date(), Occupation.POLITICIAN));
		celebrities.add(new Celebrity("Placido", "Domingo", new Date(), Occupation.SINGER));
		celebrities.add(new Celebrity("Albert", "Einstein", new Date(), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Ernest", "Hemingway", new Date(), Occupation.WRITER));
		celebrities.add(new Celebrity("Luciano", "Pavarotti", new Date(), Occupation.SINGER));
		celebrities.add(new Celebrity("Ronald", "Reagan", new Date(), Occupation.POLITICIAN));
		celebrities.add(new Celebrity("Pablo", "Picasso", new Date(), Occupation.ARTIST));
		celebrities.add(new Celebrity("Blaise", "Pascal", new Date(), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Isaac", "Newton", new Date(), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Antonio", "Vivaldi", new Date(), Occupation.COMPOSER));
		celebrities.add(new Celebrity("Niccolo", "Paganini", new Date(), Occupation.MUSICIAN));
		celebrities.add(new Celebrity("Johannes", "Kepler", new Date(), Occupation.SCIENTIST));
		celebrities.add(new Celebrity("Franz", "Kafka", new Date(), Occupation.WRITER));
		celebrities.add(new Celebrity("George", "Gershwin", new Date(), Occupation.COMPOSER));
		
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
		this.model.include("firstName", "lastName");
		this.model.get("firstName").sortable(false);
		this.model.get("lastName").label("Surname");
		this.model.get("firstName").sortable(false);
		return model;
	}
	
	@Property
	private Long currentIndex;
	
	@InjectComponent
	private Zone gridsZone;

	
	@Inject
	private ComponentResources _componentResources;
	/**
	 * <p>
	 * JSON parameter used to configure InPlaceEditor callback
	 * </p>
	 */
	public JSONObject getOptionsJSON()
	{	
		JSONObject params=new JSONObject();
		currentIndex = current.getId();
		Object [] context = new Object [] {currentIndex};
		String listenerURI = _componentResources.createEventLink("refresh", context).toAbsoluteURI(false);
	    String zoneID = gridsZone.getClientId();
	    params.put("tooltip", "Cliquer pour ï¿½diter");
	    params.put("submit", "<br/><button type='submit' class='validateButton' />");
		params.put("cancel", "<button type='submit' class='cancelButton' />");
		params.put("tooltip", "Cliquer pour Ã©diter");
	    params.put("callback", " var zoneElement = $('#"+zoneID+"');" +
							   " zoneElement.tapestryZone('update',{url : '"+listenerURI+"',params : {id:"+currentIndex+"} });" 
							   );
	return params;
	}
	
	@OnEvent(value = "refresh")
	public Object refresh(Long id) {
			//user = (User)users.get(id.intValue());
			return new MultiZoneUpdate("updateZone", gridsZone.getBody());
		
	}
	
	@OnEvent(component = "inPlaceEditor", value = InPlaceEditor.SAVE_EVENT)
	void actionFromEditor(Long id, String value)
	{
		System.err.println("User #" + id + " changed to '" + value + "'");
	}
	
	public enum GridType {
		OPENING_HOURS_GRID, MIN_AMOUNT_GRID, DAILY_CAPACITY_GRID, SLOT_AND_DELAYS_GRID, DELAYS_SLOT_GRID
	}

	@Persist
	private GridType gridToBeDisplayed;

	@Persist
	@Property	
	private GridType selectParam;
	
	@SetupRender
	public Object DeliverySetupRender() {
		this.gridToBeDisplayed = GridType.OPENING_HOURS_GRID;
		return null;
	}
	
	@OnEvent(value = EventConstants.VALUE_CHANGED, component = "selectParam")
	public Object onValueChanged(GridType choice) {
	
		this.gridToBeDisplayed = choice;
		// Rafraichissement de la grille et de la zone info
		MultiZoneUpdate mzu = new MultiZoneUpdate("gridsZone",
				gridsZone.getBody());//.add("info", info.getBody());

		return mzu;
	}
	
	// Services Tapestry & Middle
	@Inject
	private Messages messages;

	
	public SelectModel getGridType() {
		return new EnumSelectModel(GridType.class, messages);
	}
}
