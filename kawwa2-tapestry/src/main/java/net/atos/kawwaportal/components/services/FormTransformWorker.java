package net.atos.kawwaportal.components.services;

import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;

/**
 * Worker will override the rendering of the Form Component.
 * Will automatically add the Mandatory mixin if at least one input is required in the current form.
 */
public class FormTransformWorker implements ComponentClassTransformWorker2 {

	
	public void transform(PlasticClass plasticClass, TransformationSupport support,
			MutableComponentModel model) {
		if(model.getComponentClassName().equals(Form.class.getName())){
			model.addMixinClassName(net.atos.kawwaportal.components.mixins.Mandatory.class.getName());
		}
		
	}
}
