package net.atos.kawwaportal.components.services;

import net.atos.kawwaportal.components.KawwaConstants;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.services.Environment;

/**
 * Change the rendering of the inputs when they are in error.
 */
public class KawwaValidationDecorator implements ValidationDecorator{

	private final Environment environment; 

    private final MarkupWriter markupWriter; 

    public KawwaValidationDecorator(Environment environment, 
    		MarkupWriter markupWriter) { 
            this.environment = environment; 
            this.markupWriter = markupWriter; 
    } 
    
    private boolean inError(Field field){
    	ValidationTracker tracker = getTracker();
    	return tracker.inError(field);
    }
    private ValidationTracker getTracker() { 
        return environment.peekRequired(ValidationTracker.class); 
    }   

	public void beforeLabel(Field field) {
				
	}

	public void insideLabel(Field field, Element labelElement) {
		
		if(field==null) return;
		
		if(!(field.getClass().getCanonicalName().equalsIgnoreCase("org.apache.tapestry5.corelib.components.Radio") ||
				field.getClass().getCanonicalName().equalsIgnoreCase("org.apache.tapestry5.corelib.components.Checkbox"))) return;
		
		if(inError(field))
				labelElement.addClassName(KawwaConstants.CSS_CLASS_ERROR);
		
	}

	public void afterLabel(Field field) {
			
	}

	public void beforeField(Field field) {
			
	}

	public void insideField(Field field) {

        System.out.println(field.getClass().getCanonicalName()+" "+ field.isRequired());
		if(field==null) return;
		
		Element e = markupWriter.getElement();
		
		if(inError(field)) {
			
			/**
			 * The Checkbox element should not have the CSS_CLASS_ERROR class when it is in error
			 */
			if(field.getClass().getCanonicalName().equalsIgnoreCase("org.apache.tapestry5.corelib.components.Checkbox")) return;
			
			e.addClassName(KawwaConstants.CSS_CLASS_ERROR);
		}

		if(field.isRequired()){
            System.out.println("ajout class " +field.getClass().getCanonicalName());
			e.addClassName(KawwaConstants.CSS_CLASS_REQUIRED);
		}
	}

	public void afterField(Field field) {
		
		/**
		 * The Checkbox element should not display the error messages right after the element.
		 */
		if(field.getClass().getCanonicalName().equalsIgnoreCase("org.apache.tapestry5.corelib.components.Checkbox")) return;
		
		if (inError(field)) { 
             ValidationTracker tracker = getTracker(); 
             markupWriter.element("span", "class", KawwaConstants.CSS_CLASS_CONTEXTUAL_ERROR); 
             markupWriter.write(tracker.getError(field)); 
             markupWriter.end(); 
		} 
	}
}