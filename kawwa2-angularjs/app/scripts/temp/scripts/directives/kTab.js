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


 angular.module('moduleApp')
     .controller('KTabCtrl', function ($scope) {
         $scope.first = "premier";
         $scope.second = "deuxième";
         $scope.third = "troisième";
         
     });

 </script>
 <h4>Tabs</h4>
 <article class="k-article">
     <div data-k-tabs>
         <div data-k-pane title="First Tab">first content {{first}}</div>
         <div data-k-pane title="Second Tab">second content {{second}}</div>
         <div data-k-pane title="Third Tab">third content {{third}}</div>
     </div>
 </article>

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