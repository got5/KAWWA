// KAWWA2
// Le Studio/Web Platform, Atos Worldline, 2013
// General functions definitions for the portal


// LIST OF COMPONENTS CONTROL
// Depends on masonry.js


(function($) {
	  var pluginName = 'libraryControl', defaults = {
		  	masonry: {
	        	itemSelector : 'section',
	            columnWidth: 100
	        }
	  };
	  
	  
	  function LibraryControlPlugin(element, options){
	  
	  		this.element = element;
	  		this.options = $.extend({}, defaults, options);
	  		
	  		this._defaults = defaults;
	  		this._name = pluginName;
	  		
	  		this.init();
	  }
	  
	  LibraryControlPlugin.prototype.init = function(){
	  		component = this.element;
	  		
	  		if(!(ie && ie < 8))
	  			component.masonry(this.options.masonry);
            
            var zLink = component.children('p.go-top').children('a');
            //var zThis = $(this);
            var point;
            $('.compo-entry').bind('click', function(event) {
                  point = component.offset();
                  $(zLink).focus();
                  $('html, body').animate({
                        scrollTop : point.top
                  }, 1500);
                  event.preventDefault();
            });
            $(zLink).bind('click', function(event) {
                  $('nav .compo-entry').focus();
                  $('html, body').animate({
                        scrollTop : 0
                  }, 1500);
                  event.preventDefault();
            });
	  }
	  
	  $.fn[pluginName] = function( options ){
	  	return this.each(function() {
	  		if(!$.data(this, 'plugin_' + pluginName)){
	  			$.data(this, 'plugin_' + pluginName,
	  			new LibraryControlPlugin($(this), options));
	  		}
	  	});
	  }
	   
})( jQuery );

/*(function($) {
	$.fn.libraryControl = function () {
		
		$(this).masonry({
			itemSelector : 'section',
      		columnWidth: 100
		});
		
		var zLink = $(this).children('p.go-top').children('a');
		var zThis = $(this);
		var point;
		$('.compo-entry').bind('click', function(event) {
			point = zThis.offset();
			$(zLink).focus();
			$('html, body').animate({
				scrollTop : point.top
			}, 1500);
			event.preventDefault();
		});
		$(zLink).bind('click', function(event) {
			$('nav .compo-entry').focus();
			$('html, body').animate({
				scrollTop : 0
			}, 1500);
			event.preventDefault();
		});
	};
	
})( jQuery );*/
!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");

