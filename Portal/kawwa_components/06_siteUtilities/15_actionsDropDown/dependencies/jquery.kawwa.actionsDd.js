/* KAWWA - May, 2013
 *
 * A dropdown list of actions
 */


(function($) {
	$.fn.actionsDd = function(options) {

 	return this.each(function() {

		var $this = $(this);
		var $zControl = $this.find('button');
		var $list = $this.children('.content');

		var zListId = 'list' + Math.floor(Math.random() * 111);
		var zList = '#' + zListId;
		
		// ARIA
		$list.attr('id', zListId);
		$zControl.attr('aria-owns', zListId);
		$zControl.attr('aria-expanded', false);
		$zControl.attr('aria-haspopup', true);
		$this.find('a').attr('role', 'option');

			// Prepares list
			$(zList).css('display', 'none');

			$(zList).find('a').click(function() {
				$(zList).slideToggle('fast');
			});
			
			// Activates control
			$zControl.click(function(event) {
				event.preventDefault();
				$(zList).slideToggle('fast');
				$(this).toggleClass('active');

				if($(this).hasClass('active')) {
					$(this).attr('aria-expanded', true);
				} else {
					$(this).attr('aria-expanded', false);
				}

			});
		});
	};
})(jQuery);


