'use strict';

/**
 * @ngdoc directive
 * @name kawwa2.directive:raty
 * @description
 * Create a Rating widget from radio input. It uses the rating jquery plugin.
 *
 *
 * @param {number} ngModel Assignable angular rating to data-bind to.
 * @param {Object=} ratingOptions  allows you to overload the rating jquery plugin
 * @restrict A
 * @element ANY
 *
 * @example
 <doc:example module="app">
 <doc:source>
 <script>
 var module = angular.module('app',['kawwa2']);
 module.controller('myCtrl',function($scope){

                $scope.raty = 2;

  });

 </script>

 <fieldset class="k-rating">
 <legend>Rate this article: {{raty}}</legend>
 <p data-raty data-rating-options="{'value': 'test'}" ng-model="raty">
 <input name="star1" type="radio" value="1"/>
 <input name="star1" type="radio" value="2"/>
 <input name="star1" type="radio" value="3"/>
 <input name="star1" type="radio" value="4"/>
 <input name="star1" type="radio" value="5"/>
 </p>
 </fieldset>


 </doc:source>

 </doc:example>
 */
function putObject(path, object, value) {
    var modelPath = path.split(".");

    function fill(object, elements, depth, value) {
        var hasNext = ((depth + 1) < elements.length);
        if(depth < elements.length && hasNext) {
            if(!object.hasOwnProperty(modelPath[depth])) {
                object[modelPath[depth]] = {};
            }
            fill(object[modelPath[depth]], elements, ++depth, value);
        } else {
            object[modelPath[depth]] = value;
        }
    }
    fill(object, modelPath, 0, value);
}

angular.module('kawwa2')
.directive('raty', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs) {
            var json = jQuery.extend({
                callback: function(value){
                    putObject(attrs.ngModel, scope, value);
                    if(!scope.$$phase) scope.$apply();
                }
            }, scope.$eval(attrs.ratingOptions))
            jQuery(element).children("input").rating(json);

            scope.$watch(attrs.ngModel, function(value){
                jQuery(element).children("input").rating("select", ""+value);
            },true)

        }
    };
});

