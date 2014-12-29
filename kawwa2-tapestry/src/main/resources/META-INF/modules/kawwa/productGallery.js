requirejs.config({
	"shim" : {
	    "kawwa/vendor/jquery-migrate": ["jquery"],
		"kawwa/vendor/productgallery/jquery.jqzoom-core": ["kawwa/vendor/jquery-migrate"]
	}
});
define(["kawwa/vendor/productgallery/jquery.jqzoom-core"], function() {
	init = function(spec) {
	    jQuery('#' + spec.id).jqzoom(spec.params);
	};
  	
  	return exports = init;
});