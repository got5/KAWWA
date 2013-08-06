'use strict';

angular.module('kawwa2').run(function($templateCache) {
    $templateCache.put("ProductGallery",
        "<div class=\"k-product-gallery photo-data\">\n    <p>\n        <a class=\"jqzoom\" rel=\"{{title}}\" href=\"{{gallery[0].hd}}\" title=\"{{gallery[0].title}}\">\n            <img class=\"photo\" ng-src=\"{{gallery[0].small}}\" alt=\"{{gallery[0].title}}\"/>\n        </a>\n    </p>\n    <ul class=\"thumblist\">    \n        <li ng-repeat=\"image in gallery\" ng-class=\"{zoomThumbActive : $index==0}\">\n            <a  href=\"#\" rel=\"{gallery: \'{{title}}\', smallimage:\'{{image.small}}\',largeimage:\'{{image.hd}}\'}\">\n            <img ng-src=\"{{image.thumb}}\" alt=\"{{image.title}}\"/></a></li>\n    </ul>\n</div>\n\n\n")
})


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
 * @param {Object} targets a variable from the scope of the controller. It allows you to overload the jqzoom jQuery plugin.
 * @element ANY
 *
 * @example
 * <doc:example>
 *     <doc:source>
 *         <script>
 *             angular.module('app',['kawwa2']).controller('myCtrl',function Ctrl($scope){
 *               var baseImagePath = 'https://raw.github.com/got5/KAWWA/master/kawwa2-angularjs/app/';
 *               $scope.gallery = [
 *                   {
 *                   title: "first image",
 *                   thumb: baseImagePath + 'img/productGallery/1s.png',
 *                   small: baseImagePath + 'img/productGallery/1m.png',
 *                   hd: baseImagePath + 'img/productGallery/1hd.png'
 *                  }, {
 *                   title: 'second image',
 *                   thumb: baseImagePath + 'img/productGallery/2s.png',
 *                   small: baseImagePath + 'img/productGallery/2m.png',
 *                   hd: baseImagePath + 'img/productGallery/2hd.png'
 *                  }, {
 *                   title: 'third image',
 *                   thumb: baseImagePath + 'img/productGallery/3s.png',
 *                   small: baseImagePath + 'img/productGallery/3m.png',
 *                   hd: baseImagePath + 'img/productGallery/3hd.png'
 *                  }];
 *                $scope.options = {
 *                    zoomType:'innerzoom',
 *                    lens: false,
 *                    preloadImages: true,
 *                    alwaysOn: false
 *                };
 *
 *             });
 *         </script>
 *         <div ng-controller="Ctrl">
 *              <div class="content">
 *                  <div data-title="gal1" data-product-gallery data-gallery="gallery" data-options="galleryOptions"/>
 *               </div>
 *         </div>
 *     </doc:source>
 *     <doc:scenario>
 *
 *     </doc:scenario>
 * </doc:example>
 */
angular.module('kawwa2')
.directive('productGallery', function ($templateCache,$timeout) {

    return {

        restrict: 'A',
        template: $templateCache.get("ProductGallery"),
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

