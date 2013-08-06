'use strict';

/**
 * @ngdoc directive
 * @name kawwa2.directive:fieldComment
 * @description
 * Show a tool tip when an action occur on the element. Use the Tipsy jQuery plugin
 *
 * @restrict A
 * @param {Object=} fieldComment  allows you to overload the Tipsy jQuery plugin
 */
angular.module('kawwa2')
.directive('fieldComment', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs) {
            
            var json = jQuery.extend({
             gravity: 'w',
             fade: true, 
             trigger: 'click'
         }, scope.$eval(attrs.fieldComment))
            
            jQuery(element).tipsy(json);
        }
    };
});