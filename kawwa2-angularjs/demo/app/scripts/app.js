'use strict';

angular.module('demoApp', ['kawwa'])
.config(function ($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl: 'views/main.html',
    controller: 'MainCtrl'
  })
  .when('/components', {
    templateUrl: 'views/components.html',
    controller: 'ComponentsCtrl'
  })
  .otherwise({
    redirectTo: '/'
  });
});
