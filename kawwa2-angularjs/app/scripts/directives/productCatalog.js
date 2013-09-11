'use strict';

/**
 * @ngdoc directive
 * @name kawwa2.directive:productCatalog
 * @description
 * template for displaying a product.
 * Contain:
 *  - an image
 *  - link to the detail product page
 *  - rating
 *  - add to basket button
 *
 * @restrict A
 * @param {Object} product an complex object containing some of the property : see below
 * @param {Object} options Some options to configure the template:
 *   - urlImage: the base url to the products images
 *   - urlProduct: the base url to the products details page
 *   - urlComment: the base url to the products comments page
 *   - noCommentText: 'no comment (default)
 *   - commentText: 'comment(s)' (default)
 *   - rating: the function which take the product as parameter and must return the rating
 *   - add: that function is called when clicked on the addtobasket button. The product is passed as parameter.
 *
 * @param {$scope value} view define if we are in list view or block view
 * @element article
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 include "../controllers/productCatalog.js"

 </script>
 include "../../views/productCatalog.html"

 </doc:source>

 </doc:example>
 */


angular.module('moduleApp')
  .directive('productCatalog', function () {
    return {
      templateUrl: 'template/productCatalog.html',
      restrict: 'A',
      replace:'true',
      scope:{
          product:'=',
          options:'=',
          view:'='
      },
      controller:function($scope){
          $scope.options = angular.extend({

              urlComment:'#',
              noCommentText:'No comment',
              commentText:'comment(s)',
              "add":function(){
                  console.log("add to basket clicked (default)")
              },
              "basketText":'Add to basket',
              "newText":'new',
              "currency":'$'
          },$scope.options);

      }

    };
  });
