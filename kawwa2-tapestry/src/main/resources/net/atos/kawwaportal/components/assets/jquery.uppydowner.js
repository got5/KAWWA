// This script was written by Steve Fenton
// http://www.stevefenton.co.uk/Content/Jquery-Uppy-Downer/
// Feel free to use this jQuery Plugin
// Version: 1.0.2
   // Contributions by: ARICCI - 04/2012
   // replaced classCounter var by function index to increment id values
   // Added button role and titles
   // Modified each() method / Increment/decrement is tiggered by button class now 
   // Deleted focus changes
// Modifications made by Atos
   // Use of pre-detection for component calls previously
   // Binding "increment" and "decrement" fonctions with the button's id in the "each" function


(function($)
{	
	$.fn.uppydowner = function (settings) {
	
		var upDownStep = 1;
	
		var config = {
			classModifier: "uppydowner",
			upText: "One product more",
			downText: "One product less",
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
		
		base = ($("span[id*=\"" + config.classModifier + "button\"]").length / 2);
		
		this.each(function (index) {

			$This = $(this);
			
			upDownStep = parseFloat(config.step);

			var down = "<span role=\"button\" title=\"" + config.downText+ "\" " +
					"id=\"" + config.classModifier + "button" + (base + index) +"_down\" " +
					"class=\"" + config.classModifier + "button down\">" + config.downButton + "</span>";
			var up = "<span role=\"button\" title=\"" + config.upText + "\" " +
					"id=\"" + config.classModifier + "button" + (base + index) + "_up\" " +
					"class=\"" + config.classModifier + "button up\">" + config.upButton + "</span>";
			
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
			
			$("#" + config.classModifier + "button" + (base + index) + "_down").click( function () {
				var theInput = parseFloat($(this).siblings('input').val());
				if (theInput > config.minValue) {
					$(this).siblings('input').val(theInput - upDownStep).trigger("change");
				}
			});
			
			$("#" + config.classModifier + "button" + (base + index) + "_up").click( function () {
				var theInput = parseFloat($(this).siblings('input').val());
				if (theInput < config.maxValue) {
					$(this).siblings('input').val(theInput + upDownStep).trigger("change");
				}
			});
			
		});
	};
})(jQuery);