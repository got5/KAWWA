(function($){
	$.extend(Tapestry.Initializer, {
		
		deliveryform: function(specs) {
			
			$("input[name=\"" + specs.modeName + "\"]").change( function () {
				
				var parameters = {};
				parameters["deliveryModeSelectedRef"] = $("input[name=\"" + specs.modeName + "\"]:checked").val();
				
				$("#" + specs.zoneId).tapestryZone("update",{url : specs.link, params : parameters});
				
			});
			
		}
	
	});
})(jQuery);