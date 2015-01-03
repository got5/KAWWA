(function($){
	'use strict';

	function toPrint() {
		if ($('a.bt-print')) {
			$('a.bt-print').click(function(){
				window.print();
				return false;
			});
		}
	}


	$(document).ready(function(){
		toPrint();
	});
})(jQuery);
