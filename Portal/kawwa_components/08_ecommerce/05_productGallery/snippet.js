$(document).ready(function(){
	if(jQuery.fn.jqzoom) {
		jQuery('.jqzoom').jqzoom ({
            zoomType: 'standard',
            lens: true,
            preloadImages: true,
            alwaysOn: false
        });
	}
});