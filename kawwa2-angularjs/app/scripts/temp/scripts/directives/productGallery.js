'use strict';


/**
 * @ngdoc directive
 * @name kawwa2.directive:productGallery
 * @description
 * Create an image gallery using the jqzoom jQuery plugin.
 *
 * @restrict A
 * @param {string} title Give a title to your gallery
 * @param {Array of Object} gallery targets a variable from the scope of the controller. It is an array of object containing  for each one :
 *   - title: the title of the image
 *   - thumb: the path to the thumblr image
 *   - small: the path to the small image
 *   - hd: the path to the big image
 *
 * @param {Object} options a variable from the scope of the controller. It allows you to overload the jqzoom jQuery plugin.
 * @element ANY
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 angular.module('moduleApp')
   .controller('ProductGalleryCtrl', function ($scope) {
         var baseImagePath = '/'

         $scope.gallery = [
             {
                 title: "first image",
                 thumb: baseImagePath + 'img/productGallery/1s.png',
                 small: baseImagePath + 'img/productGallery/1m.png',
                 hd: baseImagePath + 'img/productGallery/1hd.png'
             }, {
                 title: 'second image',
                 thumb: baseImagePath + 'img/productGallery/2s.png',
                 small: baseImagePath + 'img/productGallery/2m.png',
                 hd: baseImagePath + 'img/productGallery/2hd.png'
             }, {
                 title: 'third image',
                 thumb: baseImagePath + 'img/productGallery/3s.png',
                 small: baseImagePath + 'img/productGallery/3m.png',
                 hd: baseImagePath + 'img/productGallery/3hd.png'
             }

         ];
         $scope.galleryOptions = {
             zoomType:'innerzoom',
             lens: false,
             preloadImages: true,
             alwaysOn: false
         };
   });


 </script>
 <h4>Product Gallery</h4>
 <div ng-controller="ProductGalleryCtrl" class="content">
     <div data-title="gal1" data-product-gallery data-gallery="gallery" data-options="galleryOptions"/>
 </div>

 </doc:source>

 </doc:example>
 */



angular.module('kawwa2')
.directive('productGallery', function ($timeout) {

    return {

        restrict: 'A',
        templateUrl:'template/productGallery.html' ,
        replace:true,
        scope:{
          options:"=",
          title:"@",
          gallery:"="
      },
      link: function(scope, element) {
        if(!scope.title){
            scope.title="gal1";
        }
        
        if (! scope.options) {
            scope.options = {};
        }
        scope.options = jQuery.extend({
            zoomType:'standard',
            lens: true,
            preloadImages: true,
            alwaysOn: false
        },
        scope.options
        );
        
        $timeout(function(){
            if(jQuery.fn.jqzoom) {
               element.children(0).children(0).jqzoom(scope.options);
            }
        },0)
    }
}
});

