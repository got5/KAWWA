(function($){
	
	$.extend(Tapestry.Initializer, {
		verticalMenu: function(specs) {
			if($('a.one-level')){
				$('a.one-level').bind('click', function(){
					window.location.href = $(this).attr("href");
				});
			}
        },
    	panel: function(specs){
            $("#" + specs.id).panel(specs.options);
        }, 
        responsiveMainNav: function(specs){
        	$("#" + specs.id).addClass("k-navbar").flexNav();
        },
        mainNav: function(specs) {
        	$("#" + specs.id+" > li > div, #" + specs.id+" > li > ul").addClass("dropdown");
        	
            var theMenu = $("#" + specs.id).addClass("k-navbar");
            
            theMenu.attr("aria-label", "Main Navigation");
            theMenu.attr("role", "menubar");
             
            $('ul.dropdown').attr("role", "menu");
              
            $('ul.dropdown').children('li').each(function(){
                 $(this).attr("role", "presentation");
                 $(this).children('a').attr("role", "menuitem");
                 $(this).children('a').attr("aria-haspopup", "false");
            });
              
            theMenu.children('li').each(function(){
                var $this = $(this);
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
        },
        languageSelection: function(specs){
        	if(specs.mode=="false") {
        		var element = $("#" + specs.id + " fieldset.k-radio p select")
        		
        	}else{
        		$("#" + specs.id + " fieldset.k-radio").buttonset();
        		var element = $("#" + specs.id + " fieldset.k-radio p input");
        	}
        	
        	element.bind("change", function(){
    			$.ajax({
    				success: function(response){
    					// Check for complete URL.
    					var redirectURL = response.redirectURL;
    	                if (/^https?:/.test(redirectURL)) {
    	                    window.location = redirectURL;
    	                    return;
    	                }
    	                
    	                window.location.pathname = redirectURL;
    				},
    				url: specs.url,
    				type: "POST",
    				data: {lang: $(this).val()}
    			});
    		});
        },
        siteSearch: function(specs) {
        	if(supports_input_placeholder){
        		
        		$("#"+specs.id + ".k-autocomplete").attr("placeholder", specs.placeholder)
        		.focus(function(){
            		if($(this).val()==specs.placeholder) $(this).attr("value","");
            	})
            	.blur(function(){
            		if($(this).val()=="") $(this).attr("value", specs.placeholder);
            	}).attr("value",specs.placeholder);
        		
        	}
        	else {
        		
        		jQuery("#"+specs.id + ".k-autocomplete").attr("value", specs.placeholder);
        		
        	}
        }, 
        productquantity: function(specs){
       	 $("input[id=\"" + specs.id + "\"]").uppydowner(specs.params);
        }
    });
	
	function supports_input_placeholder() {
		  var i = document.createElement('input');
		  return 'placeholder' in i;
	}
})(jQuery);