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
                "tooltip":{
                templateUrl:"views/tooltip.html",
                controller:"TooltipCtrl"
            },
                "raty":{
                    templateUrl:"views/raty.html",
                    controller:"RatyCtrl"
                },
                "productRating":{
                    templateUrl:"views/productRating.html",
                    controller:"ProductRatingCtrl"
                },
                "productOptions":{
                    templateUrl:"views/productOptions.html",
                    controller:"ProductOptionsCtrl"
                },
                "productCatalog":{
                    templateUrl:"views/productCatalog.html",
                    controller:"ProductCatalogCtrl"
                },
                "button":{
                    templateUrl:"views/buttons.html",
                    controller:"KButtonCtrl"
                },
                "accordion":{
                    templateUrl:"views/accordion.html",
                    controller:"AccordionCtrl"
                },
                "collapse":{
                    templateUrl:"views/collapse.html",
                    controller:"CollapseCtrl"
                },
                "carousel":{
                    templateUrl:"views/carousel.html",
                    controller:"CarouselCtrl"
                },
                "kTab":{
                    templateUrl:"views/kTab.html",
                    controller:"KTabCtrl"
                }

            }
        });

});
