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
 * @ngModel model  result of the radio selected. Has to Be a js object if you used the directive in a loop
 * @param {Object=} productOptions value of the radio button
 *
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 angular.module('moduleApp')
   .controller('ProductOptionsCtrl', function ($scope) {
         $scope.products = [
             {
                 name:"palette01",
                 colorImg:"components/kawwa/img/k-theme0/palette01.png",
                 color:"Black",
                 size:"36"
             },
             {
                 name:"palette02",
                 colorImg:"components/kawwa/img/k-theme0/palette02.png",
                 color:"Dark Blue",
                 size:"38"
             },
             {
                 name:"palette03",
                 colorImg:"components/kawwa/img/k-theme0/palette03.png",
                 color:"White",
                 size:"40"
             }
         ];
         $scope.selected = "palette02";

         //Model has to be in a js object when used in a loop...
         $scope.selected2 = {
             "name":"palette03"
         };

         $scope.selected3 = {
             "name":"palette03"
         };



     });


 </script>
 <h4>Product Options</h4>
 <div datang-controller="ProductOptionsCtrl" class="k-product-options">
     <fieldset class="color">
         <legend>Choose your color : {{selected}}</legend>
         <p>
             <input  data-product-options="products[0].name" data-ng-model="selected"  id="{{products[0].name}}"/>
             <label for="{{products[0].name}}"><img data-ng-src="{{products[0].colorImg}}" alt="{{products[0].color}}"/></label>
             <input  data-product-options="products[1].name" data-ng-model="selected"  id="{{products[1].name}}"/>
             <label for="{{products[1].name}}"><img data-ng-src="{{products[1].colorImg}}" alt="{{products[1].color}}"/></label>
             <input  data-product-options="products[2].name" data-ng-model="selected"  id="{{products[2].name}}"/>
             <label for="{{products[2].name}}"><img data-ng-src="{{products[2].colorImg}}" alt="{{products[2].color}}"/></label>

         </p>

     </fieldset>
     <p>Within data-ng-repeat directive</p>
     <fieldset class="color">
         <legend>Choose your color : {{selected2.name}}</legend>
         <p >
             <span data-ng-repeat="product in products">
             <input data-ng-model="selected2.name" data-product-options="product.name" id="{{product.name}}"/>
             <label  for="{{product.name}}"><img data-ng-src="{{product.colorImg}}" alt="{{product.color}}"/></label>
             </span>
         </p>

     </fieldset>

     <div class="k-product-options">
         <fieldset class="size">
             <legend>Choose your size: {{selected3.name}}</legend>

             <p>
                 <span data-ng-repeat="product in products">
                 <input   data-ng-model="selected3.name" data-product-options="product.name" id="s{{product.name}}"/>
                 <label  for="s{{product.name}}">{{product.size}}</label>
                 </span>
             </p>

         </fieldset>
     </div>
 </div>

 </doc:source>

 </doc:example>
 */



angular.module('kawwa2')
.directive('productOptions', function () {
        var activeClass = 'ui-state-active';
        var toggleEvent = 'click';

        return {

            require:'ngModel',
            link:function (scope, element, attrs, ngModelCtrl) {

                //model -> UI
                ngModelCtrl.$render = function () {
                    element.next().toggleClass(activeClass, angular.equals(ngModelCtrl.$modelValue, scope.$eval(attrs.productOptions)));
                };

                //ui->model
                element.next().bind(toggleEvent, function () {
                    if (!element.next().hasClass(activeClass)) {
                        scope.$apply(function () {
                            ngModelCtrl.$setViewValue(scope.$eval(attrs.productOptions));
                            ngModelCtrl.$render();
                        });
                    }
                });
            }
	};
});

