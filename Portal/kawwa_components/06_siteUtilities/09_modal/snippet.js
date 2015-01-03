(function($){
	'use strict';

	/* All modals - Copy this function definition to a separate file */

	function createDialog(dlink) {
		var theTarget = dlink.attr('href');
		$(theTarget).dialog({
			minHeight: 350,
			minWidth: 470,
			close: function(e) {
				dlink.focus();
				$(this).dialog('widget').remove();
			},
			autoOpen: false,
			describedBy: 'dialogDescription',
			modal: true
		});
	}

	function opensModal() {
		$('a.k-modal-trigger').each(function() {
			var theTarget = $(this).attr('href');
			createDialog($(this));
			$(this).click(function() {
				createDialog($(this));
				$(theTarget).dialog('open');
				return false;
			});
		});
	}

	/* Call */
	$(document).ready(function(){
		if($.ui && $.ui.dialog) {
			opensModal();
		}
	});
})(jQuery);
