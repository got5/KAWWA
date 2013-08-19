'use strict';

function incrementTest() {
	var i = document.createElement('input');
	i.setAttribute('type', 'number');
	return i.type === 'text';
}

/**
 * @ngdoc inputType
 * @name ng.directive:input.text
 *
 * @description
 * Standard HTML text input with angular data binding.
 *
 * @param {string} ngModel Assignable angular expression to data-bind to.
 * @param {string=} name Property name of the form under which the control is published.
 * @param {string=} required Adds `required` validation error key if the value is not entered.
 * @param {string=} ngRequired Adds `required` attribute and `required` validation constraint to
 *    the element when the ngRequired expression evaluates to true. Use `ngRequired` instead of
 *    `required` when you want to data-bind to the `required` attribute.
 * @param {number=} ngMinlength Sets `minlength` validation error key if the value is shorter than
 *    minlength.
 * @param {number=} ngMaxlength Sets `maxlength` validation error key if the value is longer than
 *    maxlength.
 * @param {string=} ngPattern Sets `pattern` validation error key if the value does not match the
 *    RegExp pattern expression. Expected value is `/regexp/` for inline patterns or `regexp` for
 *    patterns defined as scope expressions.
 * @param {string=} ngChange Angular expression to be executed when input changes due to user
 *    interaction with the input element.
 * @param {boolean=} [ngTrim=true] If set to false Angular will not automatically trimming the
 *    input.
 *
 * @example
 <doc:example module="app">
 <doc:source>
 <script>
 var module = angular.module('app',['kawwa2']);

 console.log(module);
 module.controller('Ctrl',['$scope',function($scope) {
    console.log("hello ********");
             $scope.text = 'guest';
             $scope.word = /^\s*\w*\s*$/;
           }]);
 </script>
 <form name="myForm" ng-controller="Ctrl">
 Single word: <input type="text" name="input" ng-model="text"
 ng-pattern="word" required ng-trim="false">
 <span class="error" ng-show="myForm.input.$error.required">
 Required!</span>
 <span class="error" ng-show="myForm.input.$error.pattern">
 Single word only!</span>

 <tt>text = {{text}}</tt><br/>
 <tt>myForm.input.$valid = {{myForm.input.$valid}}</tt><br/>
 <tt>myForm.input.$error = {{myForm.input.$error}}</tt><br/>
 <tt>myForm.$valid = {{myForm.$valid}}</tt><br/>
 <tt>myForm.$error.required = {{!!myForm.$error.required}}</tt><br/>
 </form>
 </doc:source>
 <doc:scenario>
 it('should initialize to model', function() {
            expect(binding('text')).toEqual('guest');
            expect(binding('myForm.input.$valid')).toEqual('true');
          });

 it('should be invalid if empty', function() {
            input('text').enter('');
            expect(binding('text')).toEqual('');
            expect(binding('myForm.input.$valid')).toEqual('false');
          });

 it('should be invalid if multi word', function() {
            input('text').enter('hello world');
            expect(binding('myForm.input.$valid')).toEqual('false');
          });

 it('should not be trimmed', function() {
            input('text').enter('untrimmed ');
            expect(binding('text')).toEqual('untrimmed ');
            expect(binding('myForm.input.$valid')).toEqual('true');
          });
 </doc:scenario>
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