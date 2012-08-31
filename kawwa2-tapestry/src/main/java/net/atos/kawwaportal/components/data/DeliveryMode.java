package net.atos.kawwaportal.components.data;

import java.util.ArrayList;
import java.util.List;


public class DeliveryMode {
	
	private String ref;
	
	private String label;
	
	private List<GMapSelectLocation> DeliveryLocationsList;
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<GMapSelectLocation> getDeliveryLocationsList() {
		return DeliveryLocationsList;
	}

	public void setDeliveryLocationsList(
			List<GMapSelectLocation> deliveryLocationsList) {
		DeliveryLocationsList = deliveryLocationsList;
	}
	
	public void addDeliveryLocation(GMapSelectLocation deliveryLocation) {
		if(this.DeliveryLocationsList == null) this.DeliveryLocationsList = new ArrayList<GMapSelectLocation>(); 
		this.DeliveryLocationsList.add(deliveryLocation);
	}

}
