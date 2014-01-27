/* KAWWA Collapsible Panels
 * Dependencies: jQuery 1.7 or higher
 *
 * Created for KAWWA - Dec 2013
 *
 * The active panel is:
 * - the one with the "active" class attributed to its control, in the markup
 *
 */

(function($) {
	$.fn.collapsiblePanel = function(options) {

		return this.each(function() {
			var $this = $(this);
			var $header = $this.find(".control a");
			var $panel = $this.find("> .content");
			
			// ADD ARIA ROLES AND CONTROLS
			var zRandomNum = Math.floor(Math.random() * 111);
			var panelId = 'panel' + zRandomNum;
			var controlId = 'control' + zRandomNum;
			
			$panel.css('display', 'none').attr({
				'aria-hidden': 'true',
				'aria-expanded': 'false',
				'id': panelId
			});
			
			$header.attr({
				'id': controlId,
				'href': panelId,
				'aria-controls': panelId
			});
			$panel.attr('aria-labelledby', controlId);
			
			// SETS ACTIVE PANEL
			if($header.hasClass('active')) {
				$header.attr('aria-selected', 'true');
				$header.parent('.control').siblings('.content').slideDown('fast').attr('aria-hidden', 'false').attr('aria-expanded', 'true');
			}
			
			// CONTROL EVENTS
			$header.click(function(event) {
				event.preventDefault();
				$(this).parent('.control').siblings('.content').slideToggle('fast').attr('aria-hidden', 'false').attr('aria-expanded', 'true');
				$(this).toggleClass('active').attr('aria-selected', 'true');
			});

		});
	};
})(jQuery);
