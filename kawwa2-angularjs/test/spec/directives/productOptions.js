'use strict';

describe('Directive: productOptions', function () {
  beforeEach(module('kawwa'));

  var element,scope,titles;

  beforeEach(inject(function ($rootScope, $compile) {
    
   

    element = angular.element("<div class=\"k-product-options\">\n                <fieldset class=\"color\" data-product-options=\"{}\">\n                    <legend>Choose your color : {{selected}}</legend>\n                    <p><input data-ng-model=\"selected\" type=\"radio\" id=\"palette01\" value=\"palette01\" name=\"palette\"\n                              checked=\"checked\"/>\n                        <label for=\"palette01\"><img src=\"theme/img/k-theme0/palette01.png\" alt=\"Black\"/></label>\n                        <input data-ng-model=\"selected\" type=\"radio\" id=\"palette02\" value=\"palette02\" name=\"palette\"/>\n                        <label for=\"palette02\"><img src=\"theme/img/k-theme0/palette02.png\" alt=\"Dark Blue\"/></label>\n                        <input data-ng-model=\"selected\" type=\"radio\" id=\"palette03\" value=\"palette03\" name=\"palette\"/>\n                        <label for=\"palette03\"><img src=\"theme/img/k-theme0/palette03.png\" alt=\"White\"/></label></p>\n                </fieldset>\n            </div>");
    scope = $rootScope;
    scope.selected = "palette03";
    $compile(element)(scope);
    scope.$digest();
    titles = element.find('input');
    
  }));

it('must have 3 inputs',inject(function ($rootScope, $compile) {
  
 expect(titles.length).toBe(3);  
 
}));

it('update model when click',inject(function ($rootScope, $compile) {
 
 titles[1].click();
 expect(scope.selected).toBe("palette02");
 
}));

 //  it('update view when change model',inject(function ($rootScope, $compile) {
 //  	var labels = element.find('label');
 
 
	// expect(labels.eq(2).attr("aria-pressed")).toBe("true");
 
 //  }));


});
