/**
 * @ngdoc directive
 * @name kawwa2.directive:kTab
 * @description
 * Render tab with the directive kTab and kPane
 *
 * @restrict A
 *
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 include "../controllers/kTab.js"

 </script>
 include "../../views/kTab.html"

 </doc:source>

 </doc:example>
 */

var TabsController = function($scope, $element) {
    var panes = $scope.panes = [];

    $scope.select = function(pane) {
        angular.forEach(panes, function(pane) {
            pane.selected = false;
        });
        pane.selected = true;
    };

    this.addPane = function(pane) {
        if (!panes.length) $scope.select(pane);
        panes.push(pane);
    };
};

angular.module('kawwa2').directive('kTabs', function() {
    return {
        restrict: 'EA',
        transclude: true,
        scope: {},
        controller: TabsController,
        templateUrl: 'template/tabs.html',
        replace: true
    };
});


angular.module('kawwa2').directive('kPane', function() {
    return {
        require: '^kTabs',
        restrict: 'EA',
        transclude: true,
        scope: { title: '@' },
        link: function(scope, element, attrs, tabsCtrl) {
            tabsCtrl.addPane(scope);
        },
        templateUrl: 'template/pane.html',
        replace: true
    };
});