// Le Studio, Atos Worldline, 2011
// Groups all functions to be loaded on page loading
// Version 1.2 - 02/2013

// ----------------------------------------------------------
// A short snippet for detecting versions of IE in JavaScript
// without resorting to user-agent sniffing
// ----------------------------------------------------------
// If you're not in IE (or IE version is less than 5) then:
//     ie === undefined
// If you're in IE (>=5) then you can determine which version:
//     ie === 7; // IE7
// Thus, to detect IE:
//     if (ie) {}
// And to detect the version:
//     ie === 6 // IE6
//     ie > 7 // IE8, IE9 ...
//     ie < 9 // Anything less than IE9
// ----------------------------------------------------------

// UPDATE: Now using Live NodeList idea from @jdalton

var ie = (function(){

    var undef,
        v = 3,
        div = document.createElement('div'),
        all = div.getElementsByTagName('i');
    
    while (
        div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
        all[0]
    );
    
    return v > 4 ? v : undef;
    
}());

// Identifies IE version - only for tests on X-UA-Compatible 
	var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
	var ie7 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 7.0") != -1);
	var ie8 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 8.0") != -1);

// Loading all scripts ...

window.onload = function(e) {

	if (ie < 8) {
		inputFix();
		selectorFix();
		fixStructure();
	}
	
	if (ie === 8) {
		fixStructure();
	}
	
// RESPONSIVE MENU ----------------------------------	
	
	if(jQuery.fn.flexNav) {
		$('.k-navbar').flexNav();
	}
	
	
// HIDE URL BAR FOR iPHone ----------------------------------

	if (jQuery(window).width()<=490) {
		window.scrollTo(0, 1);
	}
	
// MAIN NAVIGATION/DROPDOWN MENU ----------------------------------

	if (jQuery("ul").hasClass("k-navbar")) {
	    menuManager();
	}


// TREEVIEW ----------------------------------
	
	if(jQuery.fn.jstree) {
		jQuery('.k-tree').jstree({plugins : ["html_data", "ui", "hotkeys"]});
		/*if you don't want keyboard navigation, use this line instead : 
		jQuery('.k-tree').jstree();*/
		jQuery(".jstree-closed > a").attr("aria-expanded", "false");
		jQuery(".jstree-open > a").attr("aria-expanded", "true");
	}
	
	
// FORM FIELD HELP ----------------------------------		

	if (jQuery.ui && jQuery.ui.dialog) {
		var theValue = "";
		jQuery("a.k-field-help").each(function(){  
			theValue = jQuery(this).attr("href");
			fieldHelp(theValue, ".k-field-help");
		});	
	}

// FORM FIELD COMMENTS ----------------------------------
	
	if (jQuery.fn.tipsy) {
		jQuery('.k-field-comment').tipsy({
			gravity: 's',
			fade: true
		});
	}
	
// DATEPICKER ----------------------------------
/* With Feature detection */

function dateTest() {
	var i = document.createElement('input');
	i.setAttribute('type', 'date');
	return i.type !== 'text';
}
var dTest = dateTest();

	if (!dTest || ie<8) {
		if (jQuery.ui && jQuery.ui.datepicker) {
			jQuery('input[type=date]').addClass('k-datepick');
			/* Use the commented code below to apply translation initialisation
			 jQuery(function($){
				jQuery.datepicker.regional['fr'] = {
					closeText: 'Fermer',
					prevText: 'Mois précedent',
					nextText: 'Mois suivant',
					currentText: 'Courant',
					monthNames: ['Janvier','Février','Mars','Avril','Mai','Juin',
					'Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
					monthNamesShort: ['Jan','Fév','Mar','Avr','Mai','Jun',
					'Jul','Aoû','Sep','Oct','Nov','Déc'],
					dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
					dayNamesShort: ['Dim','Lun','Mar','Mer','Jeu','Ven','Sam'],
					dayNamesMin: ['Di','Lu','Ma','Me','Je','Ve','Sa'],
					dateFormat: 'dd/mm/yy', firstDay: 1,
					isRTL: false};
				$.datepicker.setDefaults($.datepicker.regional['fr']);
			});*/
			jQuery( "input.k-datepick" ).datepicker({
				showOn: "button",
				buttonImage: "../img/k-theme0/pic_calendar.gif",
				buttonImageOnly: true,
				buttonText: "Click to open/close the calendar"
			});
		}
	} else {
		jQuery('input[type=date]').css('width','9em');
	}

// DATATABLES ----------------------------------	

	if (jQuery.fn.dataTable) {
		jQuery('table.k-data-table').dataTable({
			"sPaginationType": "full_numbers"
		});
	}
	
	
// LANGUAGE SELECTION ----------------------------------	

	if (jQuery.ui && jQuery.ui.buttonset) {
		jQuery('form.k-language fieldset.k-radio').buttonset();
	}
	

// LOGIN FORM -------------------------------------------	
	
	if (jQuery('form.k-login').hasClass('collapsible')) {
		jQuery('form.k-login.collapsible').css("display", "none");
		jQuery('#loginLink').children("a").attr("href", "#");
		jQuery('#loginLink').click(function() {
			jQuery('form.k-login.collapsible').animate({
			    height: 'toggle'
			  }, {
			    duration: 500,
			    specialEasing: {
			      height: 'linear'
			    }
			  });
		});
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
	if(jQuery.ui && jQuery.ui.dialog) {
		opensModal();
	}
	
 	
 // PRINT -------------------------------
    
    toPrint();
          
//OPEN NEW WINDOW LINK -------------------------------
    
    toNewWindow();

	
// IMAGE CAROUSEL ----------------------------------
 
 	if (!(ie<8)) {
 		if(jQuery.fn.jcarousel) {
 			if (jQuery(window).width()>=700) {
	 			jQuery('.k-carousel').jcarousel({
		    		size: '7',
		    		visible: '4'
		    	});
	 		} else if (jQuery(window).width()>=480) {
	 			jQuery('.k-carousel').jcarousel({
		    		size: '7',
		    		visible: '3'
		    	});
	 		} else {
	 			jQuery('.k-carousel').jcarousel({
		    		size: '5',
		    		visible: '2'
		    	});
	 		}
 		}
 	}
 	
	
// INCREMENT/DECREMENT VALUE -------------------------------
	
	function incrementTest() {
		var i = document.createElement('input');
		i.setAttribute('type', 'number');
		return i.type !== 'text';
	}
	var incTest = incrementTest();

	if (jQuery.fn.uppydowner) {
		if(!incTest) {
			jQuery('input[type=number]').addClass('uppydowner');
			jQuery('input.uppydowner').uppydowner();
		} else {
			jQuery('input[type=number]').css('width', '3em');
		}
	}
	
	
// CATALOG VIEW SWITCHER -------------------------------

	if(jQuery.fn.switchView) {jQuery('catalog-view').switchView();}	
	
	
// PRODUCT GALLERY -------------------------------
	
	if(jQuery.fn.jqzoom) {
		jQuery('.jqzoom').jqzoom ({
            zoomType: 'standard',
            lens: true,
            preloadImages: true,
            alwaysOn: false
        });
	}
	

// PRODUCT OPTIONS -------------------------------	

	if (jQuery("div").hasClass("k-product-options")) {
		jQuery('fieldset').buttonset();
	}
	
	
// RATINGS -------------------------------	

	if(jQuery.fn.rating) {
		jQuery('fieldset.k-rating input').rating();
	}
	
//----------------------------------------------------
}	//close function
