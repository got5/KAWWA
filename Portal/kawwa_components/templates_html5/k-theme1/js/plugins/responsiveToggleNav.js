// KAWWA2, 2012 - v 1
// This script was written by @GERICCI
// Version: 1.0

// Responsive Navigation Menu
// windowWidth is the same used in the media queries


(function($) {	
	
	$.fn.rTNav = function (options) {
		
		var settings = $.extend({
	      windowWidth: 995
	    }, options);
		
		var theNav = $(this);
		var theMenu = theNav.children('ul');
		
		theMenu.addClass('rwdMenu');
		
		theNav.attr('role', 'menubar').attr("aria-label", "Main Navigation");
		theMenu.attr('role', 'menu').attr('aria-hidden', 'true');
		theMenu.children('li').attr('role', 'menuitem');

		if (theNav.has('h2').length == 0) {
			theMenu.before('<h2 class=\"control\"><a href="#" aria-haspopup="true"><span>Sections:</span></a></h2>');
		} else {
			theNav.children('h2').attr('class', 'control').wrapInner('<span>');
		}
		
		 $('h2.control').bind('click focusin', function(){
				theMenu.slideToggle("slow");
					if(theMenu.hasClass('expanded') && $(window).width() <= settings.windowWidth) {
						theMenu.attr('aria-hidden', 'false');
					} else {
						theMenu.attr('aria-hidden', 'true');
					}
		        });
	}
	
})(jQuery);