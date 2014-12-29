requirejs.config({
	"shim" : {
		"kawwa/vendor/jquery.kawwa.actionsDd": ["jquery"]
	}
});
define(["kawwa/vendor/jquery.kawwa.actionsDd"], function() {
	init = function(spec) {
	    jQuery("#" + spec.id).actionsDd();
	};
  	
  	return exports = init;
});