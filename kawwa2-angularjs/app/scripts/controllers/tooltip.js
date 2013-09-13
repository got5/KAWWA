angular.module("moduleApp")
    .controller('TooltipCtrl', function ($scope) {
        $scope.dynamicTooltipText = "Dynamic";
        $scope.dynamicTooltip = "It's time to chew bubble gum";
    });