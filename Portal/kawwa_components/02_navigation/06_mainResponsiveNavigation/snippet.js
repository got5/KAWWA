/* Internet Explorer sniffing */
// Identifies IE version - only for tests on X-UA-Compatible 
var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
var ie7 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 7.0") != -1);
var ie8 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 8.0") != -1);


/* Responsive Main Navigation call */

$(document).ready(function(){
	if(jQuery.fn.flexNav) {
		if (!ie6 || !ie7 || !ie8) {
			jQuery('.k-navbar').flexNav(); 
		}
	}
});
