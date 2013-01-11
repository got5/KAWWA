package net.atos.kawwaportal.components.test.components;

import net.atos.kawwaportal.components.mixins.Tabs;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.MixinClasses;

@Import(library="context:test.js")
public class FormComp {
	
	public static final String GRID_MODEL_COMPONENT_EVENT = "gridModelComponentEvent";

	
	
	
	public void onSelectedFromBtnLoad(){
		System.out.println("######## onSelectedFromBtnLoad!!");
	}
	
	public void onSelectedFromBtnCancel(){
		System.out.println("######## onSelectedFromBtnCancel!!");
	}
	
	public void onSubmit(){
		System.out.println("######## onSubmit!!");
	}
	
	
}
