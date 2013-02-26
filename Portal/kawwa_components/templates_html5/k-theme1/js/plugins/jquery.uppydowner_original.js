(function($)
{
	// This script was written by Steve Fenton
	// http://www.stevefenton.co.uk/Content/Jquery-Uppy-Downer/
	// Feel free to use this jQuery Plugin
	// Version: 1.0.1
    // Contributions by: 
	
	var classCounter = 0;
	
	$.fn.uppydowner = function (settings) {
	
		var upDownStep = 1;
	
		var config = {
			classModifier: "uppydowner",
			upButton: "+",
			upPlaceBefore: false,
			downButton: "&ndash;",
			downPlaceBefore: true,
			step: 1,
			minValue: -10000,
			maxValue: 10000
		};
		
		if (settings) {
			$.extend(config, settings);
		}
		
		return this.each(function () {

			$This = $(this);
			
			upDownStep = parseFloat(config.step);

			var down = "<span id=\"" + config.classModifier + classCounter + "_down\" class=\"" + config.classModifier + "button\">" + config.downButton + "</span>";
			var up = "<span id=\"" + config.classModifier + classCounter + "_up\" class=\"" + config.classModifier + "button\">" + config.upButton + "</span>";
			
			if (config.downPlaceBefore) {
				$This.before(down);
			} else {
				$This.after(down);
			}
			
			if (config.upPlaceBefore) {
				$This.before(up);
			} else {
				$This.after(up);
			}
		
			$("#" + config.classModifier + classCounter + "_down").click( function () {
				var value = parseFloat($This.val());
				if (value > config.minValue) {
					$This.val(value - upDownStep);
				}
				$This[0].focus();
			});
			
			$("#" + config.classModifier + classCounter + "_up").click( function () {
				var value = parseFloat($This.val());
				if (value < config.maxValue) {
					$This.val(value + upDownStep);
				}
				$This[0].focus();
			});
			
		});
		
		return this;
	};
})(jQuery);