'use strict';

angular.module('kawwa').run(function($templateCache) {
    $templateCache.put("ProductGallery",
        "<div class=\"k-product-gallery photo-data\">\n    <p>\n        <a class=\"jqzoom\" rel=\"{{title}}\" href=\"{{gallery[0].hd}}\" title=\"{{gallery[0].title}}\">\n            <img class=\"photo\" ng-src=\"{{gallery[0].small}}\" alt=\"{{gallery[0].title}}\"/>\n        </a>\n    </p>\n    <ul class=\"thumblist\">    \n        <li ng-repeat=\"image in gallery\" ng-class=\"{zoomThumbActive : $index==0}\">\n            <a  href=\"#\" rel=\"{gallery: \'{{title}}\', smallimage:\'{{image.small}}\',largeimage:\'{{image.hd}}\'}\">\n            <img ng-src=\"{{image.thumb}}\" alt=\"{{image.title}}\"/></a></li>\n    </ul>\n</div>\n\n\n")
})

angular.module('kawwa')
.directive('kawwaProductGallery', function ($templateCache,$timeout) {

    return {

        restrict: 'A',
        template: $templateCache.get("ProductGallery"),
        replace:true,
        scope:{
          options:"=",
          title:"@",
          gallery:"="
      },
      link: function(scope, element, attrs, controller) {
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

