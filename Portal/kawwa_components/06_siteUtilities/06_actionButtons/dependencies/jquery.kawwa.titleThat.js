/* KAWWA TOOLTIP FOR ICON BUTTONS
 * Dependencies: jQuery 1.7 or higher
 * 
 * January 2014
 * 
 * Displays link labels as a tooltip for icon buttons only
 * Works with Kawwa "k-actions" components
 * 
 */
 
 
 (function($) {
	$.fn.kTitleThat = function(options) {

		return this.each(function() {
			var $this = $(this);
			 
			$this.bind("focus mouseover", function() {
				var zTxt = $(this).text();
				var zLeft = $(this).position();
				$(this).parent("li").after("<span class=\"tooltip\" role=\"tooltip\">" + zTxt + "</span>");
				zLeft = zLeft.left - $("span.tooltip").width()/2 + 10;
				$("span.tooltip").css("left", zLeft);
			});
			
			$this.bind("blur mouseout", function() {
				$("span.tooltip").remove();
			});
		});
                
	};
})(jQuery);
