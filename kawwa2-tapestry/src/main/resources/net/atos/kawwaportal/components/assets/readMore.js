(function($) {
	$.fn.readMore = function(options) {
		return this.each(function() {
			var $this = $(this).hide();
			var $header = $('<a href="#">'+options.header+'</a>').click(function(){
				$this.show();
				$(this).unbind("click", arguments.callee).remove();
				return false;
			});
			$this.before($header);
		});
	};
})(jQuery);