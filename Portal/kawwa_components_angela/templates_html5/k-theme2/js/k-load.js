// KAWWA2
// Le Studio, Atos Worldline, 2011
// Groups all functions to be loaded on page loading


// Identifies IE version
	var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
	var ie7 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 7.0") != -1);
	var ie8 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 8.0") != -1);

// Loading all scripts ...

window.onload = function(e) {
	
	if (ie6 || ie7) {
		inputFix();
		fixStructure();
	}
	
	if (ie8) {
		fixStructure();
	} 
	
// DROPDOWN MENU ----------------------------------

	if ($("ul,div").hasClass("k-navbar")) {
	    menuManager();
	}

// LANGUAGE CHOICE ----------------------------------		

	if ($("form").hasClass("k-language")) {
		$('form.k-language fieldset.k-radio').buttonset();
	}
	
// FORM FIELD HELP ----------------------------------		

	if ($("a").hasClass("k-field-help")) {
		var theValue = "";
		$("a.k-field-help").each(function(){  
			theValue = $(this).attr("href");
			fieldHelp(theValue, ".k-field-help");
		});	
	}

// INPUT COMMENTS/TOOLTIP ----------------------------------	

	if ($("input").hasClass("k-field-comment")) {
		$('.k-field-comment').tipsy({
			gravity: 's',
			fade: true
		});
	}

// SEARCH/AUTOCOMPLETE ----------------------------------	
// Added input value for search

function supports_input_placeholder() {
  var i = document.createElement('input');
  return 'placeholder' in i;
}

	if (supports_input_placeholder()) {
		if (jQuery('input').hasClass("k-autocomplete")) {
			jQuery('input.k-autocomplete').attr("placeholder", "Search...");
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
		jQuery('input.k-autocomplete').attr("value", "Search...");
	}
	
	
// VERTICAL ACCORDION MENU ----------------------------------		

	if(jQuery('ul').hasClass('k-menu')) {
		jQuery('ul.k-menu').accordion({ active: false, collapsible: true });
		//FOR Accordion With one level, we make the redirect
		if($('a.one-level')){
			$('a.one-level').bind('click', function(){
			window.location.href = $(this).attr("href");
		});
		} 
	}

// ACCORDION  BLOCK ----------------------------------

	if(jQuery('div').hasClass('k-accordion')){
		jQuery( ".k-accordion" ).accordion({
			heightStyle: "content"
		});
	}
		
// COLLAPSIBLE PANELS ----------------------------------	
	
	if(jQuery('div, fieldset').hasClass('k-panel')) {
		jQuery('.k-panel').panel();
	}
	
// TABS -------------------------------------------

	if(jQuery('div').hasClass('k-tabbed-data')) {
		jQuery('.k-tabbed-data').tabs();
	}
	
// DATEPICKER -------------------------------------------

	if (!(ie6 || ie7)) {
		if (jQuery('input').hasClass('k-datepick')) {
			$( "input.k-datepick" ).datepicker({
				showOn: "button",
				buttonImage: "../img/k-theme1/pic_calendar.gif",
				buttonImageOnly: true,
				buttonText: "Click to open/close the calendar"
			});
		}
	}
	
// COLLAPSIBLE LOGIN FORM -------------------------------------------
	
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
	
// DATA SLIDER -------------------------------------------


	if (jQuery('form').hasClass('k-slider')) {
		$('select#valueA, select#valueB').selectToUISlider();
	}

	
	
// DATATABLES -------------------------------------------


	if (jQuery('table').hasClass('k-data-table')) {
		jQuery('table.k-data-table').dataTable({
			"sPaginationType": "full_numbers"
		});
	}

	
// MODAL WINDOW ----------------------------------
	opensModal();
	
	
// TREEVIEW ----------------------------------
	
	if(jQuery('div').hasClass('k-tree')) {
		$('.k-tree').jstree({plugins : ["html_data", "ui", "hotkeys"]});
		$(".jstree-closed > a").attr("aria-expanded", "false");
		$(".jstree-open > a").attr("aria-expanded", "true");
	}
	
// IMAGE CAROUSEL ----------------------------------
 
 	if (!(ie6 || ie7)) {
 		if(jQuery('div').hasClass('k-carousel')) {
 			jQuery('.k-carousel').jcarousel({
	    		size: '7',
	    		visible: '4'
	    	});
 		}
 	}
 	
// PORTAL --------- HOME IMAGE CAROUSEL -------------------------------
	if(document.getElementById("slider")) {
		 jQuery('#slider').jcarousel({
		 	auto: '5',
	        scroll: 1,
	        size: 5,
	        wrap: 'last',
	        initCallback: mycarousel_initCallback,
	        // This tells jCarousel NOT to autobuild prev/next buttons
	        buttonNextHTML: null,
	        buttonPrevHTML: null,itemVisibleInCallback: {
            onAfterAnimation: function(c, o, i, s) {
            --i;
              jQuery('.jcarousel-control a:not(.jcarousel-control a.pause):not(.jcarousel-control a.play)').removeClass('active').addClass('inactive');
              jQuery('.jcarousel-control a:eq('+i+')').removeClass('inactive').addClass('active');
            }
          }

   		 });
	}
	
	
// PRINT -------------------------------
	
	toPrint();
	
		
// OPEN NEW WINDOW LINK -------------------------------
	
	toNewWindow();
 	
 		
//----------------------------------------------------
	
}	//close function
	
