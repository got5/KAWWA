(function($){
	'use strict';

	/* With Feature detection for date input type */

	function dateTest() {
		var i = document.createElement('input');
		i.setAttribute('type', 'date');
		return i.type !== 'text';
	}

	$(document).ready(function(){

		var dTest = dateTest();

			if (!dTest) {
				if ($.ui && $.ui.datepicker) {
					$('input[type=date]').addClass('k-datepick');

					/* Use the commented code below to apply translation initialisation
					 jQuery(function($){
						jQuery.datepicker.regional['fr'] = {
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
					$('input.k-datepick').datepicker({
						showOn: 'button',
						buttonImage: '../img/[[THEME_IMG]]/pic_calendar.gif',
						buttonImageOnly: true,
						buttonText: 'Click to open/close the calendar'
					});
				}
			} else {
				$('input[type=date]').css('width', '9em');
			}
	});
})(jQuery);
