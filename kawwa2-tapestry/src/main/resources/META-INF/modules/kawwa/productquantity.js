(function() {
  requirejs.config({
  	'shim' : {
  		'kawwa/vendor/jquery.uppydowner': ['jquery']
  	}
  });
  define(['kawwa/vendor/jquery.uppydowner'], function() {
  	return exports = function(spec) {
          jQuery('input[id=\"' + spec.id + '\"]').uppydowner(spec.params);
      };
  });
})();
