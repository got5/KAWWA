package net.atos.kawwaportal.components.services;

import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentClassTransformWorker;

/**
 * Worker will override the rendering of the Errors Component by adding a kawwa/Errors mixin to the errors component
 * If you don't want this to be applied, you can use the disableKawwaErrors parameter on your component.
**/
public class ErrorsTransformWorker implements ComponentClassTransformWorker {

	public void transform(final ClassTransformation transformation,
			MutableComponentModel model) {
		
		if(model.getComponentClassName().equals(Errors.class.getName()))
			model.addMixinClassName(net.atos.kawwaportal.components.mixins.Errors.class.getName());
	}
}