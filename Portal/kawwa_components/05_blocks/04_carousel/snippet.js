/* V. 2.1 */
/* As many display problems may be found in IE versions 7 and 6, you may consider a graceful degradation by skipping this plugin for them */
/* Number of items in the carousel are defined by the browser window width */

// IE version sniffing - only for tests on X-UA-Compatible 
var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
var ie7 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 7.0") != -1);
var ie8 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 8.0") != -1);


$(document).ready(function(){
	if (!(ie6 || ie7)) {
 		if(jQuery.fn.jcarousel) {
 			if ($(window).width()>=700) {
	 			jQuery('.k-carousel').jcarousel({
		    		size: '7',
		    		visible: '4'
		    	});
	 		} else if ($(window).width()>=480) {
	 			jQuery('.k-carousel').jcarousel({
		    		size: '7',
		    		visible: '3',
		    		scroll: 3
		    	});
	 		} else {
	 			jQuery('.k-carousel').jcarousel({
		    		size: '7',
		    		visible: '1',
		    		scroll: 1
		    	});
	 		}
 		}
 	}
});
