/* Kawwa FlexNav.js 1.0.0
 * Kawwa - Jan 2014
 * 
 * Manages the display and the controls of main navigation
 * without media queries
 * 
 * Thanks to Chris Ferdinandi (http://gomakethings.com/about/) 
 * for the Resize performance tip
 * 
 */


(function($) {
	$.fn.flexNav = function(options) {
		var settings = $.extend({
			'animationSpeed': 'fast'
		},
		options);

	return this.each(function() {
		var $this = $(this);
		var $theNav = $this.parent('nav');
		var $sections = $this.children('li');
		var resizeTimer; // Set resizeTimer to empty so it resets on page load
		
		/* Add ARIA roles */
		$this.find('li').attr('role', 'presentation');
		$this.find('a').attr('role', 'menuitem');
		
		function resizeFunction() {
			if($sections.eq(0).offset().top < $sections.eq($sections.length - 1).offset().top) {
				$this.css('display', 'none');
				$theNav.addClass('adaptive');
			} else {
				$this.css('display', 'block');
				$theNav.removeClass('adaptive');
			}
		};

		// Call once to set.
		resizeFunction();

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
		$this.children('.dropdown').click(function(event) {			
			event.preventDefault();
			//Check dropdown position and fix it
			var zDiff = $(window).width() - $(this).offset().left;
			var zDDWidth = $(this).children('ul').css('width').replace('px', '');
			
			if(zDDWidth > zDiff) {
				$(this).children('ul').css('right', 0);
			}
			
			// Toggles dropdown
			$(this).find('ul').slideToggle(settings.animationSpeed, function(){
				var isExpanded = $(this).css("display") === "block";
				$(this).attr('aria-expanded', isExpanded);
				$(this).attr('aria-hidden', !isExpanded);
			});
		}); 

		
		// Call on resize.
		$(window).resize(function() {
	        clearTimeout(resizeTimer);
	        resizeTimer = setTimeout(resizeFunction, 250);
	    });
		
	 });
	};

})(jQuery);
