try {
  angular.module('kawwa');
} catch (err) {
  angular.module('kawwa', []);
}
;
'use strict';
angular.module('kawwa').directive('fieldComment', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs, controller) {
      var json = jQuery.extend({
          gravity: 'w',
          fade: true,
          trigger: 'click'
        }, scope.$eval(attrs.fieldComment));
      jQuery(element).tipsy(json);
    }
  };
});
;
'use strict';
angular.module('kawwa').run([
  '$templateCache',
  function ($templateCache) {
    $templateCache.put('ProductGallery', '<div class="k-product-gallery photo-data">\n    <p>\n        <a class="jqzoom" rel="{{title}}" href="{{gallery[0].hd}}" title="{{gallery[0].title}}">\n            <img class="photo" ng-src="{{gallery[0].small}}" alt="{{gallery[0].title}}"/>\n        </a>\n    </p>\n    <ul class="thumblist">    \n        <li ng-repeat="image in gallery" ng-class="{zoomThumbActive : $index==0}">\n            <a  href="#" rel="{gallery: \'{{title}}\', smallimage:\'{{image.small}}\',largeimage:\'{{image.hd}}\'}">\n            <img ng-src="{{image.thumb}}" alt="{{image.title}}"/></a></li>\n    </ul>\n</div>\n\n\n');
  }
]);
angular.module('kawwa').directive('kawwaProductGallery', [
  '$templateCache',
  '$timeout',
  function ($templateCache, $timeout) {
    return {
      restrict: 'A',
      template: $templateCache.get('ProductGallery'),
      replace: true,
      scope: {
        options: '=',
        title: '@',
        gallery: '='
      },
      link: function (scope, element, attrs, controller) {
        if (!scope.title) {
          scope.title = 'gal1';
        }
        if (!scope.options) {
          scope.options = {};
        }
        scope.options = jQuery.extend({
          zoomType: 'standard',
          lens: true,
          preloadImages: true,
          alwaysOn: false
        }, scope.options);
        $timeout(function () {
          if (jQuery.fn.jqzoom) {
            element.children(0).children(0).jqzoom(scope.options);
          }
        }, 0);
      }
    };
  }
]);
;
'use strict';
function putObject(path, object, value) {
  var modelPath = path.split('.');
  function fill(object, elements, depth, value) {
    var hasNext = depth + 1 < elements.length;
    if (depth < elements.length && hasNext) {
      if (!object.hasOwnProperty(modelPath[depth])) {
        object[modelPath[depth]] = {};
      }
      fill(object[modelPath[depth]], elements, ++depth, value);
    } else {
      object[modelPath[depth]] = value;
    }
  }
  fill(object, modelPath, 0, value);
}
angular.module('kawwa').directive('kawwaRaty', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs, controller) {
      var json = jQuery.extend({
          callback: function (value) {
            putObject(attrs.ngModel, scope, value);
            if (!scope.$$phase)
              scope.$apply();
          }
        }, scope.$eval(attrs.kawwaRatingJson));
      jQuery(element).children('input').rating(json);
      scope.$watch(attrs.ngModel, function (value) {
        jQuery(element).children('input').rating('select', '' + value);
      }, true);
    }
  };
});
;
'use strict';
angular.module('kawwa').directive('productOptions', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs, controller) {
      var json = jQuery.extend({}, scope.$eval(attrs.productOptions));
      jQuery(element).buttonset(json);
    }
  };
});
;
'use strict';
function incrementTest() {
  var i = document.createElement('input');
  i.setAttribute('type', 'number');
  return i.type === 'text';
}
angular.module('kawwa').directive('productQuantity', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs, controller) {
      var json = jQuery.extend({}, scope.$eval(attrs.productQuantity));
      if (incrementTest()) {
        jQuery(element).addClass('uppydowner');
        jQuery(element).uppydowner();
      } else {
        jQuery(element).css('width', '3em');
      }
    }
  };
});