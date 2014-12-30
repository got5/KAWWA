(function() {
  requirejs.config({
  	'shim' : {
  		'kawwa/vendor/jquery.kawwa.actionsDd': ['jquery']
  	}
  });
  define(['kawwa/vendor/jquery.kawwa.actionsDd'], function() {
  	return exports = function(spec) {
          jQuery('#' + spec.id).actionsDd();
      };
  });
})();
