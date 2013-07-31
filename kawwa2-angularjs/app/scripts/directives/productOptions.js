'use strict';

angular.module('kawwa')
  .directive('productOptions', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {
            
            var json = jQuery.extend({}, scope.$eval(attrs.productOptions))
            
            jQuery(element).buttonset(json);
        }
    };
});

