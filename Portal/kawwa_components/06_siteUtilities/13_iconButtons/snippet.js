function toPrint() {
	if (jQuery("a.bt-print")) {
		jQuery("a.bt-print").click (function(){
			window.print();	
			return false;
		});
	}
}


$(document).ready(function(){
	toPrint();
});