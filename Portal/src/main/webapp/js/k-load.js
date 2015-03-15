//Le Studio, Atos Worldline, 2011
// Groups all functions to be loaded on page loading
// Version 1.2 - 02/2013


// Identifies IE version
	var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
	var ie7 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 7.0") != -1);
	var ie8 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 8.0") != -1);

// Loading all scripts ...

window.onload = function(e) {

    if (ie8) {
		inputFix();
		selectorFix();
		fixStructure();
	}

	if (ie8) {
		fixStructure();
	}

// RESPONSIVE MENU ----------------------------------

	if(jQuery.fn.flexNav) {
		$('.i-navbar').flexNav();
	}


// HIDE URL BAR FOR iPHone ----------------------------------

	if (jQuery(window).width()<=490) {
		window.scrollTo(0, 1);
	}

// SEARCH/AUTOCOMPLETE ----------------------------------
// Added input value for search

function supports_input_placeholder() {
  var i = document.createElement('input');
  return 'placeholder' in i;
}

	if (supports_input_placeholder()) {
		if (jQuery.ui && jQuery.ui.autocomplete) {
			jQuery('input.k-autocomplete').attr("placeholder", "Search a Component...");
			jQuery('input.k-autocomplete').focus(function() {
				createAutoComplete("k-autocomplete", "../autocomplete_tags.txt");
				if(jQuery(this).val() == jQuery(this).attr("placeholder")) {
					jQuery(this).val("");
				}
			}).blur(function() {
				if(jQuery(this).val() == "") {
					jQuery(this).val(jQuery(this).attr("placeholder"));
				}
			}).val(jQuery(this).attr("placeholder"));
		}
	} else {
		jQuery('input.k-autocomplete').attr("value", "Search a Component...");
	}


// VERTICAL ACCORDION MENU ----------------------------------

	if(jQuery.fn.kAccordion) {
		jQuery( ".k-accordion" ).kAccordion();
	}


// COLLAPSIBLE PANELS ----------------------------------

	if(jQuery.fn.collapsiblePanel) {
		jQuery( ".k-panel" ).collapsiblePanel();
	}

// TABS -------------------------------------------

	if (jQuery.fn.kTabs) {
		jQuery('#demo-tab').kTabs();
	}


// MODAL WINDOW ----------------------------------
	opensModal();


 // PRINT -------------------------------

    toPrint();

    // PORTAL DOWNLOAD PAGE ------------------------------

 	if(jQuery('#downloadForm')) {selectAll();}

// PORTAL ONLY***************** -------------------------------

	//if(document.getElementById('components-list')) {libraryControl();}
if(jQuery.fn.libraryControl) {
		jQuery('#components-list').libraryControl();
	}

// PORTAL / COMPO INFO -------------------------------

    if(jQuery('ul').hasClass('component-info')) {
          jQuery('ul.component-info').css('display', 'none');
          jQuery('ul.component-info').css('position', 'absolute');
          jQuery('#main h3:first-of-type img').mouseenter(function() {
                jQuery('ul.component-info').css('display', 'block');
          });
          jQuery('#main h3:first-of-type img').mouseleave(function() {
                jQuery('ul.component-info').css('display', 'none');
          });
          jQuery('#main h3:first-of-type a').focus(function() {
                jQuery('ul.component-info').css('display', 'block');
          });
          jQuery('#main h3:first-of-type a').focusout(function() {
                jQuery('ul.component-info').css('display', 'none');
          });
    }
//----------------------------------------------------
}	//close function

