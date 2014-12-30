(function() {
  requirejs.config({
  	'shim' : {
  		'kawwa/vendor/flexnav': ['jquery']
  	}
  });
  define(['kawwa/vendor/flexnav'], function() {
  	return exports = function(spec) {
          jQuery('#' + spec.id).addClass('k-navbar').flexNav();
      };
  });
})();
