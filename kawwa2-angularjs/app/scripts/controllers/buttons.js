angular.module('moduleApp').
    controller('KButtonCtrl', function ($scope) {
        $scope.model = 1;

        $scope.checkModel = {
            left: false,
            center: true,
            right: false
        };

        $scope.radioValues = {
            left:'Left',
            center:'Center',
            right:'Right'
        };
        $scope.radioModel = 'Center';
        

    });