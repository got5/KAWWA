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

// DOWNLOAD SELECTION -----------------------------

function selectAll() {
	if (document.getElementById("group-components")) {
	if($("#componentList").val().indexOf(",") != -1){
		var array = $("#componentList").val().split(",");
		
		for(i=0; i<array.length;i++){
			$("#"+array[i]).attr("checked", true);
		}
		
		$("fieldset.k-sub-group").each(function(){
			if($(this).find("ul.k-check input:not(:checked)").length == 0){
				$(this).find("legend label input.category-label").attr("checked", "true");
			}
		});
	}
		
	var totalCats = $('fieldset.k-sub-group legend').length;
	var totalComps = $("ul.k-check input").length + $("li.alone input").length;
	$('#total-components').html(totalComps);
	$('#complete-selection').css("display", "block");
	$('#group-components input').change(function(){
		if ($(this).attr("id") == "select-all") {
			if ($(this).is(':checked')) {
				$('fieldset.k-sub-group input').attr("checked", true);
				$('li.alone input').attr("checked", true);
			} else {
			$('fieldset.k-sub-group input').attr("checked", false);
			$('li.alone input').attr("checked", false);
			}
		}
		if ($(this).hasClass("category-label")) {
			if ($(this).is(':checked')) {
				$(this).parents('fieldset.k-sub-group').find('ul.k-check li input').attr("checked",true);
			} else {
				$(this).parents('fieldset.k-sub-group').find('ul.k-check li input').attr("checked", false);
			}
		}
		countChecks();
		fillComponentList();
		});
	}
	
	confirmationDownload();
}

function confirmationDownload() {
	$("#downloadForm p input[type='submit']").click(function(){
		if($("#downloadForm input[type='checkbox']:checked").length == 0
				&& !confirm("You did not select any component or pack option. \nThe content of the zip file will be limited to the style-sheets and images of the chosen graphical theme")){
			return false;
		}
	});
}
// COUNT SELECTED CHECKBOXES
function countChecks() {
	var n = $("fieldset.k-sub-group li input:checked").length;
	var m = $("li.alone input:checked").length;
	var count = n + m;
	$('#total-selection').html(count);
}

function fillComponentList(){
	var list="";
	$("input[type=checkbox].counter:checked").each(function(){
		list += $(this).attr("id")+",";
	})
	$("#componentList").val(list);
	
	$("#componentList").trigger('change');
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



/** CAROUSEL / SLIDER
 * We use the initCallback callback
 * to assign functionality to the controls
 */
function mycarousel_initCallback(carousel) {
	// Dynamic control
	var totalSlides = writeControl(carousel.clip,carousel.options.auto);
	
	
    jQuery('.jcarousel-control a').bind('click', function() {
		carousel.scroll(jQuery.jcarousel.intval(jQuery(this).text()));
        return false;
    });
    
    // Disable autoscrolling if the user clicks the prev or next button.
    carousel.buttonNext.bind('click', function() {
        carousel.startAuto(0);
    });

    carousel.buttonPrev.bind('click', function() {
        carousel.startAuto(0);
    });

    // Pause autoscrolling if the user moves with the cursor over the clip.
    carousel.clip.hover(function() {
        carousel.stopAuto();
    }, function() {
        carousel.startAuto();
    });
	/*
    // Pause with control panel
	jQuery('.jcarousel-control a.pause').bind('click', function() {
		carousel.stopAuto();
		jQuery(this).hide();
		jQuery('.jcarousel-control a.play').show();
	});
	jQuery('.jcarousel-control a.play').bind('click', function() {
		carousel.startAuto();
		jQuery(this).hide();
		jQuery('.jcarousel-control a.pause').show();
	});
	*/
    jQuery('.jcarousel-scroll select').bind('change', function() {
        carousel.options.scroll = jQuery.jcarousel.intval(this.options[this.selectedIndex].value);
        return false;
    });

    jQuery('#mycarousel-next').bind('click', function() {
        carousel.next();
        return false;
    });

    jQuery('#mycarousel-prev').bind('click', function() {
        carousel.prev();
        return false;
    });
}
// Dynamic control
function writeControl(obj,auto) {
	var controlString = "<div class=\"jcarousel-control\"><p></p></div>";
	obj.prepend(controlString);
	var numLI = obj.find("li").size();
	var theString = "";
	for (var i=1; i <= numLI; i++) {
		theString = "<a href=\"#\">" + i + "</a>";
		$('.jcarousel-control p').append(theString);
	};
	/*
	jQuery('.jcarousel-control p').append("<a href=\"#\" class=\"pause\">Pause</a>");
	jQuery('.jcarousel-control p').append("<a href=\"#\" class=\"play\">Play</a>");
	if (auto > 0) {
		jQuery('.jcarousel-control a.play').css("display","none");
	} else {
		jQuery('.jcarousel-control a.pause').css("display","none");
		carousel.stopAuto();
	}
	*/
	return numLI;
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







