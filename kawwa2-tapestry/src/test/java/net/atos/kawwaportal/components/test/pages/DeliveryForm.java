package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.List;

import net.atos.kawwaportal.components.data.DeliveryMode;
import net.atos.kawwaportal.components.data.GMapSelectLocation;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

public class DeliveryForm {
	
	@InjectComponent
	private Zone resultZone;
	
	@Property
	@SuppressWarnings("unused")
	private String deliveryModeSelected;
	
	@Property
	@SuppressWarnings("unused")
	private DeliveryMode currentDeliveryMode;
	
	@Property
	@SuppressWarnings("unused")
	private String deliveryLocationSelected;
	
	@OnEvent(value = EventConstants.SUCCESS, component = "deliveryForm")
	public Object deliveryFormValidation() {
		
		return resultZone.getBody();
		
	}
	
	public List<DeliveryMode> getDeliveryModesListTest() {
		
		List<DeliveryMode> DeliveryModeList = new ArrayList<DeliveryMode>();
		
		DeliveryMode currentDeliveryMode1 = new DeliveryMode();
		currentDeliveryMode1.setRef("1");
		currentDeliveryMode1.setLabel("France");
		GMapSelectLocation currentDeliveryLocation11 = new GMapSelectLocation();
		GMapSelectLocation currentDeliveryLocation12 = new GMapSelectLocation();
		GMapSelectLocation currentDeliveryLocation13 = new GMapSelectLocation();
		currentDeliveryLocation11.setRef("11");
		currentDeliveryLocation11.setAddress("Atos Z.I. A Rue de la Pointe 59113 SECLIN");
		currentDeliveryLocation12.setRef("12");
		currentDeliveryLocation12.setAddress("Atos 80 quai Voltaire 95877 BEZONS");
		currentDeliveryLocation13.setRef("13");
		currentDeliveryLocation13.setAddress("Atos 13, rue de Bucarest F-75008 PARIS");
		currentDeliveryMode1.addDeliveryLocation(currentDeliveryLocation11);
		currentDeliveryMode1.addDeliveryLocation(currentDeliveryLocation12);
		currentDeliveryMode1.addDeliveryLocation(currentDeliveryLocation13);
		DeliveryModeList.add(currentDeliveryMode1);
		
		DeliveryMode currentDeliveryMode2 = new DeliveryMode();
		currentDeliveryMode2.setRef("2");
		currentDeliveryMode2.setLabel("Allemagne");
		GMapSelectLocation currentDeliveryLocation21 = new GMapSelectLocation();
		GMapSelectLocation currentDeliveryLocation22 = new GMapSelectLocation();
		currentDeliveryLocation21.setRef("21");
		currentDeliveryLocation21.setAddress("Atos GmbH Hahnstraﬂe 25 D-60528 FRANKFURT/MAIN");
		currentDeliveryLocation22.setRef("22");
		currentDeliveryLocation22.setAddress("Atos GmbH Pascalstraﬂe 19 D- 52076 AACHEN");
		currentDeliveryMode2.addDeliveryLocation(currentDeliveryLocation21);
		currentDeliveryMode2.addDeliveryLocation(currentDeliveryLocation22);
		DeliveryModeList.add(currentDeliveryMode2);
		
		DeliveryMode currentDeliveryMode3 = new DeliveryMode();
		currentDeliveryMode3.setRef("3");
		currentDeliveryMode3.setLabel("Pays-Bas");
		GMapSelectLocation currentDeliveryLocation31 = new GMapSelectLocation();
		currentDeliveryLocation31.setRef("31");
		currentDeliveryLocation31.setAddress("Atos Worldline Ligusterbaan 18 2908 LW Capelle a/d Ijssel");
		currentDeliveryMode3.addDeliveryLocation(currentDeliveryLocation31);
		DeliveryModeList.add(currentDeliveryMode3);
		
		DeliveryMode currentDeliveryMode4 = new DeliveryMode();
		currentDeliveryMode4.setRef("4");
		currentDeliveryMode4.setLabel("No Locations Mode");
		//Fonctionne avec ou sans instanciation de la liste d'adresses. Cependant le mode ne doit pas Ítre mis en premier sans instanciation de liste.
		currentDeliveryMode4.setDeliveryLocationsList(new ArrayList<GMapSelectLocation>());
		DeliveryModeList.add(currentDeliveryMode4);
		
		return DeliveryModeList;
	}
}