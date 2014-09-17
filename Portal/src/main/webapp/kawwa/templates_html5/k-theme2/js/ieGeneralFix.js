// KAWWA2
// Le Studio/Web Platform, Atos Worldline, 2011
// General fix functions for Internet Explorer v<9

// ADD CLASSES TO NON-SUPPORTED SELECTORS ----------------------------------  
// (ie6 only)
function inputFix() {
	$("input:submit").addClass("btSubmit");
	$("input:button").addClass("btSubmit");
	$("input:reset").addClass("btReset");
	$("input:button.reset").addClass("btReset");
	$("input:submit.optional").addClass("btOptional");
	$("input:button.optional").addClass("btOptional");
	$("input:disabled").addClass("btDisabled");
	$('input:radio').css("border", "none");
	$('input:checkbox').css("border", "none");
	$('input:radio').css("background", "none");
	$('input:checkbox').css("background", "none");
}

// FIXES STRUCTURE BLOCKS ASSIGNING PROPER CLASSES  -----------------------------
// Adds appropriate classes to main structure blocks
function fixStructure() {
	if (document.getElementById('sidebar') && document.getElementById('menu')) {
		$('#main').attr("id", "main-all");
	}
	else if (document.getElementById('menu')) {
		$('#main').attr("id", "main-menu");
	}
	else if (document.getElementById('sidebar')) {
		$('#main').attr("id", "main-aside");
	}
}