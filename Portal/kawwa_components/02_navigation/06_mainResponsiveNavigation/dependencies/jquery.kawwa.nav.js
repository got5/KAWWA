/* Based on FlexNav.js 0.3.2
 * Copyright 2013, Jason Weaver http://jasonweaver.name
 * Released under the WTFPL license
 * http://sam.zoy.org/wtfpl/
 * 
 * Modified for KAWWA - Feb 02 2013
 * 	Added ARIA support / 
 *	Modified variables / 
 *	Replaced resize method for jQuery version 1.7
 * 
 * Modified for Kawwa - May 2013
 * Added fix to correctly position dropdown if parent's 
 * too close to right edge
 * Added missing prevent.default
 * 
 * 'breakpoint' is the breakpoint width defined by the @media
 */


(function($) {
	$.fn.flexNav = function(options) {
		var settings = $.extend({
			'breakpoint': '800',
			'animationSpeed': 'fast'
		},
		options);

		var $this = $(this);
		
		/* Add ARIA roles */
		$this.find('li').attr('role', 'presentation');
		$this.find('a').attr('role', 'menuitem');

		var resizer = function() {
			if ($(window).width() >= settings.breakpoint) {
				$this.show();
			} else {
				$this.hide();
			}
		};

		// Call once to set.
		resizer();

		// Set some classes in the markup	
		$this.find("li").each(function() {
			if ($(this).has("ul").length) {
				$(this).addClass("dropdown");
				$(this).children('a').addClass("hasdropdown").attr('aria-haspopup', 'true');
			}
		});

		// Toggle for nav menu
		$this.siblings('.control').click(function(event) {
			event.preventDefault();
			$this.slideToggle(settings.animationSpeed);
		});

		// Toggle click for sub-menus on touch and or small screens
		$this.find('a.hasdropdown').click(function(event) {
			event.preventDefault();
			//Check dropdown position and fix it
			var zDiff = $(window).width() - $(this).offset().left;
			var zDDWidth = $(this).next('ul').css('width').replace('px', '');
			
			if(zDDWidth > zDiff) {
				$(this).next('ul').css('right', 0);
			}
			
			// Toggles dropdown
			$(this).next('ul').slideToggle(settings.animationSpeed, function(){
				var isExpanded = $(this).css("display") === "block";
				$(this).attr('aria-expanded', isExpanded);
				$(this).attr('aria-hidden', !isExpanded);
			});
		}); 

		
		// Call on resize.
		$(window).resize(resizer);

	};

})(jQuery);
