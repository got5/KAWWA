package net.atos.kawwaportal.components.services;

import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentClassTransformWorker;

/**
 * Worker will override the rendering of the Form Component.
 */
public class FormTransformWorker implements ComponentClassTransformWorker {

	
	public void transform(final ClassTransformation transformation,
			MutableComponentModel model) {
		
		if(model.getComponentClassName().equals(Form.class.getName())){
			model.addMixinClassName(net.atos.kawwaportal.components.mixins.Mandatory.class.getName());
		}
	}
}
