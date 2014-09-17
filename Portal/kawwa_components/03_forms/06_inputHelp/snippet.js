function fieldHelp(window, trigger) {
    $(window).dialog({
        minHeight: 350,
        minWidth: 270,
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

$(document).ready(function(){
	if (jQuery.ui && jQuery.ui.dialog) {
		var theValue = "";
		$("a.k-field-help").each(function(){  
			theValue = $(this).attr("href");
			fieldHelp(theValue, ".k-field-help");
		});	
	}
});
