'use strict';

angular.module('moduleApp', ['kawwa2','ui.router'])
.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/components")
    //
    // Now set up the states
    $stateProvider
        .state('components', {
            url: "/components",
            views:{
                "productQuantity":{
                    templateUrl:"views/productQuantity.html",
                    controller:'ProductQuantityCtrl'
                },
                "productGallery":{
                    templateUrl:"views/productGallery.html",
                    controller:"ProductGalleryCtrl"
                },
                "fieldComment":{
                    templateUrl:"views/fieldComment.html",
                    controller:"FieldCommentCtrl"
                },
                "raty":{
                    templateUrl:"views/raty.html",
                    controller:"RatyCtrl"
                },
                "productOptions":{
                    templateUrl:"views/productOptions.html",
                    controller:"ProductOptionsCtrl"
                }
            }
        });

});
