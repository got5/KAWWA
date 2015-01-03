(function($){
	'use strict';

	$(document).ready(function(){
		var login = $('form.k-login');
		var loginLink = $('#loginLink');

		if (login.hasClass('collapsible')) {
			login.css('display', 'none');
			loginLink.children('a').attr('href', '#');
			loginLink.click(function() {
				$('form.k-login.collapsible').animate({
					height: 'toggle'
				}, {
					duration: 500,
					specialEasing: {
						height: 'linear'
					}
				});
			});
		}
	});
})(jQuery);
