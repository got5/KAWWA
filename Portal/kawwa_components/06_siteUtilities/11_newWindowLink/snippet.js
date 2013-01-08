/* You may pass the target name as a variable / defaults to "_blank" */
function openNewWindow(windowName) {
	if (jQuery("a.k-new-window")) {
		jQuery("a.k-new-window").click (function(){
			var zeName = (windowName)? windowName : "_blank";
			var zeTarget = jQuery(this).attr("href");
			window.open(zeTarget, zeName);	
			return false;
		});
	}
}

$(document).ready(function(){
	openNewWindow();
});
