package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.atos.kawwaportal.components.test.data.Celebrity;
import net.atos.kawwaportal.components.test.data.Occupation;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

public class Kawwa2Grid {
	@Property private Boolean check;
	@Property
	private Celebrity celebrity;

	@Property
	private Celebrity current;
	
	@Property
	private boolean inplace;

	/**
	 * we add a param in url (true/false) to activate or not thekawwa2Grid's inplace parameter
	 * */
	@OnEvent(EventConstants.ACTIVATE)
	public void init(EventContext context){
		if(context.getCount()>0 && context.get(boolean.class, 0)){
			inplace = true;
		}
	}
	
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
		this.model.get("firstName").sortable(false);
		return model;
	}
}
