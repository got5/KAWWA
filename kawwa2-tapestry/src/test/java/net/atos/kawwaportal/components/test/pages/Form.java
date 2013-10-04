package net.atos.kawwaportal.components.test.pages;

import net.atos.kawwaportal.components.test.data.CardType;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Checkbox;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.upload.services.UploadedFile;

public class Form {
	@Property
	private String value;
	
	@Property
	private String second;

    @Property
    private String third;

	@Property 
	private String test;
	
	@Property 
	private String test2;
	
	@Property
    @Persist
    private CardType type;
	
	@InjectComponent
	private org.apache.tapestry5.corelib.comp
    onents.Form form;
	
	@Property
	private Boolean v1, v2;
	
	@InjectComponent(value="t1")
	private Checkbox c;

    @Property
    private UploadedFile file;

	public CardType getMasterCard() { return CardType.MASTER_CARD; }

    public CardType getVisa() { return CardType.VISA; }

    public CardType getAmex() { return CardType.AMERICAN_EXPRESS; }

    public CardType getDinersClub() { return CardType.DINERS_CLUB; }

    public CardType getDiscover() { return CardType.DISCOVER; }
    
    public void onValidate(){
    	form.recordError(c, "Error CheckBox");	
    }
    
    public Boolean getCheckError(){
    	if(form.getDefaultTracker() == null) return false;
    	
    	return InternalUtils.isNonBlank(form.getDefaultTracker().getError(c));
    }
    
    public String getCheckboxError(){
    	return form.getDefaultTracker().getError(c);
    }
}
