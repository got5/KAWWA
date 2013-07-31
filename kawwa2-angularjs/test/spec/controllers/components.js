'use strict';

describe('Controller: ComponentsCtrl', function () {

  // load the controller's module
  beforeEach(module('kawwa'));

  var ComponentsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ComponentsCtrl = $controller('ComponentsCtrl', {
      $scope: scope
    });
  }));

  
});
