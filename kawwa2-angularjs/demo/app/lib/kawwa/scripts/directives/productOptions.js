'use strict';
/**
 * @ngdoc directive
 * @name kawwa2.directive:productOptions
 * @description
 * Enhances standard form elements like buttons, inputs and anchors to themeable buttons with appropriate hover and active styles.
 * Use the widget button from jQuery-UI
 *
 * @element ANY
 * @restrict A
 * @param {Object=} productOptions  allows you to overload the button widget
 */
angular.module('kawwa2')
.directive('productOptions', function () {
	return {
		restrict: 'A',

		link:function (scope, element, attrs) {
			
			var json = jQuery.extend({}, scope.$eval(attrs.productOptions))
			
			jQuery(element).buttonset(json);
		}
	};
});

