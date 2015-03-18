(function($){
	'use strict';

	$(document).ready(function(){
		function incrementTest() {
			var i = document.createElement('input');
			i.setAttribute('type', 'number');
			return i.type !== 'text';
		}
		var incTest = incrementTest();

		if ($.fn.uppydowner) {
			if(!incTest) {
				$('input[type=number]').attr('class', 'uppydowner');
				$('input.uppydowner').uppydowner();

			} else {
				$('input[type=number]').css('width', '3em');
			}
		}
	});
})(jQuery);