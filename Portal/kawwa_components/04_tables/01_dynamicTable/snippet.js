(function($){
	'use strict';

	$(document).ready(function(){
		if($.fn.dataTable) {
			$('table.k-data-table').dataTable({
				'sPaginationType': 'full_numbers',
				'oLanguage': {
					'sSearch': 'You Search :',
					'oPaginate': {
						'sFirst': '1st',
						'sPrevious': 'Prev'
					}
				}
			});
		}
	});
})(jQuery);
