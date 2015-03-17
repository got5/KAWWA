// This script was written by ARICCI
// Kawwa2 
// Le Studio, Atos Worldline, 2012
// Version: 1.0

// Switch views of product catalog between thumbs and lists


(function($) {	
	
	$.fn.switchView = function (settings) {
			
		$('.catalog-view li:first-child a').addClass('active');
		
		$('.catalog-view a').click(function(event) {
			event.preventDefault();
			$('.catalog-view a').toggleClass('active');
			$('.k-catalog').toggleClass('list-view');
		});
				
	};
})(jQuery);