'use strict';

angular.module('kawwa')
  .directive('fieldComment', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {
            
            var json = jQuery.extend({
			gravity: 'w',
			fade: true, 
			trigger: 'click'
		}, scope.$eval(attrs.fieldComment))
            
            jQuery(element).tipsy(json);
        }
    };
});