(function($){
	$.extend(Tapestry.Initializer, {
		actionsdropdown: function(specs) {
			$("#"+specs.id).actionsDd();
        }
	});
})(jQuery);