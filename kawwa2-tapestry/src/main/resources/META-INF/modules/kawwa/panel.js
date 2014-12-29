requirejs.config({
	'shim' : {
		'kawwa/vendor/jquery.ui.panel.awl': ['tjq/vendor/ui/jquery-ui.custom']
	}
});
define(['kawwa/vendor/jquery.ui.panel.awl'], function() {
	return exports = function(spec) {
        jQuery('#' + spec.id).panel(spec.options);
    };
});