(function( $ ) {

$.extend(Tapestry.Initializer, {
    showSource: function(specs) {
    	$("#"+specs.id + " div.content").css("display", "block");
    	$("#"+specs.id).showsource(specs.params);
    	$("#"+specs.id).collapsiblePanel();

    	if(!$("#"+specs.id + " h4").hasClass("ui-state-active"))
    		$("#"+specs.id + " div.content").css("display", "none");
    }
});

}) ( jQuery );