// This script was written by ARICCI
// Kawwa2 
// Le Studio, Atos Worldline, 2012
// Version: 1.0

// Switch views of product catalog between thumbs and lists


(function($) {	
	
	$.fn.switchView = function (settings) {
			
		$('.catalog-view li:first-child button').addClass('active');
		
		$('.catalog-view button').click(function(event) {
			event.preventDefault();
			$('.catalog-view button').toggleClass('active');
			$('.k-catalog').toggleClass('list-view');
		});
				
	};
})(jQuery);