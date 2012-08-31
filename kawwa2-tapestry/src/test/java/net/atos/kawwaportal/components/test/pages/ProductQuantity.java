package net.atos.kawwaportal.components.test.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.corelib.components.Zone;

public class ProductQuantity {
	
	
	@Persist("flash")
    private String quantityValues;
	
	private int quantity_1;
	private int quantity_2;
	private int quantity_3;
	
	@Persist("flash")
    private String quantityAjaxValues;
	
	private int quantity_Ajax_1;
	private int quantity_Ajax_2;
	private int quantity_Ajax_3;
	
	@InjectComponent
	private Zone resultZone;
	
	//Functions
	public boolean getResultForm() {
		return (quantityValues != "" );
    }
	
	public String getZoneId() {
		return resultZone.getClientId();
	}
	
	@OnEvent(value = EventConstants.SUCCESS, component = "quantityForm")
	public Object formValidation() {
		quantityValues = quantity_1 + ";" + quantity_2 + ";" + quantity_3;
		return this;
	}
	
	@OnEvent(value = EventConstants.SUCCESS, component = "quantityAjaxForm")
	public Object formAjaxValidation() {
		quantityAjaxValues = quantity_Ajax_1 + ";" + quantity_Ajax_2 + ";" + quantity_Ajax_3;
		return resultZone.getBody();
	}

	//Getters and Seters
	public String getQuantityValues() {
		return quantityValues;
	}

	public void setQuantityValues(String quantityValues) {
		this.quantityValues = quantityValues;
	}

	public int getQuantity_1() {
		return quantity_1;
	}

	public void setQuantity_1(int quantity_1) {
		this.quantity_1 = quantity_1;
	}

	public int getQuantity_2() {
		return quantity_2;
	}

	public void setQuantity_2(int quantity_2) {
		this.quantity_2 = quantity_2;
	}

	public int getQuantity_3() {
		return quantity_3;
	}

	public void setQuantity_3(int quantity_3) {
		this.quantity_3 = quantity_3;
	}

	public String getQuantityAjaxValues() {
		return quantityAjaxValues;
	}

	public void setQuantityAjaxValues(String quantityAjaxValues) {
		this.quantityAjaxValues = quantityAjaxValues;
	}

	public int getQuantity_Ajax_1() {
		return quantity_Ajax_1;
	}

	public void setQuantity_Ajax_1(int quantity_Ajax_1) {
		this.quantity_Ajax_1 = quantity_Ajax_1;
	}

	public int getQuantity_Ajax_2() {
		return quantity_Ajax_2;
	}

	public void setQuantity_Ajax_2(int quantity_Ajax_2) {
		this.quantity_Ajax_2 = quantity_Ajax_2;
	}

	public int getQuantity_Ajax_3() {
		return quantity_Ajax_3;
	}

	public void setQuantity_Ajax_3(int quantity_Ajax_3) {
		this.quantity_Ajax_3 = quantity_Ajax_3;
	}
	
	
}
