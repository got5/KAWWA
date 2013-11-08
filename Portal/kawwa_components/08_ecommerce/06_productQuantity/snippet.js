/* Feature detection */
function incrementTest() {
	var i = document.createElement('input');
	i.setAttribute('type', 'number');
	return i.type !== 'text';
}
	
$(document).ready(function(){
	
	var incTest = incrementTest();

	if (jQuery.fn.uppydowner) {
		if(!incTest) {
			jQuery('input[type=number]').addClass('uppydowner');
			jQuery('input.uppydowner').uppydowner();
		} else {
			jQuery('input[type=number]').css('width', '3em');
		}
	}

});
