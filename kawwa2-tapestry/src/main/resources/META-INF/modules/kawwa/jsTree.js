requirejs.config({
	"shim" : {
	    "kawwa/vendor/jquery-migrate": ["jquery"],
		"kawwa/vendor/jstree/jquery.hotkeys": ["jquery"],
		"kawwa/vendor/jstree/jquery.cookie": ["jquery"],
		"kawwa/vendor/jstree/jquery.jstree": ["kawwa/vendor/jquery-migrate", "kawwa/vendor/jstree/jquery.cookie"]
	}
});
define(["kawwa/vendor/jstree/jquery.jstree"], function() {
	init = function(spec) {
	    jQuery("#" + spec.id).jstree(spec.options);
	};

  	return exports = init;
});