// KAWWA2
// Le Studio/Web Platform, Atos Worldline, 2013
// General functions definitions for the portal


// LIST OF COMPONENTS CONTROL
// Depends on masonry.js


(function($) {
      $.fn.libraryControl = function () {
            $(this).masonry({
                  itemSelector : 'section',
                  columnWidth : function(containerWidth) {
                        return containerWidth / 5;
                  }
            });
            var zLink = $(this).children('p.go-top').children('a');
            var point = $(this).offset();
            $('.compo-entry').bind('click', function(event) {
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
		
	   //Code for twitter      
      !function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
})( jQuery );

