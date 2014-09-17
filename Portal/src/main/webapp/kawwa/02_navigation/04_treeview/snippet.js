$(document).ready(function(){
	if(jQuery.fn.jstree) {
		jQuery('.k-tree').jstree({plugins : ["html_data", "ui", "hotkeys"]});
		/*if you don't want keyboard navigation, use this line instead : 
		jQuery('.k-tree').jstree();*/
		jQuery(".jstree-closed > a").attr("aria-expanded", "false");
		jQuery(".jstree-open > a").attr("aria-expanded", "true");
	}
});
