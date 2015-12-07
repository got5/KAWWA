(function() {
  requirejs.config({
  	'shim' : {
  		'tjq/vendor/ui/custom': ['jquery']
  	}
  });

  define(['tjq/vendor/ui/custom'], function() {
  	return exports = function(spec) {
          if(spec.mode == 'false') {
              var element = jQuery('#' + spec.id + ' fieldset.k-radio p select')
           } else {
              jQuery('#' + spec.id + ' fieldset.k-radio').buttonset();
              var element = jQuery('#' + spec.id + ' fieldset.k-radio p input');
           }

              element.bind('change', function(){
                  jQuery.ajax({
                      success: function(response){
                          // Check for complete URL.
                          var redirectURL = response._tapestry.redirectURL;
                          if (/^https?:/.test(redirectURL)) {
                              window.location = redirectURL;
                              return;
                          }

                          window.location.pathname = redirectURL;
                      },
                      url: spec.url,
                      type: 'POST',
                      data: {lang: jQuery(this).val()}
                  });
              });
      };
  });
})();
