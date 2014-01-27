$(document).ready(function(){
	if(jQuery.fn.jqzoom && $(window).width()>=625) {
		jQuery('.jqzoom').jqzoom ({
            zoomType: 'standard',
            lens: true,
            preloadImages: true,
            alwaysOn: false
        });
	} else {
		jQuery('.thumblist').css('display', 'none');
	}
});
