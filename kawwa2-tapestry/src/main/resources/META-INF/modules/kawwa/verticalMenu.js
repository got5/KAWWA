define(["jquery"], function() {
	init = function(spec) {
	    if(jQuery('a.one-level')){
				jQuery('a.one-level').bind('click', function(){
					window.location.href = jQuery(this).attr("href");
				});
		}
	};
  	
  	return exports = init;
});