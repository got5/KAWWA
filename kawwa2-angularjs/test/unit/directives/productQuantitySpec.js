'use strict';

describe('Directive: productQuantity', function () {
  beforeEach(module('kawwa2'));

  var element,scope,input;

  beforeEach(inject(function ($rootScope, $compile) {
    
   

    element = angular.element("<p class=\"k-quantity\">\n                <input type=\"number\" value=\"1\" title=\"Define quantity to add to basket\" data-product-quantity=\"{}\"/>\n            </p>");
    scope = $rootScope;
    $compile(element)(scope);
    scope.$digest();
    
    input = element.find('input');

  }));

  it('must be rend correctly',inject(function ($rootScope, $compile) {
    expect(input.attr("value")).toBe("1"); 
    if(input.attr("type") !== "number"){
     expect(element.find('span')).toBe(2);
     expect(input.attr("class")).toBe("uppydowner");
   }
   
   
 }));

  


});
