'use strict';

/**
 * @ngdoc directive
 * @name kawwa2.directive:fieldComment
 * @description
 * Show a tool tip when an action occur on the element. Use the Tipsy jQuery plugin
 *
 * @restrict A
 * @param {Object=} fieldComment  allows you to overload the Tipsy jQuery plugin
 * @param {String} title The content of the tooltip
 *
 * @example
 <doc:example module="app">
 <doc:source>
 <script>
 var module = angular.module('app',['kawwa2']);
 module.controller('myCtrl',function($scope){

              });
 </script>

 <p><label for="field1">Enter something:</label>
 <input class="k-field-comment" type="text" id="field1" name="field1" data-field-comment="{'gravity': 'w'}"
 title="A short comment about this field" />
 </p>


 </doc:source>

 </doc:example>
 */
angular.module('kawwa2')
.directive('fieldComment', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs) {
            
            var json = jQuery.extend({
             gravity: 'w',
             fade: true, 
             trigger: 'click'
         }, scope.$eval(attrs.fieldComment))
            
            jQuery(element).tipsy(json);
        }
    };
});