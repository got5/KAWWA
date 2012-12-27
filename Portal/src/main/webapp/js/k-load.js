// KAWWA2.0 - GraphiKAWWA 1.0
// Le Studio, Atos Worldline, 2011
// Groups all functions to be loaded on page loading


// Identifies IE version
	var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
	var ie7 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 7.0") != -1);
	var ie8 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 8.0") != -1);

// Loading all scripts ...

window.onload = function(e) {
	
    if (ie6) {
        jQuery("#ie-warning").css("display", "block");
    }

	if (ie6 || ie7) {
		inputFix();
		fixStructure();
	}
	
	if (ie8) {
		fixStructure();
	}
	
	
// HIDE URL BAR FOR iPHone ----------------------------------

	if (jQuery(window).width()<=490) {
		window.scrollTo(0, 1);
	}
	
// DROPDOWN MENU ----------------------------------

	if (jQuery("ul").hasClass("k-navbar")) {
	    menuManager();
	}
	
// FORM FIELD HELP ----------------------------------		

	if (jQuery.ui &&  jQuery.ui.dialog) {
		var theValue = "";
		jQuery("a.k-field-help").each(function(){  
			theValue = $(this).attr("href");
			fieldHelp(theValue, ".k-field-help");
		});	
	}

// SEARCH/AUTOCOMPLETE ----------------------------------	
// Added input value for search

function supports_input_placeholder() {
  var i = document.createElement('input');
  return 'placeholder' in i;
}

	if (supports_input_placeholder()) {
		if (jQuery.ui &&  jQuery.ui.autocomplete) {
			jQuery('input.k-autocomplete').attr("placeholder", "Search a Component...");
			jQuery('input.k-autocomplete').focus(function() {
				createAutoComplete("k-autocomplete", "../autocomplete_tags.txt");
				if(jQuery(this).val() == $(this).attr("placeholder")) {
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

	if(jQuery.ui && jQuery.ui.accordion) {
		jQuery('ul.k-menu').accordion({  collapsible: true });
		//FOR Accordion With one level, we make the redirect
		if(jQuery('a.one-level')){
			jQuery('a.one-level').bind('click', function(){
			window.location.href = jQuery(this).attr("href");
		});
		} 
	}

// ACCORDION  BLOCK ----------------------------------

	if(jQuery.ui && jQuery.ui.accordion){
		jQuery( ".k-accordion" ).accordion({
			heightStyle: "content"
		});
	}
		
// COLLAPSIBLE PANELS ----------------------------------	
	
	if(jQuery.ui && jQuery.ui.panel) {
		jQuery('.k-panel').panel();
	}
	
// TABS -------------------------------------------

	if(jQuery.ui && jQuery.ui.tabs) {
		jQuery('.k-tabbed-data').tabs();
	}
	
	
// MODAL WINDOW ----------------------------------
	opensModal();
	
 // PORTAL DOWNLOAD PAGE ------------------------------
 
 	if(jQuery('#downloadForm')) {selectAll();}
 	
 // PRINT -------------------------------
    
    toPrint();
          
//OPEN NEW WINDOW LINK -------------------------------
    
    toNewWindow();
    
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
	
