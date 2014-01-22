/* KAWWA Vertical Accordion Menu
 * Dependencies: jQuery 1.7 or higher
 *
 * Created for KAWWA - Jan 2014
 *
 * The active panel is:
 * - the one with the "active" class attributed to its control, in the markup, or
 * - the one with an "active" section in its sub-menu (also in the markup).
 *
 */

(function($) {
	$.fn.vMenu = function(options) {

		return this.each(function() {
			var $this = $(this);
			var $headers = $this.find("> li > :first-child");
			var $panels = $this.find("> li > ul");

			$panels.css('display', 'none').attr('aria-hidden', 'true').attr('aria-expanded', 'false');

			// ADD ARIA ROLES
			$this.attr('role', 'tablist');
			$this.find('> li').attr('role', 'presentation');
			$headers.attr('role', 'tab').attr('tabindex', '-1');
			$panels.attr('role', 'tabpanel');

			// ADD ARIA CONTROLS
			$panels.each(function(index) {
				var zId = "sub" + index;
				$(this).attr('id', zId);
				$(this).siblings('a').attr('aria-controls', zId).attr('href', zId);
			});
			// SETS ACTIVE PANEL
			$headers.each(function() {
				if($(this).hasClass('active') || $(this).siblings('ul').find('li').hasClass('active')) {
					$(this).addClass('active').attr('aria-selected', 'true').attr('tabindex', '0');
					$(this).siblings('ul').slideDown('fast').attr('aria-hidden', 'false').attr('aria-expanded', 'true');
				}

				// If entry has no panel
				if($(this).siblings('ul').length == 0) {
					$(this).addClass("no-child");
				}
			});
			// CONTROL EVENTS
			$headers.click(function(event) {
				event.preventDefault();
				if(!$(this).hasClass("active")) {
					$panels.slideUp('fast');
					// Close all
					$headers.removeClass('active').attr('aria-selected', 'false').attr('tabindex', '-1');
					$headers.siblings('ul').attr('aria-hidden', 'true').attr('aria-expanded', 'false');
					// Open clicked
					$(this).siblings('ul').slideToggle('fast').attr('aria-hidden', 'false').attr('aria-expanded', 'true');
					$(this).addClass('active').attr('aria-selected', 'true').attr('tabindex', '0');
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
