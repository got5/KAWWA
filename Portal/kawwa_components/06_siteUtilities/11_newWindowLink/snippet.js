(function($){
	'use strict';

	/* You may pass the target name as a variable / defaults to "_blank" */
    function openNewWindow(windowName) {
        $('a.k-new-window').click(function(){
            var zeName = (windowName) ? windowName : '_blank';
            var zeTarget = $(this).attr('href');
            window.open(zeTarget, zeName);
            return false;
        });
    }

	$(document).ready(function(){
		openNewWindow();
	});
})(jQuery);
