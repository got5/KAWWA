requirejs.config({
	"shim" : {
	    "kawwa/vendor/jquery-migrate": ["jquery"],
		"kawwa/vendor/ratings/jquery.rating.pack": ["kawwa/vendor/jquery-migrate"]
	}
});
define(["kawwa/vendor/ratings/jquery.rating.pack"], function() {
	init = function(spec) {
	    jQuery("input[name=\"" + spec.target + "\"]").rating();
	};
  	
  	return exports = init;
});
