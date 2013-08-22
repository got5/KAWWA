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
 <doc:example module="app">
 <doc:source>
 <script>
 var module = angular.module('app',['kawwa2']);
 module.controller('myCtrl',function($scope){
      $scope.selected="";

      $scope.products = [
        {
            name:"palette01",
            img:"theme/img/k-theme0/palette01.png",
            rel:"Black"
        },
        {
            name:"palette02",
            img:"theme/img/k-theme0/palette02.png",
            rel:"Dark Blue"
        },
        {
            name:"palette01",
            img:"theme/img/k-theme0/palette01.png",
            rel:"Black"
        }
      ];
   });
 </script>
 <div class="k-product-options">
    <fieldset class="color">
        <legend>Choose your color : {{selected}}</legend>
            <p data-product-options="{}" data-products="products" data-name="palette">
            </p>
    </fieldset>
 </div>



 </doc:source>

 </doc:example>
 */


angular.module('kawwa2').run(function($templateCache) {
    $templateCache.put("ProductOptions",
        "<p>\n    <!-- directive: ng-repeat product in products -->\n    <input data-ng-model=\"selected\" type=\"radio\" id=\"{{product.name}}\" value=\"{{product.name}}\" name=\"{{name}}\" checked=\"{checked : $index==0}\"/>\n    <label for=\"{{product.name}}\"><img ng-src=\"{{product.img}}\" alt=\"{{product.alt}}\"/></label>\n    <!-- /ng-repeat -->\n</p>\n       \n\n");
});


angular.module('kawwa2')
.directive('productOptions', function ($templateCache) {
	return {
		restrict: 'A',
        template: $templateCache.get("ProductOptions"),
        replace:true,
        scope:{
           name:'@',
           products:'='
        },
		link:function (scope, element, attrs) {
			
			var json = jQuery.extend({}, scope.$eval(attrs.productOptions))
			
			jQuery(element).buttonset(json);
		}
	};
});

