requirejs.config({
	"shim" : {
		"kawwa/vendor/jquery.tipsy": ["jquery"]
	}
});
define(["kawwa/vendor/jquery.tipsy"], function() {
	init = function(spec) {
	    jQuery("#" + spec.id).tipsy(spec.options);
	};

  	return exports = init;
});