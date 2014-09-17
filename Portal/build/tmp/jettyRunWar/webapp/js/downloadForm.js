(function($){
    
	 $.extend(Tapestry.Initializer, {
    	downloadPage: function(specs){
    		
    		addBasicHandler("downloadForm", specs);
    		
    		$("#downloadForm select[name='theme']").bind("change",function(){
    			sendAjax($(this).attr("name"), $(this).attr("value"), specs.url);
    		});
    		
    		$("#downloadForm input[name='componentList']").bind("change",function(){
    			

    			var value=$("#downloadForm input[name='componentList']").attr("value");
    			if(value=="") value= ',';
    			
    			
    			sendAjax($("#downloadForm input[name='componentList']").attr("name"), value, specs.url);
    		});
    		
        }, 
        basketDownload: function(specs){
        	
        	$('.k-basket.k-panel').panel();
        	
        	if (document.getElementById("zoneBasket")) {
        		addBasicHandler("zoneBasket", specs);
    		}
        }
    });
})(jQuery);

function addBasicHandler(prefix, specs){
	$("#"+prefix+" input[name='examples']").bind("change",function(){
		sendAjax($(this).attr("name"), $(this).is(":checked"), specs.url);
	});
	$("#"+prefix+" input[name='tapestry']").bind("change",function(){
		sendAjax($(this).attr("name"), $(this).is(":checked"), specs.url);
	});
	$("#"+prefix+" input[name='doctype']").bind("change",function(){
		sendAjax($(this).attr("name"), $(this).attr("value"), specs.url);
	});
}
function sendAjax(name, value, url){
	$.ajax({
		url: url, 
		data: "name="+name+"&value="+value
	});
}
