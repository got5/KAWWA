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

