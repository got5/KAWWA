requirejs.config({
	"shim" : {
		"kawwa/vendor/flexnav": ["jquery"]
	}
});
define(["kawwa/vendor/flexnav"], function() {
	init = function(spec) {
	    jQuery("#" + spec.id).addClass("k-navbar").flexNav();
	};
  	
  	return exports = init;
});