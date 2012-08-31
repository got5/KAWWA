(function($){
	$.extend(Tapestry.Initializer, {
		rating: function(specs) {
			$("input[name=\"" + specs.target + "\"]").rating();
        }
	});
})(jQuery);