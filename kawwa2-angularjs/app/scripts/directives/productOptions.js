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
 * @param {String} name is the attribut name use for the submission.
 * @param {Array of Object} products contains the infos for each buttons you want. These infos are
 *    - name {string} will be the value of the element selected
 *    - img {string} the path to the image
 *    -rel {string}  for the img tag
 *
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 include "../controllers/productOptions.js"

 </script>
 include "../../views/productOptions.html"

 </doc:source>

 </doc:example>
 */



angular.module('kawwa2')
.directive('productOptions', function ($templateCache,$timeout) {
	return {
		restrict: 'A',
        templateUrl: 'tpl/productOptions.html',
        replace:true,
        scope:{
           name:'@',
           products:'=',
           selected:'='
        },
		link:function (scope, element, attrs) {
			
			var json = jQuery.extend({}, scope.$eval(attrs.productOptions))
           $timeout(function(){
               $(element).buttonset();
           },10)





		}
	};
});

