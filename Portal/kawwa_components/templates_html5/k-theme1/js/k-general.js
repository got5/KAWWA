// KAWWA2.0 
// Le Studio/Web Platform, Atos Worldline, 2011
// General functions definitions - used all over the site

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
// Adds appropriate classes to main structure blocks (ie6 only)
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
	$(theInput).autocomplete({
		
		source: function(request, response){
            var ajaxRequest = {
            	url:availableTags,
                success: function(data){
                    response(eval(data));
                },
                type:"POST"
            };
            $.ajax(ajaxRequest);
        }
	});
}

function supports_input_placeholder() {
  var i = document.createElement('input');
  return 'placeholder' in i;
}


// MENUBAR -------------------------------------------
//  Check to see how to enable access to dropdown links by keyboard

function menuManager(){
var theMenu = $('ul.k-navbar');
    
    theMenu.attr("aria-label", "Main Navigation");
    theMenu.attr("role", "menubar");
     
    $('.dropdown').attr("role", "menu");
      
    $('.dropdown').children('li').each(function(){
         $(this).attr("role", "presentation");
         $(this).children('a').attr("role", "menuitem");
         $(this).children('a').attr("aria-haspopup", "false");
    });
      
    theMenu.children('li').each(function(){
        var $this = $(this);
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

function createDialog(window, trigger) {
    $(window).dialog({
        minHeight: 350,
        minWidth: 270,
        close: function(e){
            $(trigger).focus()
        },
        autoOpen: false,
        describedBy: "dialogDescription",
        modal: false
    });
    $(trigger).click(function(){
        $(window).dialog("open");
        return false;
    });
}

// All modals
// Trigger is defined by link class and modal is retrieved in link's href

function opensModal() {
	$('a.k-modal-trigger').each(function() {
		var theTarget = $(this).attr("href");
	    $(theTarget).dialog({
	        minHeight: 350,
	        minWidth: 470,
	        close: function(e){
	            $('.k-modal-trigger').focus()
	        },
	        autoOpen: false,
	        describedBy: "dialogDescription",
	        modal: true
	    });
	    $('.k-modal-trigger').click(function(){
	        $(theTarget).dialog("open");
	        return false;
	    });
    });
}


// FORM FIELD HELP -------------------------------------------
// Trigger must be full selector

function fieldHelp(window, trigger) {
    $(window).dialog({
        minHeight: 450,
        minWidth: 370,
        close: function(e){
            $(trigger).prev("input").focus()
        },
        autoOpen: false
    });
    $(trigger).click(function(){
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
	$("ul.k-menu li ul").not('.level2.open').hide();
	$("ul.k-menu strong").click (function(){
		$(this).siblings("ul.level2").slideToggle("slow").siblings("ul.level2:visible").slideUp("slow");
		$(this).siblings("ul.level2").toggleClass("open");
		$(this).toggleClass("active");
    });
}


// COPY TO CLIPBOARD -----------------------------

/* Gives an unique ID to the CSS code block */
function addCSSId() {
	
	var theId = "";
	var theContainerId = "";
	var theButtonId = "";

	$('.i-css-code').each(function(index) {
		theId = 'csssource';
		theContainerId = 'cssclipcontainer';
		theButtonId = 'cssclipbutton';
		var thePre = $(this).children('pre');
		thePre.attr('id',theId);
		thePre.siblings(".clip-container").attr('id',theContainerId);
		thePre.siblings(".clip-container").children(".clip-button").attr('id',theButtonId);
		//clipListener(theId, theContainerId, theButtonId);
		return false;
	});
}

/* Gives an unique ID to the HTML code block  */
function addHTMLId() {
	var theHTMLId = "";
	var theHTMLContainerId = "";
	var theHTMLButtonId = "";

	$('.i-html-code').each(function(index) {
		theHTMLId = 'htmlsource';
		theHTMLContainerId = 'htmlclipcontainer';
		theHTMLButtonId = 'htmlclipbutton';
		var theHTMLPre = $(this).children('div.code');
		theHTMLPre.attr('id',theHTMLId);
		theHTMLPre.siblings(".clip-container").attr('id',theHTMLContainerId);
		theHTMLPre.siblings(".clip-container").children(".clip-button").attr('id',theHTMLButtonId);
		return false;
	});
}

/* Gives an unique ID to the JS code block */
function addJSId() {
	var theJsId = "";
	var theJsContainerId = "";
	var theJsButtonId = "";
		
	$('.i-js-code').each(function(index) {
		theJsId = 'jssource';
		theJsContainerId = 'jsclipcontainer';
		theJsButtonId = 'jsclipbutton';
		var theJsPre = $(this).children('div.code');
		theJsPre.attr('id',theJsId);
		theJsPre.siblings(".clip-container").attr('id',theJsContainerId);
		theJsPre.siblings(".clip-container").children(".clip-button").attr('id',theJsButtonId);
		return false;
	});
}

/* Initiates zeroclipboard listener */
function clipListener(leId, leContainer, leButton) {
			var clip = null;
			clip = new ZeroClipboard.Client();
			clip.setHandCursor( true );
			clip.setText('');
			
			clip.addEventListener('onMouseOver', function (client) {
				// update the text on mouse over
				leId = "#" + leId;
				var theGoodText = entitify($(leId).html());
				clip.setText(theGoodText);
			});
			
			clip.addEventListener('complete', function (client, text) {
				alert("The code has been copied to clipboard");
			});
			
			clip.glue(leButton, leContainer);
}

/** SLIDER
 * We use the initCallback callback
 * to assign functionality to the controls
 */
function mycarousel_initCallback(carousel) {
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
};

//PRINT
function toPrint() {
	if (jQuery("a.bt-print")) {
		jQuery("a.bt-print").click (function(){
			window.print();	
			return false;
		});
	}
}

//PRINT
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
      if (jQuery("a.i-new-window")) {
            jQuery("a.i-new-window").click (function(){
                  var zeName = (windowName)? windowName : "_blank";
                  var zeTarget = jQuery(this).attr("href");
                  window.open(zeTarget, zeName);     
                  return false;
            });
      }
}














