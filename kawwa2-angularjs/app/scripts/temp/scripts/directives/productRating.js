'use strict';
/**
 *@ngdoc directive
* @name kawwa2.directive:productRating
* @description
* Add the specific css class to render some star to rate a product .
*
* @param {scope value : number} rating  the rating as a number between 0 and 5
* @element p
* @restrict A
    *
    *
    * @example
<doc:example module="moduleApp">
    <doc:source>
        <script>

        angular.module('moduleApp')
          .controller('ProductRatingCtrl', function ($scope) {
                $scope.rating = 2;
                
            });


        </script>
    <h4>Product Rating</h4>
    <div data-ng-controller="ProductRatingCtrl" class="product-data">
        <p data-product-rating="rating"><a href="#">900 comment(s)</a></p>
    </div>

    </doc:source>

    </doc:example>
*/



angular.module('kawwa2')
  .directive('productRating', function () {
    return {
      restrict: 'A',
      template:'<p ng-class="getRatingCss()" ng-transclude></p>',
      transclude:true,
      replace:true,
      controller:function($scope,$element,$attrs){
          var rate = $scope.$eval($attrs.productRating);
          $scope.getRatingCss = function(){
              var rating = Math.floor(rate);
              var cssClass = {rating: true };

              if(rating){
                  switch (rating){
                      case 5:
                          cssClass.five = true;
                          break;
                      case 4:
                          cssClass.four = true;
                          break;
                      case 3:
                          cssClass.three = true;
                          break;
                      case 2:
                          cssClass.two = true;
                          break;
                      case 1:
                          cssClass.one = true;
                          break;
                      case 0:
                          cssClass.zero = true;
                          break;
                      default:
                          cssClass.zero = true;
                  }
                  return cssClass;
              }
          }
      }

    };
  });
