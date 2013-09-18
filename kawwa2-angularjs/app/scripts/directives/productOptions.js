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

 include "../controllers/productOptions.js"

 </script>
 include "../../views/productOptions.html"

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

