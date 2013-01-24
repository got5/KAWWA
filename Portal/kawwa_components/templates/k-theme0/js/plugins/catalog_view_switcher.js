// This script was written by ARICCI
// Kawwa2 
// Le Studio, Atos Worldline, 2012
// Version: 1.0

// Switch views of product catalog between thumbs and lists


(function($) {	
	
	$.fn.switchView = function (settings) {
			
		$('.catalog-view li:first-child').addClass('active');
		
		$('.catalog-view li').click(function() {
		  $('.catalog-view li').toggleClass('active');
		  $('.k-catalog').toggleClass('list-view');
		});
				
	};
})(jQuery);