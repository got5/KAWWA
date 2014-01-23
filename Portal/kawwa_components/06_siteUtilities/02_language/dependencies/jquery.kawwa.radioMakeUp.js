/* KAWWA Radio Makeup
 * Dependencies: jQuery 1.7 or higher
 *
 * Created for KAWWA - Dec 2013
 * 
 * Allows you to style the labels of your radio inputs and to select 
 * a radio button by clicking its label only
 *
 *
 */

(function($) {
	$.fn.radioMakeUp = function(options) {
		
		return this.each(function() {
			var $this = $(this);
			var $label = $this.find('label');
			
			$this.find('input:checked').parents('label').addClass('active');
		
			$label.click(function() {
				$label.removeClass('active');
				$(this).find('input').attr('checked', 'checked');
				$(this).toggleClass('active');
			});

		});
	};
})(jQuery);
