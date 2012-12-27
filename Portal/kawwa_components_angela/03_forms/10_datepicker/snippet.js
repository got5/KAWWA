/* Date picker does not apply to IE6 or 7 */

$(document).ready(function(){
	if (!(ie6 || ie7)) {
		
		if (jQuery.ui && jQuery.ui.datepicker) {
			/* Use the commented code below to apply translation initialisation
		 	jQuery(function($){
				$.datepicker.regional['fr'] = {
					closeText: 'Fermer',
					prevText: 'Mois précedent',
					nextText: 'Mois suivant',
					currentText: 'Courant',
					monthNames: ['Janvier','Février','Mars','Avril','Mai','Juin',
					'Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
					monthNamesShort: ['Jan','Fév','Mar','Avr','Mai','Jun',
					'Jul','Aoû','Sep','Oct','Nov','Déc'],
					dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
					dayNamesShort: ['Dim','Lun','Mar','Mer','Jeu','Ven','Sam'],
					dayNamesMin: ['Di','Lu','Ma','Me','Je','Ve','Sa'],
					dateFormat: 'dd/mm/yy', firstDay: 1,
					isRTL: false};
				$.datepicker.setDefaults($.datepicker.regional['fr']);
			});*/
			jQuery( "input.k-datepick" ).datepicker({
				showOn: "button",
				buttonImage: "../img/k-theme1/pic_calendar.gif",
				buttonImageOnly: true,
				buttonText: "Click to open/close the calendar"
			});
		}
	}
});
