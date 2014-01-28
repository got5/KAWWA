/* KAWWA Accordion Block
 * Dependencies: jQuery 1.7 or higher
 * 
 * Created for KAWWA - Oct 2013
 * 
 * The active panel is:
 * - the one with the "active" class attributed to its control link, in the markup,
 * - the first one, by default (if no "active" class is assigned), or 
 * - the one explicitly passed as argument (zero based).
 * 
 * If you want to activate third panel on page load:
 * jQuery( ".k-accordion" ).kAccordion({'activate': '2'});
 * 
 * If you don't want any panel activated:
 * jQuery( ".k-accordion" ).kAccordion({'activate': 'none'});
 */


(function($) {
	$.fn.kAccordion = function(options) {
		var settings = $.extend({
			'activate': '0'
		},
		options);
	
	return this.each(function() {	
		var $this = $(this);
		var $headers = $this.find(".control a");
		var $panels = $this.find(".content");
		var $zActive;
		
		$panels.css('display', 'none').attr('aria-hidden', 'true').attr('aria-expanded', 'false');

		// ADD ARIA ROLES
		$this.attr('role', 'tablist').attr('aria-multiselectable', 'true');
		$headers.attr('role', 'tab');
		$panels.attr('role', 'tabpanel');
		
		// ADD ARIA CONTROLS
		$panels.each(function(index) {
			var randomNum = Math.floor(Math.random() * 111);
			var zPId = "content" + randomNum + index;
			var zHId = "control" + randomNum + index;
			$(this).attr('id',zPId).attr('aria-labelledby', zHId);
			$(this).prev('.control').children('a').attr({
				'id': zHId,
				'aria-controls': zPId,
				'href': zPId
			});
		});
		
		// SETS ACTIVE PANEL
		$headers.each(function() {
			if($(this).hasClass('active')) {
				$zActive = $(this);
			} else {
				$zActive = $headers.eq(settings.activate);
			}
			
			$zActive.addClass('active').attr('aria-selected', 'true');
			$zActive.parent('.control').next('.content').slideDown('fast').attr('aria-hidden', 'false').attr('aria-expanded', 'true');
			
		});
		
		// CONTROL EVENTS
		$headers.click(function(event) {
			event.preventDefault();
			if(!$(this).hasClass("active")){
                $panels.slideUp('fast');
				// Close all
				$headers.removeClass('active').attr('aria-selected', 'false');
				$headers.siblings('.content').attr('aria-hidden', 'true').attr('aria-expanded', 'false');
				// Open clicked
				$(this).parent('.control').next('.content').slideToggle('fast').attr('aria-hidden', 'false').attr('aria-expanded', 'true');
				$(this).addClass('active').attr('aria-selected', 'true');
            }
		});
		
		// KEYBOARD EVENTS
			$this.keyup(function(event) {
				var $zSiblingParent;

				// go left/prev
				if(event.keyCode == 37 || event.keyCode == 38) {
					$zSiblingParent = $this.find('.active').parent().prev();
					if($zSiblingParent.children('a').length != 0) {//there's a valid tab prev
						$zSiblingParent.children('a').focus().click();
					} else {
						$zSiblingParent.prev().children('a').focus().click();
					}

					// go right/next
				} else if(event.keyCode == 39 || event.keyCode == 40) {
					$zSiblingParent = $this.find('.active').parent().next();
					if($zSiblingParent.children('a').length != 0) {//there's a valid tab next
						$zSiblingParent.children('a').focus().click();
					} else {
						$zSiblingParent.next().children('a').focus().click();
					}
				}
			});
		
	  	});
	};
})(jQuery);
