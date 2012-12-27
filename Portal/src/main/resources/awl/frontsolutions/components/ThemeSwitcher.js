(function( $ ) {
	$.extend(Tapestry.Initializer, {
	    themeswitcher: function(specs) {
	        $("form#styleswitch select").change(function(){
				var tsform = $("form#styleswitch")[0];
				tsform.submit();
			});
	    }
	});
}) ( jQuery );