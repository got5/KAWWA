var isotopeApp = angular.module('kawwa', []);

function incrementTest() {
	var i = document.createElement('input');
	i.setAttribute('type', 'number');
	return i.type !== 'text';
}

function putObject(path, object, value) {
    var modelPath = path.split(".");

    function fill(object, elements, depth, value) {
        var hasNext = ((depth + 1) < elements.length);
        if(depth < elements.length && hasNext) {
            if(!object.hasOwnProperty(modelPath[depth])) {
                object[modelPath[depth]] = {};
            }
            fill(object[modelPath[depth]], elements, ++depth, value);
        } else {
            object[modelPath[depth]] = value;
        }
    }
    fill(object, modelPath, 0, value);
}

/**
 * Product Quantity
 */
angular.module('kawwa').directive('productQuantity', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {
            
            var json = jQuery.extend({}, scope.$eval(attrs.productQuantity))
            
            if(incrementTest()) {
                jQuery(element).addClass('uppydowner');
		jQuery(element).uppydowner();
            } else {
                jQuery(element).css('width', '3em');
            }
            
        }
    };
});

/**
 * ProductOptions
 */
angular.module('kawwa').directive('productOptions', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {
            
            var json = jQuery.extend({}, scope.$eval(attrs.productOptions))
            
            jQuery(element).buttonset(json);
        }
    };
});

/**
 * Field Comment
 */
angular.module('kawwa').directive('fieldComment', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {
            
            var json = jQuery.extend({
			gravity: 'w',
			fade: true, 
			trigger: 'focus'
		}, scope.$eval(attrs.fieldComment))
            
            jQuery(element).tipsy(json);
        }
    };
});

/**
 * Raty
 */
angular.module('kawwa').directive('kawwaRaty', function () {
    return {
        restrict: 'A',

        link:function (scope, element, attrs, controller) {
            var json = jQuery.extend({
                callback: function(value){
                    putObject(attrs.ngModel, scope, value);
                    if(!scope.$$phase) scope.$apply();
                }
            }, scope.$eval(attrs.kawwaRatingJson))
            jQuery(element).children("input").rating(json);

            scope.$watch(attrs.ngModel, function(value){
                jQuery(element).children("input").rating("select", ""+value);
            },true)

        }
    };
});





