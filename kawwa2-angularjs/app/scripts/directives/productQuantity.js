'use strict';



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
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 include "../controllers/fieldComment.js"

 </script>
 include "../../views/productQuantity.html"

 </doc:source>

 </doc:example>
 */

function incrementTest() {
    var i = document.createElement('input');
    i.setAttribute('type', 'number');
    return i.type === 'text';
}

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