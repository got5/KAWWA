'use strict';

function incrementTest() {
	var i = document.createElement('input');
	i.setAttribute('type', 'number');
	return i.type === 'text';
}

/**
 * @ngdoc directive
 * @name kawwa2.directive:productQuantity
 * @description
 * Add the specific buttons to the standard HTML input with type number, if the browser can't render it.
 *
 * @param {Object=} productQuantity  allow you to overload the uppydowner jquery plugin
 * @element input
 * @restrict A
 *
 *
 * @example
 <doc:example module="app">
 <doc:source>
 <script>
 var module = angular.module('app',['kawwa2']);
 module.controller('myCtrl',function($scope){

 });
 </script>
    <p class="k-quantity">
        <input type="number" value="1" title="Define quantity to add to basket" data-product-quantity="{}"/>
    </p>

 </doc:source>

 </doc:example>
 */

angular.module('kawwa2')
.directive('productQuantity', function () {

    return {
        restrict: 'A',

        link:function (scope, element, attrs) {

            var json = jQuery.extend({}, scope.$eval(attrs.productQuantity))
            
            if(incrementTest()) {
                jQuery(element).addClass('uppydowner');
                jQuery(element).uppydowner(json);
            } else {
                jQuery(element).css('width', '3em');
            }
            
        }
    };
});