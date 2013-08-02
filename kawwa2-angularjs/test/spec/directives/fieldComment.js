'use strict';

describe('Directive: fieldComment', function () {
  beforeEach(module('kawwa'));

  var element,scope,input;

  beforeEach(inject(function ($rootScope, $compile) {
    
   
   
    element = angular.element("<p><label for=\"field1\">Enter something:</label>\n            <input class=\"k-field-comment\" type=\"text\" id=\"field1\" name=\"field1\" data-field-comment=\"{'gravity': 'w'}\"\n                   title=\"A short comment about this field\" />\n        </p>");
    scope = $rootScope;
    $compile(element)(scope);
    scope.$digest();
    
    input = element.find('input');
    
    
  }));
 //need to execute the test in document
 it('must display the tooltip',inject(function ($rootScope, $compile) {
  //input[0].focus(); 
  //var tipsy = element.find('div.tipsy');
  //expect(tipsy.count()).toBe(1); 
  // div.tipsy is add at the beginning of <body> how to test it ?
  
}));

});
