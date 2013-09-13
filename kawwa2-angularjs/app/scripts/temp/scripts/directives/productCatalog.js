'use strict';

/**
 * @ngdoc directive
 * @name kawwa2.directive:productCatalog
 * @description
 * template for displaying a product.
 * Contain:
 *
 *  - an image
 *  - link to the detail product page
 *  - rating
 *  - add to basket button
 *
 * @restrict A
 * @param {Object} product an complex object containing some of the property : see below
 * @param {Object} options Some options to configure the template:
 *
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

 angular.module('moduleApp').controller('ProductCatalogCtrl', function ($scope) {

     $scope.basket = [];

     $scope.product = {
         "id": 1,
         "name": "AngularJS",
         "author": "Brad Green, Shyam Seshadri",
         "price": 15.34,
         "description": "Description...",
         "category": "book",
         "isNew": true,
         "comments": [
             {
                 "rate": 2,
                 "user": "Toto",
                 "comment": "Test comment"
             },
             {
                 "rate": 5,
                 "user": "Jojo",
                 "comment": "Test comment"
             },
             {
                 "rate": 3,
                 "user": "Fifi",
                 "comment": "Test comment"
             }
         ]
     };

     //make the rating of the product from the average of the comments
     var productRating = function(pItem){
         if (pItem.comments) {
             var sumRatings = 0;
             for ( var index = 0; index < pItem.comments.length; index++) {
                 var comment = pItem.comments[index];
                 sumRatings += comment.rate;
             }
             return Math.floor(sumRatings / pItem.comments.length);
         }
         return 0;
     }

     //minimalist basket
     var addToBasket = function (product) {

         var i = $scope.basket.indexOf(product);
         if(i ==-1){
             product.quantity = 1;
             $scope.basket.push(product);

         }else{
             $scope.basket[i].quantity++;
         }
     };

     $scope.options={
         "urlImage":'img/catalog',
         "urlProduct":'#/product/detail',
         "urlComment":'#/product/comment',
         "rating":productRating,
         "add":addToBasket,
         "basketText":"Ajouter au panier",
         "currency":'€',
         "newText":'nouv'
     };

     $scope.view = 'block';

 });

 </script>
 <h4>Product Catalog</h4>
 <div data-ng-controller="ProductCatalogCtrl">
     <div  class="k-catalog">
     <article data-product-catalog data-product="product" data-options="options" data-view="view"></article>
     </div>

     Basket: <p ng-repeat="b in basket"> product: {{b.name}} <b ng-show="b.quantity > 1"> | quantity : {{b.quantity}}</b>  </p>
 </div>

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
