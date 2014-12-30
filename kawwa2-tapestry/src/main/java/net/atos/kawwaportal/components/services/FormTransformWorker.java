package net.atos.kawwaportal.components.services;

import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;

/**
 * Worker will override the rendering of the Form Component.
 */
public class FormTransformWorker implements ComponentClassTransformWorker2 {

	
	public void transform(PlasticClass plasticClass, TransformationSupport support,
			MutableComponentModel model) {
		System.out.println(model.getComponentClassName());
		if(model.getComponentClassName().equals(Form.class.getName())){
			model.addMixinClassName(net.atos.kawwaportal.components.mixins.Mandatory.class.getName());
		}
		
	}
}
