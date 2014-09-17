$(document).ready(function(){
	if(jQuery.ui && jQuery.ui.panel) {
		jQuery('.k-panel').panel();
	}
	
	function incrementTest() {
		var i = document.createElement('input');
		i.setAttribute('type', 'number');
		return i.type !== 'text';
	}
	var incTest = incrementTest();

	if (jQuery.fn.uppydowner) {
		if(!incTest) {
			jQuery('input[type=number]').attr('class', 'uppydowner');
			jQuery('input.uppydowner').uppydowner();
				
		} else {
			jQuery('input[type=number]').css('width', '3em');
		}
	}
	
	
});
