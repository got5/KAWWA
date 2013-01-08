/* Function adds ARIA roles/states*/
function menuManager(){
    var theMenu = jQuery('ul.k-navbar');
    
    theMenu.attr("aria-label", "Main Navigation");
    theMenu.attr("role", "menubar");
     
    jQuery('ul.dropdown').attr("role", "menu");
      
    jQuery('ul.dropdown').children('li').each(function(){
         jQuery(this).attr("role", "presentation");
         jQuery(this).children('a').attr("role", "menuitem");
         jQuery(this).children('a').attr("aria-haspopup", "false");
    });
      
    theMenu.children('li').each(function(){
        var $this = jQuery(this);
        if ($this.parent().not("ul.dropdown")) {
            $this.attr("role", "presentation");
            var $span = $this.children('a');
            $span.attr("role", "menuitem");
            
            /*if there's a dropdown*/
            if ($span.siblings().is(".dropdown")) {                    
            	$span.attr("aria-haspopup", "true");
                $span.addClass("hasdropdown");
                theMenu.superfish(); 
            }
        }         
    });
}

/* Function call */
$(document).ready(function(){
	if (jQuery.fn.superfish) {
	    menuManager();
	}
});
