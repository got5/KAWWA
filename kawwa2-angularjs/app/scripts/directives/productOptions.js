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

