var module = angular.module('app',['kawwa2']);
module.controller('myCtrl',function($scope){
    $scope.selected="";

    $scope.products = [
        {
            name:"palette01",
            img:"theme/img/k-theme0/palette01.png",
            rel:"Black"
        },
        {
            name:"palette02",
            img:"theme/img/k-theme0/palette02.png",
            rel:"Dark Blue"
        },
        {
            name:"palette01",
            img:"theme/img/k-theme0/palette01.png",
            rel:"Black"
        }
    ];
});