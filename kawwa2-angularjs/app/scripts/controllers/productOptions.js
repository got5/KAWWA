'use strict';

angular.module('moduleApp')
  .controller('ProductOptionsCtrl', function ($scope) {
        $scope.products = [
            {
                name:"palette01",
                colorImg:"components/kawwa/img/k-theme0/palette01.png",
                color:"Black",
                size:"36"
            },
            {
                name:"palette02",
                colorImg:"components/kawwa/img/k-theme0/palette02.png",
                color:"Dark Blue",
                size:"38"
            },
            {
                name:"palette03",
                colorImg:"components/kawwa/img/k-theme0/palette03.png",
                color:"White",
                size:"40"
            }
        ];
        $scope.selected = "palette02";

        //Model has to be in a js object when used in a loop...
        $scope.selected2 = {
            "name":"palette03"
        };

        $scope.selected3 = {
            "name":"palette03"
        };



    });
