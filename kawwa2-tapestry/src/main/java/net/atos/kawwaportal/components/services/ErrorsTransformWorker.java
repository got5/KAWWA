package net.atos.kawwaportal.components.services;

import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;

/**
 * Worker will override the rendering of the Errors Component by adding a kawwa/Errors mixin to the errors component
 * If you don't want this to be applied, you can use the disableKawwaErrors parameter on your component.
**/
public class ErrorsTransformWorker implements ComponentClassTransformWorker2 {

	public void transform(PlasticClass plasticClass, TransformationSupport support,
			MutableComponentModel model) {
		
		if(model.getComponentClassName().equals(Errors.class.getName()))
			model.addMixinClassName(net.atos.kawwaportal.components.mixins.Errors.class.getName());
	}
}