(function($){

	   $.extend(Tapestry.Initializer, {
	     initZoom: function(specs){
	            $('#'+specs.id).jqzoom(specs.params);
	        }
	   });

})(jQuery);