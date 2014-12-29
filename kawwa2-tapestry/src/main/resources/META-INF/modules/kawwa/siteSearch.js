define(["jquery"], function() {
	function supports_input_placeholder() {
		  var i = document.createElement('input');
		  return 'placeholder' in i;
	}
	
	init = function(spec) {
	    if(supports_input_placeholder){
        		
        	jQuery("#" + spec.id + ".k-autocomplete").attr("placeholder", spec.placeholder)
        		.focus(function(){
            		if(jQuery(this).val() == spec.placeholder) jQuery(this).attr("value","");
            	})
            	.blur(function(){
            		if(jQuery(this).val() == "") jQuery(this).attr("value", spec.placeholder);
            	}).attr("value", spec.placeholder);
    	} else {
    		jQuery("#" + spec.id + ".k-autocomplete").attr("value", spec.placeholder);
    	}
	};
  	
  	return exports = init;
});