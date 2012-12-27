package net.atos.kawwaportal.components.test.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;

public class Test {
/*
	@Property
    @Inject 
    @Path("context:css/k-structure.css")  
    private org.apache.tapestry5.Asset stylesKstructure;     
    
    @Property
    @Inject 
    @Path("context:css/k-theme1.css")  
    private org.apache.tapestry5.Asset stylesKtheme1;  
    
    @Property
    @Inject 
    @Path("context:css/iehacks1.css")  
    private org.apache.tapestry5.Asset iehacks1;  	
    
	
    @Property
    @Inject 
    @Path("context:css/article_es.css")  
    private org.apache.tapestry5.Asset stylesArticle;  
    */
    
    @Persist
	@Property
	private Date fechaFin;
    
    
	@Log
	public JSONObject getParams(){ 
    		JSONObject jsonObject = new JSONObject("nextText", "Next Month");
    		//jsonObject.put("showOn", "button");
    		jsonObject.put("firstDay", 1);
    		jsonObject.put("showOn", "focus");
    		
    	/*	jsonObject.put("buttonImage", assetSourceCalendar.getClasspathAsset("context:img/k-theme1/pic_calendar.gif").toClientURL());				
    		jsonObject.put("buttonImageOnly", true);				
    		jsonObject.put("buttonText", "Click to open/close the calendar");				
    		jsonObject.put("dateFormat", "dd/mm/yyyy");
    		jsonObject.put("changeYear", true);
    		jsonObject.put("changeMonth", true);
    		jsonObject.put("selectOtherMonths", true);
    		
    		jsonObject.put("monthNames", "['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Augosto', 'Septiembrer', 'Octubre', 'Noviembrer', 'Diciembre']");
    		*/
    		return jsonObject;
    }	

}
