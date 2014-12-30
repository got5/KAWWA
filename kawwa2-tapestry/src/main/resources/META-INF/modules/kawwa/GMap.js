(function() {
  requirejs.config({
  	'shim' : {
  		'kawwa/vendor/gmap3': ['jquery', 'async!http://maps.googleapis.com/maps/api/js?sensor=false']
  	}
  });
  define(['kawwa/vendor/gmap3'], function() {
  	return exports = function(spec) {
          jQuery('#' + spec.id).gmap3(spec.params);
      };
  });
})();
