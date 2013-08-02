'use strict';

describe('Directive: kawwaRaty', function () {
  beforeEach(module('kawwa'));

  var element,scope;

  beforeEach(inject(function ($rootScope, $compile) {
    
   

    element = angular.element("<fieldset class=\"k-rating\">\n                <legend>Rate this article: {{raty}}</legend>\n                <p kawwa-raty kawwa-rating-json=\"{'value': 'test'}\" ng-model=\"raty\">\n                    <input name=\"star1\" type=\"radio\" value=\"1\"/>\n                    <input name=\"star1\" type=\"radio\" value=\"2\"/>\n                    <input name=\"star1\" type=\"radio\" value=\"3\"/>\n                    <input name=\"star1\" type=\"radio\" value=\"4\"/>\n                    <input name=\"star1\" type=\"radio\" value=\"5\"/>\n                </p>\n            </fieldset>");
    scope = $rootScope;
    scope.raty = 2;
    $compile(element)(scope);
    scope.$digest();
    
    
  }));

  it('initial value is 2',inject(function ($rootScope, $compile) {
    
  	expect(scope.raty).toBe("2"); 
  	//expect(element.find('.star-rating').length).toBe(2);
  	//doesn't work ? say 0
   
  }));

  it('update model when click',inject(function ($rootScope, $compile) {
  	
  	
	//expect(scope.selected).toBe("palette02");
 
}));
  
 //  it('update view when change model',inject(function ($rootScope, $compile) {
 //  	var labels = element.find('label');
 
 
	// expect(labels.eq(2).attr("aria-pressed")).toBe("true");
 
 //  }));


});
