// KAWWA, 2012
// General fix functions for Internet Explorer v<9
// v. 1.5

// Feb/2013
// 	- Added selectorFix function
//  - Modified function fixStructure


// TEST FOR DOCTYPE -----------------------------------------

function get_doctype() {
    var doctype = 
	    '<!DOCTYPE ' + 
	    document.doctype.name +
	    (document.doctype.publicId?' PUBLIC "' +  document.doctype.publicId + '"':'') +
	    (document.doctype.systemId?' "' + document.doctype.systemId + '"':'') + '>';
	    return doctype;
}



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


//--------------------------------------------------------------
// ADD CLASSES TO NON-SUPPORTED CSS3 SELECTORS -----------------  
// (ie6/7 only)

function selectorFix() {
	jQuery('header[role]').attr('id', 'header');
	jQuery('aside[role]').attr('id', 'sidebar');
	jQuery('footer[role]').attr('id', 'footer');
}


//-----------------------------------------------------------------
// FIXES STRUCTURE BLOCKS ASSIGNING PROPER CLASSES  ---------------
// Adds appropriate classes to main structure blocks (ie6, 7 and 8)
// Problems using :only-child with IE6 ... 
// for IE6, the only element is also the first one :( so, using plan B
	
function fixStructure() {
	if(jQuery('#wrapper').children().length != 3) {
		if(jQuery('#main').is(':first-child') && jQuery('#main').is(':last-child')) {
			jQuery('#main').attr('id', 'main-alone');
		} else if (jQuery('#main').is(':first-child')) {
			jQuery('#main').attr('id', 'main-aside');
		} else if (jQuery('#main').is(':last-child')) {
			jQuery('#main').attr('id', 'main-menu');
		}
	}
}
