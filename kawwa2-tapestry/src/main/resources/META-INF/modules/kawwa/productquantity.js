requirejs.config({
	"shim" : {
		"kawwa/vendor/jquery.uppydowner": ["jquery"]
	}
});
define(["kawwa/vendor/jquery.uppydowner"], function() {
	init = function(spec) {
	    jQuery("input[id=\"" + spec.id + "\"]").uppydowner(spec.params);
	};
  	
  	return exports = init;
});