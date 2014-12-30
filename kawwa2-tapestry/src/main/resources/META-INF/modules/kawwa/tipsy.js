(function() {
  requirejs.config({
  	'shim' : {
  	    'kawwa/vendor/jquery.tipsy': ['jquery']
  	}
  });
  define(['kawwa/vendor/jquery.tipsy'], function() {
  	return exports = function(spec) {
          jQuery('#' + spec.id).tipsy(spec.options);
      };
  });
})();
