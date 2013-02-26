(function($) {
	$.fn.extend({
		placeholder : function() {
			return this.each(function() {
				$(this).focus(function() {
					if($(this).val() == $(this).attr("placeholder")) {
						$(this).val("");
					}
				}).blur(function() {
					if($(this).val() == "") {
						$(this).val($(this).attr("placeholder"));
					}
				}).val($(this).attr("placeholder"));
			});
		}
	});
})(jQuery);
