/* All modals - Copy this function definition to a separate file */

function createDialog(dlink) {
	var theTarget = dlink.attr("href");
	jQuery(theTarget).dialog({
		minHeight: 350,
		minWidth: 470,
		close: function(e) {
			dlink.focus();
			jQuery(this).dialog('widget').remove();
		},
		autoOpen: false,
		describedBy: "dialogDescription",
		modal: true
	});
}

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

/* Call */
$(document).ready(function(){
	if(jQuery.ui && jQuery.ui.dialog) {
		opensModal();
	}
});
