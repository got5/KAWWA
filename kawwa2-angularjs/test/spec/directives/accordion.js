'use strict';

describe('Directive: accordion', function () {

  // load the directive's module
  beforeEach(module('moduleApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<accordion></accordion>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the accordion directive');
  }));
});
