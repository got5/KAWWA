(function($){

    $.fn.extend({
    
       showsource: function(options){
        
            var defaults = {
                swfUrl: "ZeroClipboard10.swf"
            };
            var options = $.extend(defaults, options);
            
            ZeroClipboard.setMoviePath(options.swfUrl);
            
            var entitify = function(theString){
                var retour = "";
                var lines = $("li pre", theString);
                lines.each(function(){
                    var theString = $(this).html();
                    theString = theString.replace(/&lt;/gi, "<");
                    theString = theString.replace(/&gt;/gi, ">");
                    theString = theString.replace(/&quot;/gi, "\"");
                    retour += theString + "\n";
                });
                
                return retour;
            };
            
            var i = 0;
            return this.each(function(){
            
                i++;
                var elt = $(this);
                var eltId = this.id;
                
                var clip = null;
               
                clip = new ZeroClipboard.Client();
                clip.setHandCursor(true);
                var theGoodText = entitify($(".display-code", elt));
                clip.setText(theGoodText);
                
                clip.addEventListener('complete', function(client, text){
                    alert("The code has been copied to clipboard");
                });
                
                var buttonId = eltId + "-clip-button";
                var containerId = eltId + "-clip-container";
               
                clip.glue(buttonId, containerId);
                
                $("h3", elt).click(function(){
                    var c = $(".content", elt);
                    if (c.is(":visible")) {
                        c.slideUp("slow");
                    }
                    else {
                        c.slideDown("slow");
                    }
                });
         });
        }
    });
})(jQuery);
