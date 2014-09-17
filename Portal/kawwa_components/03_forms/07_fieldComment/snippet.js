$(document).ready(function(){
	if (jQuery.fn.tipsy) {
		jQuery('.k-field-comment').tipsy({
			gravity: 'w',
			fade: true, 
			trigger: 'focus'
		});
	}
});
