$(document).ready(function(){
	if(jQuery.fn.collapsiblePanel) {
		jQuery( ".k-panel" ).collapsiblePanel();
	}
	
	/* For ptoduct quantity */
	function incrementTest() {
		var i = document.createElement('input');
		i.setAttribute('type', 'number');
		return i.type !== 'text';
	}
		
	var incTest = incrementTest();
	
	if (jQuery.fn.uppydowner) {
		if(!incTest) {
			jQuery('input[type=number]').addClass('uppydowner');
			jQuery('input.uppydowner').uppydowner();
		}
	}
	
	
});
