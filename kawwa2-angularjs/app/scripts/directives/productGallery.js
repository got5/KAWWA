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

 include "../controllers/productGallery.js"

 </script>
 include "../../views/productGallery.html"

 </doc:source>

 </doc:example>
 */



angular.module('kawwa2')
.directive('productGallery', function ($timeout) {

    return {

        restrict: 'A',
        templateUrl:'tpl/productGallery.html' ,
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

