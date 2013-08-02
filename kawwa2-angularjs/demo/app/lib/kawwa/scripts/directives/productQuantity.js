'use strict';

function incrementTest() {
	var i = document.createElement('input');
	i.setAttribute('type', 'number');
	return i.type === 'text';
}

angular.module('kawwa')
.directive('productQuantity', function () {

    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {

            var json = jQuery.extend({}, scope.$eval(attrs.productQuantity))
            
            if(incrementTest()) {
                jQuery(element).addClass('uppydowner');
                jQuery(element).uppydowner();
            } else {
                jQuery(element).css('width', '3em');
            }
            
        }
    };
});