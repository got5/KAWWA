'use strict';
/* @name kawwa2.directive:rating
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

        include "../controllers/rating.js"

        </script>
    include "../../views/rating.html"

    </doc:source>

    </doc:example>
*/



angular.module('kawwa2')
  .directive('rating', function () {
    return {
      restrict: 'A',
      template:'<p ng-class="getRatingCss()" ng-transclude></p>',
      transclude:true,
      replace:true,
      scope:{
          rating:'='
      },
      controller:function($scope,$element,$attrs){
          $scope.getRatingCss = function(){
              var rating = Math.floor($scope.rating);
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
