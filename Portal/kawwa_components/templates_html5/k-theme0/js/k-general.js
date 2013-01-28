// KAWWA2
// Le Studio/Web Platform, Atos Worldline, 2011
// General functions definitions - used all over the site

// ADD CLASSES TO NON-SUPPORTED SELECTORS ----------------------------------  
// (ie6 only)
function inputFix() {
	jQuery("input:submit").addClass("btSubmit");
	jQuery("input:button").addClass("btSubmit");
	jQuery("input:reset").addClass("btReset");
	jQuery("input:button.reset").addClass("btReset");
	jQuery("input:submit.optional").addClass("btOptional");
	jQuery("input:button.optional").addClass("btOptional");
	jQuery("input:disabled").addClass("btDisabled");
	jQuery('input:radio').css("border", "none");
	jQuery('input:checkbox').css("border", "none");
	jQuery('input:radio').css("background", "none");
	jQuery('input:checkbox').css("background", "none");
}

// FIXES STRUCTURE BLOCKS ASSIGNING PROPER CLASSES  -----------------------------
// Adds appropriate classes to main structure blocks (ie6, 7 and 8)
function fixStructure() {       
       if ((document.getElementById('sidebar') || jQuery('aside').attr('role') == 'complementary') 
                     && (document.getElementById('menu') || document.getElementById('secondary'))) {
             jQuery('#main').attr("id", "main-all");
			return false;
       } else if (document.getElementById('menu')  || document.getElementById('secondary')){ 
        	jQuery('#main').attr("id", "main-menu");
			return false;
       } else if (jQuery('aside').attr('role') == 'complementary' || document.getElementById('sidebar'))
            jQuery('#main').attr("id", "main-aside");
			return false;
}



// AUTOCOMPLETE ---------------------------------------------------------

function createAutoComplete(theInput, theSource) {
	var availableTags = theSource;
	jQuery(theInput).autocomplete({
		
		source: function(request, response){
            var ajaxRequest = {
            	url:availableTags,
                success: function(data){
                    response(eval(data));
                },
                type:"POST"
            };
            jQuery.ajax(ajaxRequest);
        }
	});
}

function supports_input_placeholder() {
  var i = document.createElement('input');
  return 'placeholder' in i;
}


// MENUBAR -------------------------------------------

function menuManager(){
    var theMenu = jQuery('ul.k-navbar');
    
    theMenu.attr("aria-label", "Main Navigation");
    theMenu.attr("role", "menubar");
     
    jQuery('.dropdown').attr("role", "menu");
      
    jQuery('.dropdown').children('li').each(function(){
         jQuery(this).attr("role", "presentation");
         jQuery(this).children('a').attr("role", "menuitem");
         jQuery(this).children('a').attr("aria-haspopup", "false");
    });
      
    theMenu.children('li').each(function(){
        var $this = jQuery(this);
        if ($this.parent().not('.dropdown')) {
            $this.attr("role", "presentation");
            var $span = $this.children('a');
            $span.attr("role", "menuitem");
            
            /*if there's a dropdown*/
            if ($span.siblings().is('.dropdown')) {                    
            	$span.attr("aria-haspopup", "true");
                $span.addClass("hasdropdown");
                theMenu.superfish(); 
            }
        }         
    });
}



// DIALOG -------------------------------------------
// Trigger must be full selector

function createDialog2(window, trigger) {
    jQuery(window).dialog({
        minHeight: 350,
        minWidth: 270,
        close: function(e){
            jQuery(trigger).focus()
        },
        autoOpen: false,
        describedBy: "dialogDescription",
        modal: true
    });
    jQuery(trigger).click(function(){
        jQuery(window).dialog("open");
        return false;
    });
}

// All modals
// Trigger is defined by link class and modal is retrieved in link's href

function opensModal() {
	jQuery('a.k-modal-trigger').each(function() {
		var theTarget = jQuery(this).attr("href");
		createDialog(jQuery(this));
		jQuery(this).click(function() {
			createDialog(jQuery(this));
			jQuery(theTarget).dialog("open");
			return false;
		});
	});
}

function createDialog(dlink) {
	var theTarget = dlink.attr("href");
	jQuery(theTarget).dialog({
		minHeight : 350,
		minWidth : 470,
		close : function(e) {
			dlink.focus();
			jQuery(this).dialog('widget').remove();
		},
		autoOpen : false,
		describedBy : "dialogDescription",
		modal : true
	});
}





// FORM FIELD HELP -------------------------------------------
// Trigger must be full selector

function fieldHelp(window, trigger) {
    jQuery(window).dialog({
        minHeight: 450,
        minWidth: 370,
        close: function(e){
            jQuery(trigger).prev("input").focus()
        },
        autoOpen: false
    });
    jQuery(trigger).click(function(){
        $(window).dialog("open");
        return false;
    });
}



// VERTICAL MENU.... 
// Accordion control of menu entries
function menuAccordion(){
	jQuery("ul.k-menu li ul").not('.level2.open').hide();
	jQuery("ul.k-menu strong").click (function(){
		jQuery(this).siblings("ul.level2").slideToggle("slow").siblings("ul.level2:visible").slideUp("slow");
		jQuery(this).siblings("ul.level2").toggleClass("open");
		jQuery(this).toggleClass("active");
    });
}


// PRINT
function toPrint() {
	if (jQuery("a.bt-print")) {
		jQuery("a.bt-print").click (function(){
			window.print();	
			return false;
		});
	}
}


// LINK OPENS IN NEW WINDOW
function toNewWindow(windowName) {
	if (jQuery("a.k-new-window")) {
		jQuery("a.k-new-window").click (function(){
			var zeName = (windowName)? windowName : "_blank";
			var zeTarget = jQuery(this).attr("href");
			window.open(zeTarget, zeName);	
			return false;
		});
	}
}







