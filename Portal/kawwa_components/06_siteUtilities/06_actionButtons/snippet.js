function toPrint() {
	if (jQuery(".bt-print")) {
		jQuery(".bt-print").click (function(event){
			event.preventDefault();
			window.print();	
			return false;
		});
	}
}


$(document).ready(function(){
	toPrint();
});
