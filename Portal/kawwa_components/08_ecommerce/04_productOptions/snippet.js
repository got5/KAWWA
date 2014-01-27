$(document).ready(function(){
	if(jQuery.fn.radioMakeUp) {
		if(jQuery(".k-product-options fieldset").hasClass("color")) {
				jQuery( ".k-product-options .color" ).radioMakeUp();
		}
		if(jQuery(".k-product-options fieldset").hasClass("size")) {
				jQuery( ".k-product-options .size" ).radioMakeUp();
		}
	}
});
