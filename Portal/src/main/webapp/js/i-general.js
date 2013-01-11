// KAWWA2
// Le Studio/Web Platform, Atos Worldline, 2013
// General functions definitions for the portal


// LIST OF COMPONENTS CONTROL
// Depends on masonry.js

/*function libraryControl() {
		$(this).masonry({
			itemSelector : 'section',
			columnWidth : function(containerWidth) {
				return containerWidth / 5;
			}
		});
	
		var point = $(this).offset();
		$('.compo-entry').bind('click', function(event) {
			$('#components-list p.go-top a').focus();
			$('html').animate({
				scrollTop : point.top
			}, 1500);
			event.preventDefault();
		});
		$('#components-list p.go-top a').bind('click', function(event) {
			$('nav .compo-entry').focus();
			$('html').animate({
				scrollTop : 0
			}, 1500);
			event.preventDefault();
		});
}; */

(function($) {
	$.fn.libraryControl = function () {
		$('#components-list').masonry({
			itemSelector : 'section',
			columnWidth : function(containerWidth) {
				return containerWidth / 5;
			}
		});
	
		var point = $('#components-list').offset();

		$('.compo-entry').bind('click', function(event) {
			$('#components-list p.go-top a').focus();
			$('html, body').animate({
				scrollTop : point.top
			}, 1500);
			event.preventDefault();
		});
		$('#components-list p.go-top a').bind('click', function(event) {
			$('nav .compo-entry').focus();
			$('html, body').animate({
				scrollTop : 0
			}, 1500);
			event.preventDefault();
		});
	};
})( jQuery );
