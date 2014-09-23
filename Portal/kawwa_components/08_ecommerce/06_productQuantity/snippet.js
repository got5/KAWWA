/* Feature detection */
function isNumberInput() {
	var i = document.createElement('input');
	i.setAttribute('type', 'number');
	return i.type !== 'text';
}
	
$(document).ready(function(){
	
	var incTest = isNumberInput();

	if (jQuery.fn.uppydowner) {
		if(!incTest) {
			jQuery('input[type=number]').addClass('uppydowner');
			jQuery('input.uppydowner').uppydowner();
		} else {
			jQuery('input[type=number]').css('width', '3em');
		}
	}

});
