'use strict';

angular.module('moduleApp')
  .controller('ProductOptionsCtrl', function ($scope) {
        $scope.products = [
            {
                name:"palette01",
                img:"components/kawwa/img/k-theme0/palette01.png",
                alt:"Black"
            },
            {
                name:"palette02",
                img:"components/kawwa/img/k-theme0/palette02.png",
                alt:"Dark Blue"
            },
            {
                name:"palette03",
                img:"components/kawwa/img/k-theme0/palette03.png",
                alt:"White"
            }
        ];
  });
